package com.project.entity;

import com.project.utility.enums.EEquipmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_equipments")
public class Equipments extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long employeeId;
    EEquipmentType equipmentType;
    Long authId;
    Long managerId;
    String document;
    Long receivingDate;
    Long returningDate;
    //TODO: Buraya equipment status eklersek karışabilir
    // yukarıdaki isimlerle bence eklemeyelim :D

}
