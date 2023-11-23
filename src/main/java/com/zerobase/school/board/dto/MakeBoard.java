package com.zerobase.school.board.dto;

import com.zerobase.school.board.domain.Board;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MakeBoard {
    @Id
    @GeneratedValue
    private String bUserName;
    private String bEmail;
    private String bTitle;
    private String bContents;

    public static Board bform(MakeBoard makeBoard) {
        Board board = Board.builder()
                .bUserName(makeBoard.bUserName)
                .bEmail(makeBoard.bEmail)
                .bTitle(makeBoard.bTitle)
                .bContents(makeBoard.bContents).build();
        return board;
    }
}