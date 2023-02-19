package com.example.rsssaver.utils;

import com.example.rsssaver.entities.RSSItem;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrii Filimonov
 */
public class RSSItemsDecoder {
    
    public List<RSSItem> decodeRSSItems(String rssasxml) throws ParserConfigurationException, SAXException, IOException {
        List<RSSItem> res = new ArrayList();
        Document doc = parseXmlToDoc(rssasxml);
        
        NodeList itemNodes = doc.getElementsByTagName("item");
        for (int i = 0; i < itemNodes.getLength(); i ++) {
            Node itemContent = itemNodes.item(i);            
            String title = getNodeChildTextByName(itemContent, "title");
            String link = getNodeChildTextByName(itemContent, "link");
            String descr = getNodeChildTextByName(itemContent, "description");
            res.add(new RSSItem(title, link, descr));
        }
        
        return res;
    }
    
    private Document parseXmlToDoc(String xml) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource source = new InputSource(new StringReader(xml));
        return builder.parse(source);
    }
    
    private String getNodeChildTextByName(Node parentNode, String name) {
        String res = null;
        NodeList childNodes = parentNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i ++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeName().equals(name)) {
                res = childNode.getTextContent();
                break;
            }
        }
        return res;
    }
    
}
