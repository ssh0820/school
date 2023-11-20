package com.zerobase.school.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor
@Entity @Builder @Data
@AllArgsConstructor
public class Board {@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bId;
    @Column
    private String bUserName;
    @Column
    private String bEmail;
    @Column
    private String bTitle;
    @Column
    private String bContents;
    //@Column private LocalDateTime createTime=LocalDateTime.now();//[6]
    public void bModify(MakeBoard makeBoard) {
        this.bUserName=makeBoard.getBUserName();
        this.bEmail=makeBoard.getBEmail();
        this.bTitle=makeBoard.getBTitle();
        this.bContents=makeBoard.getBContents();
    }
}