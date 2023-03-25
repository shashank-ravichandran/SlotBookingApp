package com.example.project.slotfetcher.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SlotsEntity {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
		private Date date;
		private int slot;
		private String timeZone;
		private String userId;
		private String examId;
		 
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getSlot() {
			return slot;
		}
		public void setSlot(int slot) {
			this.slot = slot;
		}
		public String getTimeZone() {
			return timeZone;
		}
		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getExamId() {
			return examId;
		}
		public void setExamId(String examId) {
			this.examId = examId;
		}
	}
