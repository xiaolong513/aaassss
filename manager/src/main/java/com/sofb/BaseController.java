package com.sofb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sofb.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
    }

    public HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getSession();
    }

    public ServletContext getServletContext() {
        return ContextLoader.getCurrentWebApplicationContext()
                .getServletContext();
    }


    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 获取参数
     *
     * @param request
     * @return
     */
    protected Map<String, Object> requstParamToMap(HttpServletRequest request) {
        final Map<String, Object> result = new HashMap<String, Object>();
        final Map<?, ?> paramMap = request.getParameterMap();
        for (Map.Entry<?, ?> e : paramMap.entrySet()) {
            final Object key = e.getKey();
            final Object value = e.getValue();

            if (!(key instanceof String)) {
                continue;
            }
            if (null == value) {
                continue;
            }
            if (!(value instanceof String || value instanceof String[])) {
                continue;
            }
            if (value instanceof String) {
                result.put((String) key, (String) value);
                continue;
            }
            final String[] vs = (String[]) value;
            if (vs.length == 0) {
                continue;
            }
            if (vs.length == 1) {
                result.put((String) key, vs[0]);
                continue;
            }
            result.put((String) key, value);
        }
        result.put("ipAddr", this.getIpAddr(request));
        //处理数组和map
        for (Map.Entry<String, Object> e : result.entrySet()) {
            final Object value = e.getValue();

            if (value instanceof String) {
                final Object v = stringToCollection((String) value);
                e.setValue(v);
                continue;
            }

            if (value instanceof String[]) {
                final List<Object> list = new ArrayList<Object>();
                final String[] vs = (String[]) value;
                for (String v : vs) {
                    list.add(stringToCollection((String) v));
                    e.setValue(list);
                }
            }
        }

        return result;
    }

    /**
     * 简单的处理
     *
     * @param string
     * @return
     */
    @SuppressWarnings({"static-access", "unchecked", "rawtypes", "deprecation"})
    private Object stringToCollection(String string) {
        if (StringUtil.isEmpty(string)) {
            return string;
        }

        final Map clsMap = new HashMap();
        clsMap.put(".*", Map.class);

        final String value = string.trim();
        if (value.startsWith("[") && value.endsWith("]")) {
            final JSONArray jsonArray = JSONArray.parseArray(value);
            return JSONObject.parseArray(jsonArray.toJSONString(), Map.class);
            //return JSONArray.toList(jsonArray, Map.class, clsMap);
        }
        if (value.startsWith("{") && value.endsWith("}")) {
            return JSON.parseObject(value, Map.class);
        }
        return value;
    }
}
