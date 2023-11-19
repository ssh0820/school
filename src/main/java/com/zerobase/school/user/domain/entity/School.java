package com.zerobase.school.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;
    @Column(name = "school_code", unique = true)
    private String schoolCode;
    private String name;
    private String schoolType;
    private String phone_number;
    private String url;
    private String zip_code;
    private String address;
    private String anniversary;
    private String fax;

    //private String key;
    private Long board_board_id;

}
