package com.zerobase.school.user.service;

import com.zerobase.school.user.domain.entity.Customer;
import com.zerobase.school.user.dto.SignUpForm;
import com.zerobase.school.user.exception.CustomException;
import com.zerobase.school.user.exception.ErrorCode;
import com.zerobase.school.user.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {
    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form){
       return customerRepository.save(Customer.from(form));
    }

    public boolean isEmailExist(String email){
       return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }

    @Transactional
    public void verifyEmail(String email, String code){
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));

        if (customer.isVerify()){
            throw new CustomException(ErrorCode.ALREADY_VERIFY);
        } else if(customer.getVerifycationCode().equals(code)){
            throw new CustomException(ErrorCode.WRONG_VERIFICATION);
        } else if(customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())){
            throw new CustomException(ErrorCode.EXPIRE_CODE);
        }
        customer.setVerify(true);
    }

    @Transactional
    public LocalDateTime ChangeCustomerValidateEmail(Long customerId, String verificationCode){
        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent()){
            Customer c = customer.get();
            c.setVerifycationCode(verificationCode);
            c.setVerifyExpiredAt(LocalDateTime.now());
            return c.getCreatedAt();
        }
        throw new CustomException(ErrorCode.NOT_FOUND_USER);
    }

}

