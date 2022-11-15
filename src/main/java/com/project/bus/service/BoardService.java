package com.project.bus.service;

import com.project.bus.entity.Board;

import com.project.bus.entity.Bus;
import com.project.bus.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;



@Service

public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void write(Board board) {boardRepository.save(board);}

    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable); // SELECT * FROM 조건없이 테이블의 모든 튜플을 가져온다.
    }

    public Board boardView(Integer id) {

        return boardRepository.findByid(id); // id 값을 받아온다.
    }


    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }

//    @Transactional
//    public int updateView(Integer id) {
//        return boardRepository.updateView(id);
//    }

}
