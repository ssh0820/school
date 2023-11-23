package com.zerobase.school.board.dto;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CreateBoardResponse {
    @Id
    @GeneratedValue
    private Long bId;
    private String bUserName;
    private String bEmail;
    private String bTitle;
    private String bContents;
    @CreatedBy
    private LocalDateTime createTime = LocalDateTime.now();//[6]
}
