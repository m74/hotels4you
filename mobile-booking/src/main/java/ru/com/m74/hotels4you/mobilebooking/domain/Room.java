package ru.com.m74.hotels4you.mobilebooking.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mixam
 * @since 09.06.16 11:59
 */
@Entity
@Table(name = "hotel_rooms")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotels_id")
    private Hotel hotel;

    @Column(name = "title_ru")
    private String title;

    /**
     * Аннотация (короткое описание)
     */
    private String annotation;

    /**
     * Полное описание (дополняет аннотацию)
     */
    @Column(name = "description_ru", columnDefinition = "text")
    private String description;
    /**
     * Кол-во одноместных кроватей
     */
    private int singleBads = 0;

    /**
     * Кол-во двухместных кроватей
     */
    private int doubleBads = 0;

    /**
     * Возможность установки дополнительной кровати
     */
    private boolean extraBad = false;

    /**
     * Возможно ли подсиление в номер (для хостелов)
     */
    private boolean shares = false;

    /**
     * Завтрак входит в стоимость
     *
     * @see Hotel#getBreakfast()
     */
    private boolean includeBreakfast = false;
}
