package com.zerobase.school.user.controller;

import com.zerobase.school.user.config.JwtAuthenticationProvider;
import com.zerobase.school.user.domain.entity.Customer;
import com.zerobase.school.user.dto.CustomerDto;
import com.zerobase.school.user.dto.UserResponse;
import com.zerobase.school.user.exception.CustomException;
import com.zerobase.school.user.exception.ErrorCode;
import com.zerobase.school.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;

    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token){
        UserResponse userResponse = provider.getUserResponse(token);

        Customer customer = customerService.findByIdAndEmail(userResponse.getId(), userResponse.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        return ResponseEntity.ok(CustomerDto.from(customer));
    }

}
