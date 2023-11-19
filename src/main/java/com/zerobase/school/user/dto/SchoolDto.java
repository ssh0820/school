package com.zerobase.school.user.dto;

import com.zerobase.school.user.domain.entity.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SchoolDto {
    private Long school_id;
    private String schoolCode;
    private String name;
    private String school_type;
    private String phone_number;
    private String url;
    private String zip_code;
    private String address;
    private String anniversary;
    private String fax;

    //private String key;
    private Long board_board_id;

    //search selection
    private String searchDistrict;
    private String searchSchoolNm;
    private String searchType;

    public static SchoolDto from(School school){
        return SchoolDto.builder()
                .school_id(school.getSchoolId())
                .name(school.getName())
                .school_type(school.getSchoolType())
                .phone_number(school.getPhone_number())
                .url(school.getUrl())
                .zip_code(school.getZip_code())
                .address(school.getAddress())
                .fax(school.getFax())
                .build();
    }

}
