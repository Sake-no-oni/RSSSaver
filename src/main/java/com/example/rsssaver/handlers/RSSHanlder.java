package com.example.rsssaver.handlers;

import com.example.rsssaver.entities.RSSItem;
import com.example.rsssaver.repos.RSSItemRepo;
import com.example.rsssaver.utils.RSSItemsDecoder;
import com.example.rsssaver.utils.WebClient;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrii Filimonov
 */
public class RSSHanlder {
    
    @Autowired
    WebClient webClient;
    
    @Autowired
    RSSItemsDecoder decoder;
    
    @Autowired
    RSSItemRepo rssItemRepo;
    
    public Map<String, String> saveRSSFeed(List<String> urls) {
        String status = "ok";
        String details = "Executed successfully";
        
        for (String url: urls) {
            try {
                Map<String, String> httpResp = webClient.doGet(url);
                String respCode = httpResp.get("respCode");
                if (respCode.equals("200")) {
                    String rawRSSFeed = httpResp.get("resp");
                    for (RSSItem item: decoder.decodeRSSItems(rawRSSFeed)) {
                        if (!rssItemRepo.existsRSSItemByTitle(item.getTitle())) {
                            rssItemRepo.save(item);
                        }
                    }
                } else {
                    status = "failed";
                    details = String.format("The URL %s has returned the code %s", url, respCode);
                }
            } catch (IOException | ParserConfigurationException | SAXException ex) {
                status = "failed";
                details = String.format("An exception has occured: ", ex);
            }            
        }
        Map<String, String> resp = new HashMap<>();
        resp.put("status", status);
        resp.put("details", details);
        return resp;
    }
    
}
