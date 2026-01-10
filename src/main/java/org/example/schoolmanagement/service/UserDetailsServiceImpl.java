package org.example.schoolmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
