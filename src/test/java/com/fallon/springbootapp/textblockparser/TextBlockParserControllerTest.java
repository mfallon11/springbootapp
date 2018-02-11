package com.fallon.springbootapp.textblockparser;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TextBlockParserControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new TextBlockParserController()).build();
    }

    @Test
    public void shouldReturnArrayOfWords() throws Exception {
        mockMvc.perform(post("/parsetext")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"A random text string of text\"}"))
                        .andExpect(status().isOk())
                        .andExpect(content().json("[{\"word\":\"A\",\"occurrences\":1},{\"word\":\"of\",\"occurrences\":1},{\"word\":\"random\",\"occurrences\":1},{\"word\":\"string\",\"occurrences\":1},{\"word\":\"text\",\"occurrences\":2}]"));
    }

}
