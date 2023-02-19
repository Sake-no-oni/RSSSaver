package com.example.rsssaver.repos;

import com.example.rsssaver.entities.RSSItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andrii Filimonov
 */
public interface RSSItemRepo extends JpaRepository<RSSItem, Long> {
    
    RSSItem findByTitle(String title);
    
    boolean existsRSSItemByTitle(String title);
    
}
