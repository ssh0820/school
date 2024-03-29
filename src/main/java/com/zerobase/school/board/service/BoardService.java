package com.zerobase.school.board.service;

import com.zerobase.school.board.repository.BoardRepository;
import com.zerobase.school.board.dto.MakeBoard;
import com.zerobase.school.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public Board createBoard(MakeBoard makeBoard) {
        Board board = MakeBoard.bform(makeBoard);
        return boardRepository.save(board);
    }

    @Transactional
    public Board getBoard(Long bId) {
        return boardRepository.findById(bId).get();
    }

    public Board modifyBoard(Long bId, MakeBoard makeBoard) {
        Board saved = boardRepository.findBybId(bId);
        saved.bModify(makeBoard);
        return saved;
    }

    public void boardDelete(Long bId) {
        boardRepository.deleteBybId(bId);
    }
}