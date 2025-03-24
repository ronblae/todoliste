package com.inmediasp.todoList.integrationTests.restController;

import com.inmediasp.todoList.restController.RedirectController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RedirectController.class)
class RedirectControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser
    void WHEN_accessingAnyRessource_THEN_redirectToRoot() throws Exception {
        mockMvc.perform(get("/somepath"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/"));
    }
}