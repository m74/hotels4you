package ru.com.m74.hotels4you.mobilebooking.dto;

import ru.com.m74.hotels4you.mobilebooking.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mixam
 * @since 09.06.16 13:45
 */
public class HotelFullDTO extends HotelShortDTO {
    private String region;
    private Integer category;
    private String phones;
    private String description;

    private List<RoomDTO> rooms = new ArrayList<>();
    private List<String> photos = new ArrayList<>();

    public HotelFullDTO(ResultSet set) throws SQLException {
        super(set);
        category = set.getInt("category");
        phones = set.getString("phones");
        description = Utilities.removeHtml(set.getString("description_ru"));
    }

    public List getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
