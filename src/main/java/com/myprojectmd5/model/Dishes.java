package com.myprojectmd5.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String img;
    private String description;
}
