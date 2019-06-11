package com.sofb.shiro;

import com.sofb.common.StringUtil;
import com.sofb.hr.Person;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private static String algorithmName = "md5";
    private final static int hashIterations = 2;

    public static void encryptPassword(Person person) {

        person.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                person.getPassword(),
                ByteSource.Util.bytes(person.getCredentialsSalt()),
                hashIterations).toHex();

        person.setPassword(newPassword);
    }

    public static String encryptPassword(String credentialsSalt, String password) {
        if (StringUtil.isEmpty(credentialsSalt) || StringUtil.isEmpty(password)) {
            throw new IllegalArgumentException("参数不合法");
        }
        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(credentialsSalt),
                hashIterations).toHex();
        return newPassword;
    }
}
