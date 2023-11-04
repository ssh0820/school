package com.zerobase.school.user.filter;

import com.zerobase.school.user.config.JwtAuthenticationProvider;
import com.zerobase.school.user.dto.UserResponse;
import com.zerobase.school.user.service.CustomerService;
import lombok.RequiredArgsConstructor;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/customer/*")
@RequiredArgsConstructor
public class CustomerFilter implements Filter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerService customerService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTH-TOKEN");
        if(!jwtAuthenticationProvider.validateToken(token)){
            throw new ServletException("Invalid Access");
        }
        UserResponse vo = jwtAuthenticationProvider.getUserResponse(token);
        customerService.findByIdAndEmail(vo.getId(),vo.getEmail())
                .orElseThrow(()-> new SecurityException("Invalid access"));
        chain.doFilter(request, response);
    }
}
