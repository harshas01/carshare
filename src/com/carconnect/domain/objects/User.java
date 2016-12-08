package com.carconnect.domain.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	private String userId;
	private String name;
	private long mobileNumber;
	private String email;
	private char gender;
	private String password;
	private Date DateOfBirth;
	private Car car;
	private List<Offered> offeredList = new ArrayList<Offered>();
	private List<Booked> bookedList = new ArrayList<Booked>();

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
