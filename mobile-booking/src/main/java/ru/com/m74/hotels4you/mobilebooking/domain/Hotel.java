package ru.com.m74.hotels4you.mobilebooking.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import org.hibernate.annotations.IndexColumn;

/**
 * @author mixam
 * @since 09.06.16 11:47
 */
@Entity
@Table(name = "hotels")
public class Hotel implements Serializable, Comparable<Hotel> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name_ru")
    private String title;

    @Column(name = "category")
    private Integer rating;

    /**
     * Аннотация (краткое описание)
     */
    @Column(columnDefinition = "text")
    private String annotation;

    /**
     * Полное описание (дополнительно к аннотации)
     */
    @Column(name = "description_ru", columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "hotel")
    // @OrderBy("sequence")
    @IndexColumn(name = "sequence", base = 0)
    private List<Room> rooms = new ArrayList<Room>();

    /**
     * Стоимость завтрака
     */
    private Double breakfast;

    @ManyToOne
    @JoinColumn(name = "city")
    private Region region;

    @Column(name = "comments_ru", columnDefinition = "text")
    private String priceComments;

    @Override
    public int compareTo(Hotel o) {
        return title.compareTo(o.title);
    }
}
