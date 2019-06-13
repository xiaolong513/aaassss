package com.sofb.controller;

import com.sofb.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class PersonControllerTest extends BaseTest {

    @Test
    public void createPerson_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/person/createPerson")
                .param("phone", "123456789")
                .param("number", "110112")
                .param("userName", "yves"))
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void list_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/person/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果
        System.out.println("============================");
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    @Rollback(false)
    public void changePassword_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/person/72a229ad-1588-49cc-9374-31891d1e6f9c/changePassword")
                .param("newPassword", "123"))
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果
        System.out.println("============================");
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    @Rollback(false)
    public void addRole_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/person/addRole")
                .param("id", "72a229ad-1588-49cc-9374-31891d1e6f9c")
                .param("roleIds", "1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果
        System.out.println("============================");
        System.out.println(result.getResponse().getContentAsString());
    }


}
