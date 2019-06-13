package com.sofb.controller;

import com.sofb.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class RoleControllerTest extends BaseTest {
    @Test
    public void list_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/role/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void save_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/role/save")
                .param("roleName", "test")
                .param("description", "测试员"))
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void disable_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/role/2/disable"))
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void addSource_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/role/addSource")
                .param("id", "1")
                .param("resourceIds", "1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }
}
