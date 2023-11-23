package com.zerobase.school.security.OAuth2;

import com.zerobase.school.user.domain.entity.Customer;
import com.zerobase.school.user.exception.CustomException;
import com.zerobase.school.user.exception.ErrorCode;
import com.zerobase.school.user.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2Service implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String name){

        Customer customer = customerRepository.findByName(name)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));

        return new OAuth2Details(customer);

    }
}
