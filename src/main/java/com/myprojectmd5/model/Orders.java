package com.myprojectmd5.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date date;
    private double totalPrice;
    private String customer;
    private String phone;
    private String address;

    @ManyToMany
    List<Dishes> dishes;

    public Orders() {
    }

}
