package com.carconnect.domain.objects;

import java.util.Date;

public class RequestRide {
private String fromPlace;
private String toPlace;
private Date journeyDate;
public String getFromPlace() {
	return fromPlace;
}
public void setFromPlace(String fromPlace) {
	this.fromPlace = fromPlace;
}
public String getToPlace() {
	return toPlace;
}
public void setToPlace(String toPlace) {
	this.toPlace = toPlace;
}
public Date getJourneyDate() {
	return journeyDate;
}
public void setJourneyDate(Date journeyDate) {
	this.journeyDate = journeyDate;
}


}
