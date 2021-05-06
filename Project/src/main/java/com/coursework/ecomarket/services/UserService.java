package com.coursework.ecomarket.services;

import com.coursework.ecomarket.models.User;

import java.util.List;

public interface UserService {

    public User findByEmail(String email);

    public void save(User user);

    public void update(User user);

    public List<User> findAllUser();

    public void deleteUser(long userId);
}

