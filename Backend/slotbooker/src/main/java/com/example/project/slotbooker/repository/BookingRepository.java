package com.example.project.slotbooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.slotbooker.entity.SlotsEntity;

public interface BookingRepository extends JpaRepository<SlotsEntity,Long>{
	
	SlotsEntity findById(long id);
}