package com.project.bus.repository;

import com.project.bus.entity.Board;
import com.project.bus.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findByid(Integer id);

//    @Modifying
//    @Query("update Board p set p.view = p.view + 1 where p.id = :id")
//    int updateView(Integer id);
}

