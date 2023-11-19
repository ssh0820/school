package com.zerobase.school.board.boardfile;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController @RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @DeleteMapping("/board/delete/{id}")
    public void boardDelete(@PathVariable Long id){
    boardService.boardDelete(id);}

    //CRUD
}