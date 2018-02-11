package com.fallon.springbootapp.textblockparser;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class TextBlockParserController {
    
    @RequestMapping(value="/parsetext", method=RequestMethod.POST)
    public Word[] parseText(@RequestBody JsonTextBlock jsonText) {
        String text = jsonText.getText();

        TextBlockParser parser = new TextBlockParser();
        return parser.parseText(text);
    }

}
