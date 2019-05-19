package com.datasoft.dpdc.smartmetermiddleware.service;

import com.datasoft.dpdc.smartmetermiddleware.dao.RoleDao;
import com.datasoft.dpdc.smartmetermiddleware.dao.UserDao;
import com.datasoft.dpdc.smartmetermiddleware.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final User user = userDao.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User name: " + userName + " not found.");
        }
        final String roleName = roleDao.findRoleNameById(user.getRole().getId());
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
