package com.zerobase.school.board.boardfile;

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
    private int id;
    @Column
    private String userName;
    @Column
    private String email;
    @Column
    private String title;
    @Column
    private String contents;
    @Column
    private LocalDateTime createTime;
    @Column
    private LocalDateTime modifyTime;
    @Column
    private LocalDateTime deleteTime;
}