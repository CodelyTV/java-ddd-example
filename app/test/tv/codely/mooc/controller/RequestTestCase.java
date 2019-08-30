package tv.codely.mooc.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class RequestTestCase {
    @Autowired
    private MockMvc mockMvc;

    public void getRequest(String endpoint, Integer expectedStatusCode, String expectedResponse) throws Exception {
        mockMvc
            .perform(get(endpoint))
            .andExpect(status().is(expectedStatusCode))
            .andExpect(content().json(expectedResponse));
    }

    public void postRequest(String endpoint, Integer expectedStatusCode) throws Exception {
        mockMvc
            .perform(post(endpoint))
            .andExpect(status().is(expectedStatusCode))
            .andExpect(content().string(""));
    }

    public void putRequest(String endpoint, Integer expectedStatusCode) throws Exception {
        mockMvc
            .perform(put(endpoint))
            .andExpect(status().is(expectedStatusCode))
            .andExpect(content().string(""));
    }
}
