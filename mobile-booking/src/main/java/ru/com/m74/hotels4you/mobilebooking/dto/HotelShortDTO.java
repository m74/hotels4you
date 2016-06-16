package ru.com.m74.hotels4you.mobilebooking.dto;

import ru.com.m74.hotels4you.mobilebooking.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mixam
 * @since 09.06.16 13:45
 */
public class HotelShortDTO {
    private long id;
    private String title;
    private String annotation;
    private Double breakfast;
    private Integer rating;
    private String icon;

    public HotelShortDTO(ResultSet set) throws SQLException {
        id = set.getLong("id");
        title = set.getString("name_ru");
        breakfast = set.getDouble("breakfast");
        rating = set.getInt("category");
        annotation = Utilities.removeHtml(set.getString("annotation"));
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

    public Double getBreakfast() {
        return breakfast;
    }

    public Integer getRating() {
        return rating;
    }

    public String getIcon() {
        return icon;
    }


}
