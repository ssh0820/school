package com.zerobase.school.user.controller;

import com.zerobase.school.user.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {
    private final CustomerRepository customerRepository;
}
