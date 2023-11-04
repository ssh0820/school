package com.zerobase.school.user.application;

import com.zerobase.school.user.config.JwtAuthenticationProvider;
import com.zerobase.school.user.domain.UserType;
import com.zerobase.school.user.domain.entity.Customer;
import com.zerobase.school.user.dto.SignInForm;
import com.zerobase.school.user.exception.CustomException;
import com.zerobase.school.user.exception.ErrorCode;
import com.zerobase.school.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;

    public String customerLoginToken(SignInForm form){
        //1. 로그인 가능 여부
        Customer customer = customerService.findValidCustomer(form.getEmail(),form.getPassword())
                .orElseThrow(()-> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        //2. 토큰을 발행
        //3. 토큰을 response 한다.
        return provider.createToken(customer.getEmail(),customer.getId(), customer.getUserType());
    }
}
