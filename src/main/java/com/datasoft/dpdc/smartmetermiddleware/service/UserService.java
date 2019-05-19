package com.datasoft.dpdc.smartmetermiddleware.service;

import com.datasoft.dpdc.smartmetermiddleware.dao.UserDao;
import com.datasoft.dpdc.smartmetermiddleware.model.Address;
import com.datasoft.dpdc.smartmetermiddleware.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by rayhan on 9/19/18.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean addUser(User user) {
        if (user.getAddress() == null) {
            Address address = new Address();
            address.setId(1);
            user.setAddress(address);
        }
        user.setRegistrationDate(new Date());
        user = userDao.add(user);
        if (user.getId() > 0) {
            return true;
        }
        return false;
    }

    public List<User> findAllUser(){
        return userDao.findAll();
    }

    public User findUserById(int userId) {
        return userDao.findUser(userId);
    }
}
