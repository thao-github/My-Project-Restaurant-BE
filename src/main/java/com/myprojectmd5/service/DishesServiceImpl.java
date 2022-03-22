package com.myprojectmd5.service;

import com.myprojectmd5.model.Dishes;
import com.myprojectmd5.repository.DishesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishesServiceImpl implements DishesService {
    @Autowired
    DishesRepo dishesRepo;

    @Override
    public List<Dishes> findAll() {
        return dishesRepo.findAll();
    }

    @Override
    public Optional<Dishes> findById(Long id) {
        return dishesRepo.findById(id);
    }

    @Override
    public void save(Dishes dish) {
        dishesRepo.save(dish);
    }

    @Override
    public void delete(Long id) {
        dishesRepo.deleteById(id);
    }
}
