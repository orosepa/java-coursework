package com.coursework.ecomarket.services;

import com.coursework.ecomarket.models.Product;
import com.coursework.ecomarket.models.User;
import com.coursework.ecomarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        user.setActive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        List<Product> productlist1 = user.getProductList();
        List<Product> productlist = (userRepository.findByEmail(user.getEmail())).getProductList();
        productlist1.addAll(productlist);
        user.setProductList(productlist1);
        userRepository.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

}

