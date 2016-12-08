package com.carconnect.domain.objects;

import java.util.List;

public class GetRides {
	private OfferRide offerRide;
	private List<CoPassenger> coPassengers;
	public OfferRide getOfferRide() {
		return offerRide;
	}
	public void setOfferRide(OfferRide offerRide) {
		this.offerRide = offerRide;
	}
	public List<CoPassenger> getCoPassengers() {
		return coPassengers;
	}
	public void setCoPassengers(List<CoPassenger> coPassengers) {
		this.coPassengers = coPassengers;
	}
	

}
