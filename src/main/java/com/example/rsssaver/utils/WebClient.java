package com.example.rsssaver.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Andrii Filimonov
 */
public class WebClient {
    
    public Map<String, String> doGet(String urlAsStr) throws MalformedURLException, IOException {        
        String respStr = null;        
        URL url = new URL(urlAsStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int respCode = connection.getResponseCode();
        if (respCode == 200) {
            StringBuilder respBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    respBuilder.append(inputLine);
                }
            }
            respStr = respBuilder.toString();
        }
        
        Map<String, String> res = new HashMap<>();
        res.put("resp", respStr);
        res.put("respCode", String.valueOf(respCode));
        return res;
    }
    
}
