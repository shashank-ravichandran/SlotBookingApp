package com.example.project.slotfetcher.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.slotfetcher.entity.SlotsEntity;
import com.example.project.slotfetcher.repository.FetchingRepository;

@Service
public class FetchingService {
	
	@Autowired
	FetchingRepository fetchingRepository;
	
	public List<SlotsEntity> getBookedSlots(String userId) {
		List<SlotsEntity> result = fetchingRepository.findByUserId(userId);
		if(result.size() > 0)
			return result;
		else 
			return new ArrayList<SlotsEntity>();
	}
	
	public int[] getSlotsByDateAndTimezone(Date date, String timezone) {
		int[] freeSlots = new int[] {10,10,10,10};
		List<SlotsEntity> result = fetchingRepository.findByDateAndTimeZone(date, timezone);
		result.forEach((slot) -> {freeSlots[slot.getSlot()] -=1;});
		return freeSlots;
	}
	
	public SlotsEntity getSlotById(long slotid) {
		return fetchingRepository.findById(slotid);
	}
}
