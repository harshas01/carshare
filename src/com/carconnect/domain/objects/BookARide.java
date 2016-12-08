package com.carconnect.domain.objects;

import java.util.ArrayList;
import java.util.List;

public class BookARide {

	private int offerId;
	private List<CoPassenger> coPassengerList = new ArrayList<CoPassenger>();

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public List<CoPassenger> getCoPassengerList() {
		return coPassengerList;
	}

	public void setCoPassengerList(List<CoPassenger> coPassengerList) {
		this.coPassengerList = coPassengerList;
	}
}
