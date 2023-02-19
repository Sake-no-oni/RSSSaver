package com.example.rsssaver;

import com.example.rsssaver.entities.RSSItem;
import com.example.rsssaver.repos.RSSItemRepo;
import com.example.rsssaver.utils.RSSItemsDecoder;
import com.example.rsssaver.utils.WebClient;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

@SpringBootTest
class DemoApplicationTests {
    
    @Autowired
    WebClient webClient;
    
    @Autowired
    RSSItemsDecoder rssItemsDecoder;
    
    @Autowired
    RSSItemRepo rssItemRepo;
    
    @Test
    public void testWholeFlow() throws IOException, ParserConfigurationException, SAXException {
        Map<String, String> res = webClient.doGet("https://news.google.com/news?cf=all&hl=en&pz=1&ned=us&output=rss");
        String rawRSSXML = res.get("resp");
        
        Assertions.assertTrue(res.get("resp").length() > 0);
        Assertions.assertEquals("200", res.get("respCode"));
        
        List<RSSItem> rssItemsLst = rssItemsDecoder.decodeRSSItems(rawRSSXML);
        RSSItem rssItem = rssItemsLst.get(0);
        
        Assertions.assertTrue(rssItem.getTitle().length() > 0);
        Assertions.assertTrue(rssItem.getLink().length() > 0);
        Assertions.assertTrue(rssItem.getDescr().length() > 0);
        
        String title = rssItem.getTitle();
        rssItemRepo.save(rssItem);
        
        Assertions.assertEquals(title, rssItemRepo.findByTitle(title).getTitle());
    }
}
