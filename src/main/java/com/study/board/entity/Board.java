package com.study.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter
@Entity
@Table(name = "board")
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    private Long boardId;

    // 제목
    @Column(nullable = false, length = 50)
    private String title;
    // 작성자
    @Column(nullable = false, length = 50)
    private String writer;
    // 내용
    @Column(nullable = false, length = 1000)
    private String contents;

}
