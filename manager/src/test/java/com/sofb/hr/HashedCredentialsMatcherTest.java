package com.sofb.hr;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class HashedCredentialsMatcherTest {

    @Test
    public void test() {
        String algorithmName = "MD5";
        String username = "yves";
        String password = "123";
        String salt1 = username;
        //String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        String salt2 = "ed650f0ccbbbba61eeac0915f8256a56";
        int hashIterations = 2;

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        SimpleHash hash = new SimpleHash(algorithmName, token.getCredentials(), salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println(salt2);
        System.out.println(encodedPassword);
    }


}
