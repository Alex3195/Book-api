package com.alex.springbootrestapibook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersModel {
    private Long id;
    private String name;
    private String password;
}
