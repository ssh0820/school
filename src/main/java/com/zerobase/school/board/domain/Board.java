package com.zerobase.school.board.domain;

import com.zerobase.school.board.dto.MakeBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Builder
@Data
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bId;
    @Column
    private String bUserName;
    @Column
    private String bEmail;
    @Column
    private String bTitle;
    @Column
    private String bContents;

    public void bModify(MakeBoard makeBoard) {
        this.bUserName = makeBoard.getBUserName();
        this.bEmail = makeBoard.getBEmail();
        this.bTitle = makeBoard.getBTitle();
        this.bContents = makeBoard.getBContents();
    }
}