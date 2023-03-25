package com.example.project.slotbooker.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.slotbooker.entity.SlotsEntity;
import com.example.project.slotbooker.service.BookingService;

@RestController
@RequestMapping("/slotops")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/bookslot")
	public String bookSlot(@RequestBody SlotsEntity slot) {
        
		return bookingService.bookSlot(slot);
	}
	
	@PutMapping("/modifyslot/{slotid}")
	public String modifySlot(@RequestBody SlotsEntity newSlot, @PathVariable String slotid) throws NumberFormatException, ParseException {
	return bookingService.modifySlot(Integer.parseInt(slotid), newSlot);
	}	

	@DeleteMapping("/cancelslot/{slotid}")
	public String cancelSlot(@PathVariable String slotid) throws NumberFormatException, ParseException {
		return bookingService.cancelSlot(Integer.parseInt(slotid));
	}
}
