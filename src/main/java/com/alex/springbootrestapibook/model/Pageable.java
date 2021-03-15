package com.alex.springbootrestapibook.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pageable<K> {
    private List<K> content;
    private Long page;
    private Long totalPage;
}
