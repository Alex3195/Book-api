package com.alex.springbootrestapibook.repository;

import com.alex.springbootrestapibook.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepo extends JpaRepository<Users,Long> {
    Users findByName(String name);

}
