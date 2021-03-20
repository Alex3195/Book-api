package com.alex.springbootrestapibook.repository;

import com.alex.springbootrestapibook.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepo extends JpaRepository<Language,Long> {
}
