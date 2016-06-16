package ru.com.m74.hotels4you.mobilebooking.dto;

import ru.com.m74.hotels4you.mobilebooking.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author mixam
 * @since 13.06.16 20:19
 */
public class RoomDTO {
    private long id;
    private String title;
    private String annotation;
    private String description;
    private String icon;
    private List photos;

    public RoomDTO(ResultSet set) throws SQLException {
        id = set.getLong("id");
        title = set.getString("title_ru");
        annotation = Utilities.removeHtml(set.getString("annotation"));
        description = Utilities.removeHtml(set.getString("description_ru"));
        icon = Utilities.getImageFilename(set.getString("imageId"), set.getString("imageContentType"));
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAnnotation() {
        return annotation;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public void setPhotos(List photos) {
        this.photos = photos;
    }

    public List getPhotos() {
        return photos;
    }
}