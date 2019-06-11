package com.sofb.authorizing;

import com.sofb.cache.RedisCache;
import com.sofb.config.ShiroProperties;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.crazycake.shiro.exception.SerializationException;
import org.crazycake.shiro.serializer.ObjectSerializer;
import org.crazycake.shiro.serializer.RedisSerializer;
import org.crazycake.shiro.serializer.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;

@Component
public class RedisSessionDao extends CachingSessionDAO {

    private static final Logger logger = LoggerFactory.getLogger("RedisSessionDao");
    private String keyPrefix = "shiro_redis_session";

    private RedisSerializer keySerializer = new StringSerializer();
    private RedisSerializer valueSerializer = new ObjectSerializer();

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ShiroProperties shiroProperties;

    @Override
    protected void doUpdate(Session session) {
        this.saveSession(session);
    }

    @Override
    protected void doDelete(Session session) {
        Serializable sessionId = generateSessionId(session);
        jedisPool.getResource().del(serializeRedisSessionKey(getRedisSessionKey(sessionId)));
        //redisCache.getRedisTemplate(CacheType.SESSION).delete();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        if (serializable == null) {
            logger.warn("session id is null");
            return null;
        } else {
            logger.debug("read session from redis");
            Session s = deserializeRedisSessionValue(jedisPool.getResource().get(serializeRedisSessionKey(this.getRedisSessionKey(serializable))));
            //Session s = deserializeRedisSessionValue((byte[]) redisCache.getRedisTemplate(CacheType.SESSION).opsForValue().get(serializeRedisSessionKey(this.getRedisSessionKey(serializable))));
            return s;
        }
    }

    private void saveSession(Session session) {
        if ((session == null) || (session.getId() == null)) {
            logger.error("session or session id is null");
            return;
        }
        String key = getRedisSessionKey(session.getId());
        session.setTimeout(shiroProperties.getSessionTimeOut() * 1000);
        jedisPool.getResource().set(serializeRedisSessionKey(key), serializeRedisSessionValue(session));
        jedisPool.getResource().expire(key, shiroProperties.getSessionTimeOut());
        //redisCache.getRedisTemplate(CacheType.SESSION).opsForValue().set(serializeRedisSessionKey(key), session, shiroProperties.getSessionTimeOut(), TimeUnit.SECONDS);
    }

    private String getRedisSessionKey(Serializable sessionId) {
        return this.keyPrefix + sessionId;
    }

    private byte[] serializeRedisSessionKey(String key) {
        byte[] result = null;
        try {
            result = keySerializer.serialize(key);
        } catch (SerializationException e) {
            e.printStackTrace();
        }
        return result;
    }

    private byte[] serializeRedisSessionValue(Session session) {
        byte[] result = null;
        try {
            result = valueSerializer.serialize(session);
        } catch (SerializationException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Session deserializeRedisSessionValue(byte[] value) {
        Session deserialize = null;
        try {
            deserialize = (Session) valueSerializer.deserialize(value);
        } catch (SerializationException e) {
            e.printStackTrace();
        }
        return deserialize;
    }

}
