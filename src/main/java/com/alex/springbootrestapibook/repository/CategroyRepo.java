package com.alex.springbootrestapibook.repository;

import com.alex.springbootrestapibook.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategroyRepo extends JpaRepository<Category, Long> {
}
