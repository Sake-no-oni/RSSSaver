package com.example.rsssaver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Andrii Filimonov
 */

@Entity
@Table(name = "rssitems")
public class RSSItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "title", unique = true, length = 500)
    private String title;
    
    @Column(name = "link", length = 1000)
    private String link;
    
    @Column(name = "descr", length = 5000)
    private String descr;
    
    public RSSItem() {}
    
    public RSSItem(String title, String link, String descr) {
        this.title = title;
        this.link  = link;
        this.descr = descr;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescr() {
        return descr;
    }
    
}
