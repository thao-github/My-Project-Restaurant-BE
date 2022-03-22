package com.myprojectmd5.service;

import com.myprojectmd5.model.Dishes;

import java.util.List;
import java.util.Optional;

public interface DishesService {
    List<Dishes> findAll();

    Optional<Dishes> findById(Long id);

    void save(Dishes dish);

    void delete(Long id);
}
