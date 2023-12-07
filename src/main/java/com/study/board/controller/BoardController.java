package com.study.board.controller;

import com.study.board.DTO.BoardDTO;
import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //C : 게시글 만들기
//    @GetMapping("/new")
//    public String boardForm(Model model){
    @GetMapping("/new")
    public String boardForm(@ModelAttribute Board board){
//        model.addAttribute("board", new Board());
        return "board/boardForm";
    }
    @PostMapping("/new")
    public String createBoard(@ModelAttribute("board") Board board){
        // boardForm에서 넘겨받은 정보 저장처리
        boardService.create(board);

        return "redirect:/board/"+board.getBoardId();
    }

    //R : 리스트 불러오기
    @GetMapping("/list")
    public String boardList(Model model){
        List<Board> list = boardService.getList();
        model.addAttribute("boardList",list);
        return "/board/list";
    }
    //R : 글 상세
    @GetMapping("/{boardId}")
    public String boardDetail(@PathVariable Long boardId, Model model){
        model.addAttribute("board", boardService.getBoardById(boardId));
        return "board/detail";
    }

    //U : 게시글 수정
    // 1. 수정할 게시글 한개 받아오기
    @GetMapping("/edit")
    public String editForm(Long boardId, Model model){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@ Edite boardId : {} " ,boardId);
        // 2. 받아온 게시글 정보 모델에 담아서 view페이지에 뿌려주기( readonlu 없애고 수정가능하도록 설정)
        model.addAttribute("board", boardService.getBoardById(boardId));
        return "board/editForm";
    }
    // 3. 수정 후 완료 버튼 클릭하면 해당 게시글의 boardId 가지고 넘어와서
    @PostMapping("/edit")
    // editBoard 란 이름으로 뷰페이지에서 넘겨준 덩어리를 board라는 이름으로 받아옴
    public String boardUpdate(@ModelAttribute("board") Board board){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@ Get boardId : {} " ,board.getBoardId());
        // 4. 해당 아이디로 정보 저장
        // ( 추후 변경필요가 잆음 : 지금 구현된 상황은 덮어쓰는 방식이고 변화를 감지해서 해당내용만 수정처리하는 방법으로 변경)
        // 엔티티로 모든 데이터 처리를 하고 있지만 추후에 dto를 사용하여 로직처리를 분리할 예정
        Board setBoard = boardService.getBoardById(board.getBoardId());
        setBoard.setTitle(board.getTitle());
        setBoard.setContents(board.getContents());
        boardService.update(setBoard);
        return "redirect:/board/" + board.getBoardId();
    }

    //D : 게시글 삭제
    @GetMapping("/del")
    public String boardDel(Long boardId){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@ Delete boardId : {} " ,boardId);
        boardService.deleteByBoardId(boardId);
        return "redirect:/board/list";
    }



}
