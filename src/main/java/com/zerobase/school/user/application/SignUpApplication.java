package com.zerobase.school.user.application;

import com.zerobase.school.user.client.MailgunClient;
import com.zerobase.school.user.domain.entity.Customer;
import com.zerobase.school.user.dto.SendMailForm;
import com.zerobase.school.user.dto.SignUpForm;
import com.zerobase.school.user.exception.CustomException;
import com.zerobase.school.user.exception.ErrorCode;
import com.zerobase.school.user.service.SignUpCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpApplication {
    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;

    public void customerVerify(String email, String code){
        signUpCustomerService.verifyEmail(email, code);
    }

    public String customerSignUp(SignUpForm form){
        if(signUpCustomerService.isEmailExist(form.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else {
            Customer c = signUpCustomerService.signUp(form);
            LocalDateTime now = LocalDateTime.now();

            String code = getRandomCode();

            SendMailForm mailForm = SendMailForm.builder()
                    .form("email@gmail.com")
                    .to(form.getEmail())
                    .subject("Verification Email!")
                    .text(getVerificationEmailBody(c.getEmail(), c.getName(), code))
                    .build();

            log.info("Send email result : "+mailgunClient.sendEmail(mailForm).getBody());

            signUpCustomerService.ChangeCustomerValidateEmail(c.getId(), code);

            return "회원 가입에 성공하였습니다.";
        }


    }

    private String getRandomCode(){
        return RandomStringUtils.random(10,true,true);
    }

    private String getVerificationEmailBody(String email, String name, String code){
        StringBuilder builder = new StringBuilder();
        return builder.append("Hello ").append(name).append("! Please Click Link for verification.\n\n")
                .append("http://localhost:8080/signup/verify/customer/verify?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();
    }


}
