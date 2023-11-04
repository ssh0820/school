package com.zerobase.school.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SendMailForm {
    private String form;
    private String to;
    private String subject;
    private String text;
}
