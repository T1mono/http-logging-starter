package ru.t1.opencschool.httploggingstarter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Класс для тестирования просто контроллера.
 */
@SpringBootTest
@AutoConfigureMockMvc
class HttpLoggingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFilterWithEmptyRequestId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/test")
                        .header("X-Request-ID", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("GET Response"));
    }

    @Test
    public void testFilterWithPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("POST Response"));
    }

    @Test
    public void testFilterWithPutRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("PUT Response"));
    }

    @Test
    public void testFilterWithDeleteRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("DELETE Response"));
    }
}