package com.carconnect.domain.objects;

import java.util.ArrayList;
import java.util.List;

public class UserHistory {

	List<Offered> offeredList = new ArrayList<Offered>();
	List<Booked> bookedList = new ArrayList<Booked>();

	public List<Offered> getOfferedList() {
		return offeredList;
	}

	public void setOfferedList(List<Offered> offeredList) {
		this.offeredList = offeredList;
	}

	public List<Booked> getBookedList() {
		return bookedList;
	}

	public void setBookedList(List<Booked> bookedList) {
		this.bookedList = bookedList;
	}
}
