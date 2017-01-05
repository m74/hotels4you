package ru.com.m74.hotels4you.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.com.m74.hotels4you.dto.Hotel;
import ru.com.m74.hotels4you.dto.HtmlDocument;

/**
 * @author mixam
 * @since 04.01.17 10:24
 */
@Controller
public class CatalogController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("find")
    public HtmlDocument index() {

        HtmlDocument document = new HtmlDocument();
        document.setStylesheet("/xsl/catalog/root.xsl");

        document.getBody().put("region", jdbcTemplate.queryForList("select * from cities"));

        return document;
    }

    @RequestMapping("region/{id}")
    public HtmlDocument region(@PathVariable String id) {
        Hotel hotel = new Hotel();
        hotel.setTitle("Красные ворота");

        HtmlDocument document = new HtmlDocument();
        document.setStylesheet("/xsl/catalog/region.xsl");
        document.getBody().put("hotel", hotel);

        return document;
    }

    @RequestMapping("hotel/{id}")
    public HtmlDocument hotel(@PathVariable String id) {
        Hotel hotel = new Hotel();
        hotel.setTitle("Красные ворота");

        HtmlDocument document = new HtmlDocument();
        document.setStylesheet("/xsl/catalog/hotel.xsl");
        document.getBody().put("hotel", hotel);

        return document;
    }

    @RequestMapping("room/{id}")
    public HtmlDocument room(@PathVariable String id) {
        Hotel hotel = new Hotel();
        hotel.setTitle("Красные ворота");

        HtmlDocument document = new HtmlDocument();
        document.setStylesheet("/xsl/catalog/room.xsl");
        document.getBody().put("hotel", hotel);

        return document;
    }
}
