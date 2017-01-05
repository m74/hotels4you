package ru.com.m74.hotels4you.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author mixam
 * @since 05.01.17 9:10
 */
//@XmlAccessorType(XmlAccessType.FIELD)
//@JacksonXmlRootElement(localName = "hotel")
@XmlRootElement(name = "hotel")
public class Hotel {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
