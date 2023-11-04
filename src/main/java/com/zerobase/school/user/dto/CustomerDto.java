package com.zerobase.school.user.dto;


import com.zerobase.school.user.domain.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String email;

    public static CustomerDto from(Customer customer){
        return new CustomerDto(customer.getId(), customer.getEmail());
    }
}
