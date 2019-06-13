package com.sofb.controller;

import com.sofb.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ResourceControllerTest extends BaseTest {
    @Test
    public void list_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/resource/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void save_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/resource/save")
                .param("resourceName", "admin")
                .param("priority", "1")
                .param("resourceType", "MENU")
                .param("parentId", "1")
                .param("permission", "role:*")
                .param("description", "权限列表")
                .param("url", "http://www.baidu.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void disable_test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/role/297e6c466b4e8501016b4e8561a60000/disable"))
                .andExpect(MockMvcResultMatchers.status().isOk())// 模拟向testRest发送get请求
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
    }
}
