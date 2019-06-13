package com.sofb;


import com.sofb.common.SessionPerson;
import com.sofb.hr.Person;
import com.sofb.hr.PersonService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableTransactionManagement(proxyTargetClass = true)
public class BaseTest {
    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Autowired
    private PersonService personService;

    @Before // 在测试开始前初始化工作
    public void setup() {
        //设置当前登录人
        Person person = personService.getByUserName("admin");
        SessionPerson.set(person);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @After
    public void after() {
    }
}
