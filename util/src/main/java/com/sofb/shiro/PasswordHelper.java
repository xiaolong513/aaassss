package com.sofb.shiro;

import com.sofb.hr.Person;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private final int hashIterations = 2;

    public void encryptPassword(Person person) {

        person.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                person.getPassword(),
                ByteSource.Util.bytes(person.getCredentialsSalt()),
                hashIterations).toHex();

        person.setPassword(newPassword);
    }
}
