package com.zerobase.school.user.service.school;

import com.zerobase.school.user.domain.entity.School;
import com.zerobase.school.user.dto.SchoolDto;
import com.zerobase.school.user.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Transactional
    public void insertSchcoolInfo(List<SchoolDto> schcoolDtos) {
        for(SchoolDto dto : schcoolDtos){
            if(schoolRepository.existsBySchoolCode(dto.getSchoolCode())){
                break;
            }
            schoolRepository.save(School.builder()
                    .schoolCode(dto.getSchoolCode())
                    .name(dto.getName())
                    .schoolType(dto.getSchool_type())
                    .phone_number(dto.getPhone_number())
                    .anniversary(dto.getAnniversary())
                    .url(dto.getUrl())
                    .zip_code(dto.getZip_code())
                    .fax(dto.getFax())
                    .address(dto.getAddress())
                    .build());
        }
    }


    public SchoolDto changeSchoolDtos(JSONObject data){

        SchoolDto dto = SchoolDto.builder()
                            .schoolCode(data.get("SD_SCHUL_CODE").toString())
                            .name(data.get("SCHUL_NM").toString())
                            .address(data.get("ORG_RDNMA").toString())
                            .school_type(data.get("FOND_SC_NM").toString())
                            .phone_number(data.get("ORG_TELNO").toString())
                            .url(data.get("HMPG_ADRES").toString())
                            .zip_code(data.get("ORG_RDNZC").toString())
                            .anniversary(data.get("FOND_YMD").toString())
                            .fax(data.get("ORG_FAXNO").toString())
                            .build();

        return dto;
    }

    public School getSchoolDetail(SchoolDto searchDto){
        if(!searchDto.getSearchSchoolNm().isEmpty()){
            return schoolRepository.findByNameContaining(searchDto.getSearchSchoolNm()).get();
        }else if(!searchDto.getSearchDistrict().isEmpty()){
            return schoolRepository.findByAddressContaining(searchDto.getSearchDistrict()).get();
        }else if (!searchDto.getSchool_type().isEmpty()) {
            return schoolRepository.findBySchoolType(searchDto.getSchool_type()).get();
        }
        return new School();
    }

}
