package com.example.project.slotfetcher.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.slotfetcher.entity.SlotsEntity;

public interface FetchingRepository extends JpaRepository<SlotsEntity, Long>{
	
	List<SlotsEntity> findByUserId(String userId);
	
	SlotsEntity findById(long id);
	
	@Query("SELECT s from SlotsEntity s WHERE DATEDIFF(?1, s.date) = 0 AND s.timeZone = ?2")
	List<SlotsEntity> findByDateAndTimeZone(Date date, String timeZone);
}
