package org.example.schoolmanagement.service;

import org.example.schoolmanagement.dao.UserDao;
import org.example.schoolmanagement.dto.AuthRequest;
import org.example.schoolmanagement.dto.AuthResponse;
import org.example.schoolmanagement.model.User;
import org.example.schoolmanagement.utils.BusinessException;
import org.example.schoolmanagement.utils.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
     private final JwtService jwtService;
     private final UserDao userDao;
      private final PasswordEncoder passwordEncoder;
      public AuthResponse login(AuthRequest request) {
        User user = userDao.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new BusinessException(404,"User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException(404,"Invalid password");
        }

        String token = jwtService.generateToken(
                Map.of("role", user.getRole().name()),
                user.getUsername()
        );

        return new AuthResponse(token);
    }
}
