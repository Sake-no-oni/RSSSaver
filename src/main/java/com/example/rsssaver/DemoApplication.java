package com.example.rsssaver;

import com.example.rsssaver.handlers.RSSHanlder;
import com.example.rsssaver.utils.RSSItemsDecoder;
import com.example.rsssaver.utils.WebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    
        // --- Beans declaration ---
    
        @Bean
        public WebClient webClient() {
            return new WebClient();
        }
        
        @Bean
        public RSSItemsDecoder rssItemsDecoder() {
            return new RSSItemsDecoder();
        }
        
        @Bean
        public RSSHanlder rssHanlder() {
            return new RSSHanlder();
        }
        
        // --- Starting the app ---

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
