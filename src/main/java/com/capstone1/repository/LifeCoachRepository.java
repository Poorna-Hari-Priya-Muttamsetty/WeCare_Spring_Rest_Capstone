package com.capstone1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone1.entity.LifeCoach;

public interface LifeCoachRepository extends JpaRepository<LifeCoach, Integer>{

    LifeCoach findByName(String name);
}
