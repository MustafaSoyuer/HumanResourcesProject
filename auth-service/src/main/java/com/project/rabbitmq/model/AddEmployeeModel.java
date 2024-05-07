package com.project.rabbitmq.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class AddEmployeeModel implements Serializable {
    private String email;
    private String password;
    
}
