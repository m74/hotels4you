package ru.com.m74.hotels4you.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Краткое описание гостиницы (в списке)
 *
 * @author mixam
 * @since 05.01.17 9:10
 */
//@JacksonXmlRootElement(localName = "hotel")
@XmlRootElement(name = "hotel")
//@XmlAccessorType(XmlAccessType.FIELD)
public class HotelSmallDescription {

    private long id;

    private String title;

    private String annotation;

    private String iconUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }


}
