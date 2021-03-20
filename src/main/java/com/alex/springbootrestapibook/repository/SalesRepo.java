package com.alex.springbootrestapibook.repository;

import com.alex.springbootrestapibook.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface SalesRepo extends JpaRepository<Sale, Long> {
    List<Sale> findByUserId(Long userId);

    List<Sale> findByTime(Timestamp times);

    List<Sale> findByBookId(Long bookId);

    List<Sale> findByTimeBetweenAndUserId(Timestamp start, Timestamp end, Long userId);

    List<Sale> findByTimeBetween(Timestamp start, Timestamp end);
}
