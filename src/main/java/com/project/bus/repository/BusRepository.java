package com.project.bus.repository;

import com.project.bus.entity.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // <extends> JpaRepository <클래스 이름, id(기본 키)의 자료형>
public interface BusRepository extends JpaRepository<Bus, Integer> {


    Page<Bus> findByRouteContaining(String searchKeyword, Pageable pageable);
    // return 타입은 Bus 객체가 담긴 Page
    // Containing 컬럼에서 Route(버스노선) 키워드가 포함된 것을 찾겠다.
    Bus findByid(Integer id);
}
