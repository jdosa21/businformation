package com.project.bus.repository;

import com.project.bus.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
}
