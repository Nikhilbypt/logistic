package com.logistic.config;


import com.logistic.enums.RoleType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class EcommerceUser {

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String userName;

    private String userId;
}
