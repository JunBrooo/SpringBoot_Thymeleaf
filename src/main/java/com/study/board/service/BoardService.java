package com.study.board.service;

import com.study.board.DTO.BoardDTO;
import com.study.board.entity.Board;

import java.util.List;

public interface BoardService {

    void create(Board board);

    List<Board> getList();

    Board getBoardById(Long boardId);

    void deleteByBoardId(Long boardId);


    void update(Board board);
}
