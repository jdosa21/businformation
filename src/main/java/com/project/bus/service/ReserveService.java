package com.project.bus.service;

import com.project.bus.entity.Reserve;
import com.project.bus.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;
    public void ReserveCheck(Reserve reserve){
        reserveRepository.save(reserve);
    }

    public Page<Reserve> reserveList(Pageable pageable) {
        return reserveRepository.findAll(pageable);
    }

    // 예약 취소
    public void reserveDelete(Integer id) {

        reserveRepository.deleteById(id);
    }
}
