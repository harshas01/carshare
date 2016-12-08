/**
 * 
 */
package com.carconnect.domain.objects;

/**
 * @author 
 *
 */
public class Car {
	private String model;
	private enum carType {Sedan,Hatchback,SUV,MPV};
	private String name;
	private boolean absEnabled;
	private Integer numberOfAirbags;
	private boolean airConditioned;
	private String regNo;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAbsEnabled() {
		return absEnabled;
	}
	public void setAbsEnabled(boolean absEnabled) {
		this.absEnabled = absEnabled;
	}
	public Integer getNumberOfAirbags() {
		return numberOfAirbags;
	}
	public void setNumberOfAirbags(Integer numberOfAirbags) {
		this.numberOfAirbags = numberOfAirbags;
	}
	public boolean isAirConditioned() {
		return airConditioned;
	}
	public void setAirConditioned(boolean airConditioned) {
		this.airConditioned = airConditioned;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
}
