package com.myprojectmd5.controller;

import com.myprojectmd5.model.Dishes;
import com.myprojectmd5.service.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dishes")
public class DishesController {

    @Autowired
    DishesService dishesService;

    @GetMapping("")
    public List<Dishes> getDishList() {
        return dishesService.findAll();
    }

    @PostMapping("/add")
    public void createDish(@RequestBody Dishes dish) {
        dishesService.save(dish);
    }

    @GetMapping("/{id}")
    public Dishes findDishById(@PathVariable Long id) {
        return dishesService.findById(id).get();
    }

    @PutMapping("/edit/{id}")
    public void updateDish(@PathVariable Long id, @RequestBody Dishes dish) {
        dish.setId(id);
        dishesService.save(dish);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDish(@PathVariable Long id) {
        dishesService.delete(id);
    }
}
