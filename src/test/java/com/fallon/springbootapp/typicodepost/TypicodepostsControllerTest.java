package com.fallon.springbootapp.typicodepost;

import com.fallon.springbootapp.textblockparser.TextBlockParserController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TypicodepostsControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new TypicodePostsController()).build();
    }

    @Test
    public void shouldReturnProperJson() throws Exception {
        this.mockMvc.perform(get("/typicode").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
                //.andExpect(content().json("[\"Hello World\"]"));
    }
}
