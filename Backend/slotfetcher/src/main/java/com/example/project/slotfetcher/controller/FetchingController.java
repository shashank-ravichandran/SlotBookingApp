package com.example.project.slotfetcher.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.slotfetcher.entity.SlotsEntity;
import com.example.project.slotfetcher.service.FetchingService;

@RestController
@RequestMapping("/fetch")
public class FetchingController {
	
	@Autowired
	private FetchingService fetchingService;
	
	@GetMapping("/bookedslots/{userId}")
	public List<SlotsEntity> getBookedSlots(@PathVariable String userId) {
		return fetchingService.getBookedSlots(String.valueOf(userId));
	}
	
	@GetMapping("/availableslots/{date}/{timezone}")
	public int[] getSlotsByDate(@PathVariable String date, @PathVariable String timezone) {
		int[] result = null;
		try {
			result = fetchingService.getSlotsByDateAndTimezone(new SimpleDateFormat("yyyy-MM-dd").parse(date), timezone);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping("/slotbyid/{slotid}")
	public SlotsEntity getSlotById(@PathVariable String slotid) {
		return fetchingService.getSlotById(Integer.parseInt(slotid));
	}
}
