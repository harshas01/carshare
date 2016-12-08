package com.carconnect.domain.objects;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@JsonSerialize
public class OfferRide {

	private int offerId;
	private Ride ride;
	private User user;
	private long fare;
	private List<String> stopOvers;
	private List<CoPassenger> coPassengerList = new ArrayList<CoPassenger>();
	private int seats;
	private String departureTime;

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getFare() {
		return fare;
	}

	public void setFare(long fare) {
		this.fare = fare;
	}

	public List<String> getStopOvers() {
		return stopOvers;
	}

	public void setStopOvers(List<String> stopOvers) {
		this.stopOvers = stopOvers;
	}

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

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	
}
