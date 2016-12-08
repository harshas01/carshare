package com.carconnect.domain.objects;

import java.util.ArrayList;
import java.util.List;

public class Offered extends Ride {
private int seats;
private List<CoPassenger> coPassList = new ArrayList<CoPassenger>();
private int offerId;
public int getSeats() {
	return seats;
}
public void setSeats(int seats) {
	this.seats = seats;
}
public List<CoPassenger> getCoPassList() {
	return coPassList;
}
public void setCoPassList(List<CoPassenger> coPassList) {
	this.coPassList = coPassList;
}
public int getOfferId() {
	return offerId;
}
public void setOfferId(int offerId) {
	this.offerId = offerId;
}
}
