package com.alex.springbootrestapibook.service;

import com.alex.springbootrestapibook.entity.Users;
import com.alex.springbootrestapibook.model.UsersModel;
import com.alex.springbootrestapibook.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;

    public List<UsersModel> getAllUsers() {
        List<Users> users = usersRepo.findAll();
        List<UsersModel> resUserList = new ArrayList<>();
        for (Users u : users) {
            UsersModel model = new UsersModel(u.getId(), u.getName(), u.getPassword());
            resUserList.add(model);
        }
        return resUserList;
    }

    public UsersModel getUSerById(Long id) {
        Users user = usersRepo.findById(id).get();
        UsersModel res = new UsersModel(user.getId(), user.getName(), user.getPassword());
        return res;
    }

    public UsersModel getUserByName(String name) {
        Users u = usersRepo.findByName(name);
        UsersModel result = new UsersModel(u.getId(), u.getName(), u.getPassword());
        return result;
    }

}
