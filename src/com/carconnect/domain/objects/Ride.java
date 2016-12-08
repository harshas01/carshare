/**
 * 
 */
package com.carconnect.domain.objects;

import java.util.Date;

/**
 * @author 
 *
 */
public class Ride {

	private String fromPoint;
	private String toPoint;
	private Date date;
	
	public String getFromPoint() {
		return fromPoint;
	}
	public void setFromPoint(String fromPoint) {
		this.fromPoint = fromPoint;
	}
	public String getToPoint() {
		return toPoint;
	}
	public void setToPoint(String toPoint) {
		this.toPoint = toPoint;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
