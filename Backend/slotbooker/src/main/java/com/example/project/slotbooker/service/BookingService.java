package com.example.project.slotbooker.service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.project.slotbooker.entity.SlotsEntity;
import com.example.project.slotbooker.repository.BookingRepository;

@Service
public class BookingService {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Lazy
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	public long findDiffInHours(SlotsEntity slot) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat();
		df.setTimeZone(TimeZone.getTimeZone(slot.getTimeZone()));
		String localTime = df.format(new Date());
		
		SimpleDateFormat df_2 = new SimpleDateFormat();
		
        long diffInHours = (slot.getDate().getTime() - df_2.parse(localTime).getTime()) / (3600000);
        return diffInHours;
	}
	
	public SlotsEntity getSlotById(long slotid) {
		return restTemplate.getForObject( "http://localhost:8081/fetch/slotbyid/" + slotid, SlotsEntity.class);
	}
	
	public String bookSlot(SlotsEntity slot) {
		slot.setDate(new Date(slot.getDate().getTime() + ((10 + slot.getSlot()*2) * 3600 * 1000)));
		bookingRepository.save(slot);
		return "Slot booked successfully";
	}
	
	public String cancelSlot(long slotid) throws ParseException {
        
		SlotsEntity slotToDelete = getSlotById(slotid);
		
		if(findDiffInHours(slotToDelete) >=24) {
			bookingRepository.deleteById(slotid);
			return "Slot cancellation successful";
		}
		else
			return "Slot cancellation can be performed only until 24hrs before the start time";
	}
	
	public String modifySlot(long slotid, SlotsEntity newslot) throws ParseException {
		
		SlotsEntity slotToUpdate = getSlotById(slotid);
		
		if(slotToUpdate != null) {
			if(findDiffInHours(slotToUpdate) >=24) {
				newslot.setDate(new Date(newslot.getDate().getTime() + ((10 + newslot.getSlot()*2) * 3600 * 1000)));
				slotToUpdate.setDate(newslot.getDate());
				slotToUpdate.setTimeZone(newslot.getTimeZone());
				slotToUpdate.setSlot(newslot.getSlot());
				bookingRepository.save(slotToUpdate);
				return "Slot updated successful";
			}
			
			else
				return "Slot updation can be performed only until 24hrs before the start time";
		}
		else
			return "Slot updation failed";
	}
}