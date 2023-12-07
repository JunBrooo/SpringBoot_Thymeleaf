package com.study.board.service;

import com.study.board.DTO.BoardDTO;
import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void create(Board board) {
        boardRepository.save(board);
    }

    @Override
    public List<Board> getList(){
        return boardRepository.findAll();
    }

    @Override
    public Board getBoardById(Long boardId){
        return boardRepository.findByBoardId(boardId);
    }

    @Override
    public void deleteByBoardId(Long boardId){
        boardRepository.deleteById(boardId);
    }

    @Override
    public void update(Board board){
        boardRepository.save(board);
    }



}
