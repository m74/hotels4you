package ru.com.m74.hotels4you.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author mixam
 * @since 07.01.17 0:29
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Hotel {
    private long id;
    private String title;
    private String description;

    @XmlElement(name = "photo")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Photo> photos;

    @XmlElement(name = "room")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Room> rooms;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
