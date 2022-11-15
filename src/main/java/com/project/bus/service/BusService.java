package com.project.bus.service;

import com.project.bus.entity.Bus;
import com.project.bus.repository.BoardRepository;
import com.project.bus.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service // 요청에 대해서 어떤 처리들을 할 것인가?
public class BusService {
    @Autowired // 생성자를 자동으로
    private BusRepository busRepository; // Autowired를 생성하면 알아서 객체를 받아준다

    // 글 작성 처리
    public void write(Bus bus) {
        busRepository.save(bus);
    } // Bus 엔티티 저장


    // 게시글 리스트 처리
    public Page<Bus> busList(Pageable pageable) {

        return busRepository.findAll(pageable); // SELECT * FROM 조건없이 테이블의 모든 튜플을 가져온다.
    }

    // 특정 게시글 불러오기
    public Bus busView(Integer id) {

        return busRepository.findByid(id); // id 값을 받아온다.
    }

    public Page<Bus> busSearchList(String searchKeyword, Pageable pageable) {
        return busRepository.findByRouteContaining(searchKeyword, pageable);
    }

    // 특정 게시글 삭제
    public void busDelete(Integer id) {

        busRepository.deleteById(id);
    }


}
