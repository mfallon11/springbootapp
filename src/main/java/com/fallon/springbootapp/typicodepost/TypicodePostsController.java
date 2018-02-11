package com.fallon.springbootapp.typicodepost;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class TypicodePostsController {

    @RequestMapping("/typicode")
    public JsonNode typicode() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";

        // Some type of exponential backoff strategy should be considered here
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        JsonNode root = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            root = mapper.readTree(response.getBody());
            System.out.println("");
        } catch(IOException e) {}

        return root;
    }

}
