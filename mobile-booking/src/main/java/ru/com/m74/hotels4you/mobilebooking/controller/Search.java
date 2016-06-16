package ru.com.m74.hotels4you.mobilebooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import ru.com.m74.hotels4you.mobilebooking.Utilities;
import ru.com.m74.hotels4you.mobilebooking.dto.HotelFullDTO;
import ru.com.m74.hotels4you.mobilebooking.dto.HotelShortDTO;
import ru.com.m74.hotels4you.mobilebooking.dto.RoomDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author mixam
 * @since 08.06.16 23:29
 */
@RestController
@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=utf-8")
public class Search {

    @Autowired
    private NamedParameterJdbcTemplate sql;

    @Autowired
    private JdbcTemplate jdbc;

    @RequestMapping(path = "/")
    public List region(@RequestParam Map<String, String> params) throws SQLException, ClassNotFoundException {
        if (params.get("query") == null) params.put("query", "");

        return sql.queryForList(
                "SELECT id, name_ru AS title FROM cities " +
                        "WHERE name_ru LIKE concat(:query,'%')" +
                        "ORDER BY prior DESC, title", params);
    }

    @RequestMapping(path = "/region/{regionId}")
    public List hotels(@PathVariable String regionId, @RequestParam Map<String, String> params) throws SQLException, ClassNotFoundException {
        if (params.get("query") == null) params.put("query", "");
        params.put("regionId", regionId);
        return sql.query(
                "SELECT hotels.*, img.id AS imageId, content_type AS imageContentType " +
                        "FROM hotels " +
                        "JOIN images AS img ON(hotels.gallery_id=img.gallery_id AND img.DTYPE='Image' AND img.sequence=0) " +
                        "WHERE city=:regionId AND hotels.is_active=1 AND name_ru LIKE concat(:query,'%')" +
                        "GROUP BY hotels.id " +
                        "ORDER BY hotels.name_ru",
                params, (resultSet, i) -> new HotelShortDTO(resultSet));
    }

    @RequestMapping(path = "/search")
    public List allHotels(@RequestParam Map<String, String> params) throws SQLException, ClassNotFoundException {
        if (params.get("query") == null) params.put("query", "");
        return sql.query(
                "SELECT hotels.*, img.id AS imageId, content_type AS imageContentType, cities.name_ru AS region, city AS regionId " +
                        "FROM hotels " +
                        "LEFT JOIN images AS img ON(hotels.gallery_id=img.gallery_id AND img.DTYPE='Image' AND img.sequence=0) " +
                        "JOIN cities ON(city=cities.id) " +
                        "WHERE hotels.is_active=1 AND hotels.name_ru LIKE concat(:query,'%') " +
                        "GROUP BY hotels.id " +
                        "ORDER BY hotels.name_ru",
                params, (resultSet, i) -> new HotelShortDTO(resultSet));
    }

    @RequestMapping(path = "/hotel/{hotelId}")
    public HotelFullDTO rooms(@PathVariable String hotelId, @RequestParam Map<String, String> params) throws SQLException, ClassNotFoundException {
        params.put("hotelId", hotelId);
        return jdbc.queryForObject(
                "SELECT hotels.*, img.id AS imageId, content_type AS imageContentType, cities.name_ru AS region, city AS regionId " +
                        "FROM hotels " +
                        "LEFT JOIN images AS img ON(hotels.gallery_id=img.gallery_id AND img.DTYPE='Image' AND img.sequence=0) " +
                        "JOIN cities ON(city=cities.id) " +
                        "WHERE hotels.id=? " +
                        "GROUP BY hotels.id",
                (resultSet, i) -> {
                    HotelFullDTO hotel = new HotelFullDTO(resultSet);
                    hotel.setPhotos(jdbc.query(
                            "SELECT img.id, img.content_type FROM images img " +
                                    "JOIN hotels hotel ON(hotel.gallery_id=img.gallery_id AND img.sequence>0)" +
                                    "WHERE hotel.id=? " +
                                    "ORDER BY img.sequence",
                            (rs, i2) -> {
                                return Utilities.getImageFilename(
                                        rs.getString("id"),
                                        rs.getString("content_type"));
                            }, hotelId));
                    hotel.setRooms(jdbc.query(
                            "SELECT room.id, " +
                                    "room.title_ru, " +
                                    "room.annotation, " +
                                    "room.description_ru, " +
                                    "img.id AS imageId, " +
                                    "content_type AS imageContentType " +
                                    "FROM hotel_rooms room " +
                                    "LEFT JOIN images AS img ON(room.gallery_id=img.gallery_id AND img.sequence=0) " +
                                    "WHERE room.is_active=1 AND room.hotels_id=? " +
                                    "GROUP BY room.id",
                            (rs, ri) -> {
                                RoomDTO room = new RoomDTO(rs);
                                room.setPhotos(jdbc.query(
                                        "SELECT img.id, img.content_type FROM images img " +
                                                "JOIN hotel_rooms room ON(room.gallery_id=img.gallery_id AND img.sequence>0)" +
                                                "WHERE room.id=? " +
                                                "ORDER BY img.sequence",
                                        (prs, pi) -> {
                                            return Utilities.getImageFilename(
                                                    prs.getString("id"),
                                                    prs.getString("content_type"));
                                        },
                                        room.getId()));
                                return room;
                            },
                            hotelId));
                    return hotel;
                },
                hotelId);
    }
}
