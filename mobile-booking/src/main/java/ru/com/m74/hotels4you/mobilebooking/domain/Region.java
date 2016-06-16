package ru.com.m74.hotels4you.mobilebooking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author mixam
 * @since 09.06.16 11:56
 */
@Entity
@Table(name = "cities")
public class Region implements Serializable {
    @Id
    private String id;

    // @Column(unique = true)
    // private String code;

    @Column(name = "name_ru")
    private String title;

    @Column(name = "name_ru_rp")
    private String hotelsTitle;

    @Column(name = "prior")
    private Integer priority = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHotelsTitle() {
        return hotelsTitle;
    }

    public void setHotelsTitle(String hotelsTitle) {
        this.hotelsTitle = hotelsTitle;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
