package com.zerobase.school.board;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @PostMapping("/create")
    public Board createBoard(@RequestBody MakeBoard makeBoard){
        return boardService.createBoard(makeBoard);}
    @GetMapping("/get/{bId}")
    public Board getBoard(@PathVariable Long bId){
        return boardService.getBoard(bId);}
    @PutMapping("/modify/{bId}")
    public Board modifyBoard(@PathVariable Long bId,
                             @RequestBody MakeBoard makeBoard){
        return boardService.modifyBoard(bId, makeBoard);}
    @DeleteMapping("/board/delete/{id}")
    public void boardDelete(@PathVariable Long bId){
    boardService.boardDelete(bId);}

    //CRUD
}