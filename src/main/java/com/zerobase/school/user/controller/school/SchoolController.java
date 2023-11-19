package com.zerobase.school.user.controller.school;

import com.zerobase.school.user.config.JwtAuthenticationProvider;
import com.zerobase.school.user.dto.SchoolDto;
import com.zerobase.school.user.dto.UserResponse;
import com.zerobase.school.user.service.school.SchoolOpenAPIService;
import com.zerobase.school.user.service.school.SchoolService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {
    private final JwtAuthenticationProvider provider;
    private final SchoolService schoolService;
    private final SchoolOpenAPIService schoolOpenAPIService;

    @GetMapping("/getSchoolInfo")
    public void getSchoolInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token)throws IOException, ParseException{
        //UserResponse userResponse = provider.getUserResponse(token);
        //비회원은 조회가 안됨
        schoolOpenAPIService.saveSchoolInfo();
    }

    @PostMapping("/getSchoolDetail")
    public ResponseEntity<SchoolDto> getSchoolDetail(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                    @RequestParam SchoolDto searchDto){

        //비회원은 조회가 안됨
        return ResponseEntity.ok(SchoolDto.from(schoolService.getSchoolDetail(searchDto)));
    }
}
