package ru.com.m74.hotels4you.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.com.m74.hotels4you.Utilities;
import ru.com.m74.hotels4you.dto.*;

/**
 * @author mixam
 * @since 04.01.17 10:24
 */
@Controller
public class CatalogController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @RequestMapping("find")
    public HtmlDocument index() {

        HtmlDocument document = new HtmlDocument();
        document.setStylesheet("/xsl/catalog/root.xsl");

        document.getBody().put("region", jdbcTemplate.queryForList(
                "select id, name_ru as name from cities"));

        return document;
    }

    @RequestMapping("region/{id}")
    public HtmlDocument region(@PathVariable String id) {
        HtmlDocument document = new HtmlDocument();
        document.setTitle(jdbcTemplate.queryForObject("select name_ru from cities where id=?", String.class, id));
        document.setStylesheet("/xsl/catalog/region.xsl");
        document.getBody().put("hotel", jdbcTemplate.query(
                "select " +
                        " hotels.id,\n" +
                        " hotels.name_ru as title,\n" +
                        " annotation,\n" +
                        " images.content_type as iconType,\n" +
                        " images.id as iconId\n" +
                        "from hotels\n" +
                        " left join images on(hotel_id=hotels.id and sequence=0)\n" +
                        "where city=? group by hotels.id LIMIT 0, 10", (rs, i) -> {
                    HotelSmallDescription hotel = new HotelSmallDescription();
                    hotel.setId(rs.getLong("id"));
                    hotel.setTitle(rs.getString("title"));
                    hotel.setIconUrl(Photo.getIconUrl(rs.getString("iconId"), rs.getString("iconType"), "320x240"));
                    hotel.setAnnotation(Utilities.removeHtml(rs.getString("annotation")));
                    return hotel;
                }, id));
        return document;
    }

    @RequestMapping("hotel/{id}")
    public HtmlDocument hotel(@PathVariable String id) {
        HtmlDocument document = new HtmlDocument();
        document.setStylesheet("/xsl/catalog/hotel.xsl");
        document.getBody().put("hotel", jdbcTemplate.query(
                "select " +
                        "id," +
                        "name_ru as title," +
                        "description_ru as description" +
                        " from hotels" +
                        " where id=?",
                (rs, i) -> {
                    Hotel hotel = new Hotel();
                    hotel.setTitle(rs.getString("title"));
                    hotel.setDescription(rs.getString("description"));
                    hotel.setId(rs.getLong("id"));

                    hotel.setPhotos(
                            jdbcTemplate.query(
                                    "select id, content_type as type, title" +
                                            " from images" +
                                            " where hotel_id=?", (rs1, i1) -> {
                                        Photo photo = new Photo();
                                        photo.setTitle(rs1.getString("title"));
                                        photo.setFilename(rs1.getString("id"), rs1.getString("type"));
                                        return photo;
                                    }, hotel.getId())
                    );


                    hotel.setRooms(jdbcTemplate.query("select " +
                            "hotel_rooms.id," +
                            "hotel_rooms.title_ru as title," +
                            "hotel_rooms.description_ru as description," +
                            "images.content_type as iconType," +
                            "images.id as iconId" +
                            " from hotel_rooms" +
                            " left join images on(images.gallery_id=hotel_rooms.gallery_id and images.sequence=0)" +
                            " where hotel_rooms.hotels_id=? and hotel_rooms.is_active=1", (rs1, i1) -> {
                        Room room = new Room();
                        room.setId(rs1.getLong("id"));
                        room.setTitle(rs1.getString("title"));
                        room.setDescription(rs1.getString("description"));
                        room.setIconUrl(
                                Photo.getIconUrl(rs1.getString("iconId"), rs1.getString("iconType"), "320x240"));
                        return room;
                    }, hotel.getId()));

                    return hotel;
                },
                id));

        return document;
    }

    @RequestMapping("room/{id}")
    public HtmlDocument room(@PathVariable String id) {
        HotelSmallDescription hotel = new HotelSmallDescription();
        hotel.setTitle("Красные ворота");

        HtmlDocument document = new HtmlDocument();
        document.setStylesheet("/xsl/catalog/room.xsl");
        document.getBody().put("hotel", hotel);

        return document;
    }
}
