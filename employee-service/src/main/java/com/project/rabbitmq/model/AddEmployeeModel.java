package com.project.rabbitmq.model;


import lombok.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeModel implements Serializable {
    private String email;
    private String name;
    private String surname;

}
