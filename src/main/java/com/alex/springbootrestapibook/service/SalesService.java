package com.alex.springbootrestapibook.service;

import com.alex.springbootrestapibook.entity.Sale;
import com.alex.springbootrestapibook.model.SalesModel;
import com.alex.springbootrestapibook.repository.SalesRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SalesService {
    @Autowired
    private SalesRepo salesRepo;
    private ModelMapper mapper;

    public void save(SalesModel salesModel) {
        Sale sale = mapper.map(salesModel, Sale.class);
        salesRepo.save(sale);
    }

    public List<SalesModel> getSalesByUserId(Long userId) {
        List<Sale> sales = salesRepo.findByUserId(userId);
        List<SalesModel> res = sales.stream().map(this::convetrToSaleModel).collect(Collectors.toList());
        return res;
    }

    public List<SalesModel> getSalesBetweenDate(String startDate, String endDate) {
        List<SalesModel> res = new ArrayList<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(startDate);
            Date parsedDate1 = dateFormat.parse(endDate);
            Timestamp start = new java.sql.Timestamp(parsedDate.getTime());
            Timestamp end = new java.sql.Timestamp(parsedDate1.getTime() + 8693000);
            List<Sale> sales = salesRepo.findByTimeBetween(start, end);
            res = sales.stream().map(this::convetrToSaleModel).collect(Collectors.toList());
        } catch (Exception e) {
            log.debug(e.toString());
        }
        return res;
    }

    public List<SalesModel> getSalesByUserIdBetweenDate(Long userId, String startDate, String endDate) {
        List<SalesModel> res = new ArrayList<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(startDate);
            Date parsedDate1 = dateFormat.parse(endDate);
            Timestamp start = new java.sql.Timestamp(parsedDate.getTime());
            Timestamp end = new java.sql.Timestamp(parsedDate1.getTime() + 8693000);
            List<Sale> sales = salesRepo.findByTimeBetweenAndUserId(start, end, userId);
            res = sales.stream().map(this::convetrToSaleModel).collect(Collectors.toList());
        } catch (Exception e) {
            log.debug(e.toString());
        }
        return res;
    }


    private SalesModel convetrToSaleModel(Sale sale) {
        return mapper.map(sale, SalesModel.class);
    }
}
