package com.example.rsssaver.controllers;

import com.example.rsssaver.handlers.RSSHanlder;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 * @author Andrii Filimonov
 */

@Controller
@RequestMapping("rsstools")
public class RSSController {
    
    @Autowired
    RSSHanlder handler;
    
    @RequestMapping(method = RequestMethod.POST, value = "/savefeeds", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    byte[] saveFeeds(@RequestBody Map<String, List<String>> requestMap) {
        List<String> urls = requestMap.get("urls");
        Map<String, String> res = handler.saveRSSFeed(urls);
        return new JSONObject(res).toString().getBytes();
    }
    
}
