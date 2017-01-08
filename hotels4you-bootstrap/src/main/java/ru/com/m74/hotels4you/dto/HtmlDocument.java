package ru.com.m74.hotels4you.dto;

import javax.xml.bind.annotation.*;
import java.util.HashMap;

/**
 * @author mixam
 * @since 05.01.17 23:35
 */
@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
public class HtmlDocument {

    private String title;

    private HashMap<String, Object> body = new HashMap<String, Object>();

    @XmlTransient
    private String stylesheet;

    @XmlAttribute
    private boolean anonymousUser;

    public HtmlDocument() {
//        anonymousUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
    }

    public String getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public void setBody(HashMap<String, Object> body) {
        this.body = body;
    }
}
