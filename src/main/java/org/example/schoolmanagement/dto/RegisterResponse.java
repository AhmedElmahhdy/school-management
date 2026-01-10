package org.example.schoolmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {
          private Integer userId;   // ID المستخدم الجديد
          private String message;   // رسالة العملية
    }

