package com.project.entity;

import com.project.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder //TODO: Bu sıkıntı çıkarmıyor düzelttim ama gıcıklık yaratabilir spring çalışırken bişeyler yazıo
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    private Long createDate;
    private Long updateDate;
    private EStatus status;
}
