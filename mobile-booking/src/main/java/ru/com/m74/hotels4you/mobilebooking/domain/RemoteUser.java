package ru.com.m74.hotels4you.mobilebooking.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author mixam
 * @since 09.06.16 11:40
 */
@Entity
public class RemoteUser implements Serializable {
    @Id
    private String id;

    private String nic;
    private String name, surname;
    private String password;

}
