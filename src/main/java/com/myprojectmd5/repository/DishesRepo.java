package com.myprojectmd5.repository;

import com.myprojectmd5.model.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepo extends JpaRepository<Dishes, Long> {
}
