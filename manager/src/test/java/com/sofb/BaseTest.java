package com.sofb;


import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableTransactionManagement
public class BaseTest {
    @Before
    public void before() {
    }


    @After
    public void after() {
    }
}
