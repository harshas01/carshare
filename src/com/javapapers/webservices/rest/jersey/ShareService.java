package com.javapapers.webservices.rest.jersey;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.carconnect.domain.objects.BookARide;
import com.carconnect.domain.objects.Booked;
import com.carconnect.domain.objects.Car;
import com.carconnect.domain.objects.Cast;
import com.carconnect.domain.objects.CoPassenger;
import com.carconnect.domain.objects.CounterForBookARide;
import com.carconnect.domain.objects.CounterForOfferARide;
import com.carconnect.domain.objects.DateTime;
import com.carconnect.domain.objects.GetRides;
import com.carconnect.domain.objects.Message;
import com.carconnect.domain.objects.Movie;
import com.carconnect.domain.objects.OfferRide;
import com.carconnect.domain.objects.Offered;
import com.carconnect.domain.objects.RequestRide;
import com.carconnect.domain.objects.Ride;
import com.carconnect.domain.objects.Subscription;
import com.carconnect.domain.objects.User;
import com.carconnect.domain.objects.UserHistory;
import com.carconnect.service.notification.SendNotification;

@Path("/carconnect")
public class ShareService {

	private static List<Movie> movies = new ArrayList<Movie>();
	private static List<OfferRide> offerRideList = new ArrayList<OfferRide>();
	private static List<Subscription> subscriptionList = new ArrayList<Subscription>();
	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	// Sree Start
	private static Set<User> users = new HashSet<User>();
	private static String currentlyLoggedInUser;

	private enum emailScenarios {
		SUBSCRIPTION, BOOKING
	};
	// Sree End

	// Sree Start
	@GET
	@Path("userIdentification/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void userIdentification(@PathParam("userId") String userId) {
		this.currentlyLoggedInUser = userId;
	}

	@GET
	@Path("getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser() {
		for (User user : users) {
			if (this.currentlyLoggedInUser.equalsIgnoreCase(user.getUserId())) {
				return user;
			}
		}
		return new User();
	}

	@GET
	@Path("getuserInformation/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getProfile(@PathParam("userId") String userId)
			throws JsonParseException, JsonMappingException, IOException {
		for (User user : users) {
			if (userId.equalsIgnoreCase(user.getUserId())) {
				return user;
			}
		}
		return new User();
	}

	@POST
	@Path("createprofile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message createProfile(String data) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(data, User.class);
		for (User userExisting : users) {
			if ("".equalsIgnoreCase(user.getUserId())) {
				userExisting.setUserId(currentlyLoggedInUser);
				user = userExisting;
				break;
			} else {
				if ((user.getUserId()).equalsIgnoreCase(userExisting.getUserId())) {
					if ("".equalsIgnoreCase(user.getUserId())) {
						user.setUserId(currentlyLoggedInUser);
					}
					users.remove(userExisting);
					users.add(user);
				}
			}
		}
		if (users.isEmpty()) {
			user.setUserId(currentlyLoggedInUser);
		}
		users.add(user);
		Message message = new Message();
		message.setStatus("Success");
		message.setMessageText("Profile creation successful");
		return message;
	}

	@GET
	@Path("updateRoute/{places}")
	public void updateRoute(@PathParam("places") String places) {
		List<String> placesList = new ArrayList<>(Arrays.asList(places.split(",")));
		OfferRide offerRide = new OfferRide();
		for (OfferRide offerRideExisting : offerRideList) {
			if (offerRideExisting.getUser() != null && offerRideExisting.getUser().getUserId() != null) {
				if (offerRideExisting.getUser().getUserId().equalsIgnoreCase(currentlyLoggedInUser)) {
					offerRideExisting.setStopOvers(placesList);
					offerRide = offerRideExisting;
					break;
				}
			}
		}
		for (OfferRide offerRideExiSting : offerRideList) {
			if (offerRide.getOfferId() == offerRideExiSting.getOfferId()) {
				offerRideList.remove(offerRideExiSting);
				offerRideList.add(offerRide);
			}
		}

	}

	/**
	 * Prepare E mail.
	 *
	 * @param user
	 *            the user
	 * @param scenario
	 *            the scenario
	 * @param object1
	 *            the object 1
	 */
	private void prepareEMail(User user, String scenario, Object object1) {
		if (scenario.equalsIgnoreCase(emailScenarios.BOOKING.name())) {
			if (object1 instanceof OfferRide) {
				OfferRide offerRide = (OfferRide) object1;
				String subject = "Car Connect: Booking Alert";
				String body = "Booking has been made by user: " + offerRide.getUser().getUserId();
				String toEmail = offerRide.getUser().getEmail();
				sendEmail(subject, body, toEmail);
			}
		}
	}

	private void sendEmail(String subject, String body, String toEmail) {
		new SendNotification().sendNotification(body, subject, toEmail);
	}
	// Sree End

	

	@POST
	@Path("offerRide")
	@Consumes(MediaType.APPLICATION_JSON)
	public void offerRide(String data) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		OfferRide offerRide = mapper.readValue(data, OfferRide.class);
		offerRide.setOfferId(CounterForOfferARide.getInstance().getCounter());
		User currentUser = null;
		for (User user : users) {
			if (currentlyLoggedInUser.equalsIgnoreCase(user.getUserId())) {
				currentUser = user;
				break;
			}
		}
		System.out.println("printing the offer id-->" + offerRide.getOfferId());
		offerRide.setUser(currentUser);
		sendEmailSubscription(offerRide);
		getRides();
		offerRideList.add(offerRide);
		
		
		List<Offered> offeredList = currentUser.getOfferedList();
		Offered offered = new Offered();
		offered.setFromPoint(offerRide.getRide().getFromPoint());
		offered.setToPoint(offerRide.getRide().getToPoint());
		offered.setDate(offerRide.getRide().getDate());
		offeredList.add(offered);
		currentUser.setOfferedList(offeredList);
		System.out.println("Created offerRide");
	}

	@GET
	@Path("getRides")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OfferRide> getRides() {
		System.out.println("returning offerRide");
		Car car = new Car();
		car.setModel("2015");
		car.setName("Hyundai Verna");
		car.setNumberOfAirbags(4);
		car.setAirConditioned(true);
		car.setRegNo("KA 05 MC 4772");

		User user = new User();
		user.setName("Harsha");
		user.setDateOfBirth(new Date(02, 02, 1980));
		user.setEmail("harsha@xyz.com");
		user.setGender('M');
		user.setMobileNumber(988423);
		user.setUserId("harsha01");
		user.setCar(car);

		Ride ride = new Ride();
		ride.setFromPoint("Bengaluru");
		ride.setToPoint("Mysore");
		ride.setDate(new Date());

		OfferRide offerRide = new OfferRide();
		offerRide.setUser(user);
		offerRide.setFare(200);
		offerRide.setRide(ride);
		offerRide.setSeats(5);

		offerRideList.add(offerRide);
		return offerRideList;
	}

	@POST
	@Path("getAllRides")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GetRides> getAllRides(String data) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		RequestRide reqRide = mapper.readValue(data, RequestRide.class);
		List<GetRides> userReqList = new ArrayList<GetRides>();
		if (StringUtils.isEmpty(reqRide.getFromPlace()) && StringUtils.isEmpty(reqRide.getToPlace())
				&& null == reqRide.getJourneyDate()) {
			for (OfferRide eachRide : offerRideList) {
				GetRides getRides = new GetRides();
				getRides.setOfferRide(eachRide);
				userReqList.add(getRides);
			}
		}

		for (OfferRide eachRide : offerRideList) {
			if (reqRide.getFromPlace().equalsIgnoreCase(eachRide.getRide().getFromPoint())
					&& reqRide.getToPlace().equalsIgnoreCase(eachRide.getRide().getToPoint())
					&& eachRide.getSeats() > 0) {

				String reqDate = df.format(reqRide.getJourneyDate());
				String eachRideDate = df.format(eachRide.getRide().getDate());
				if (reqDate.equalsIgnoreCase(eachRideDate)) {
					GetRides getRides = new GetRides();
					getRides.setOfferRide(eachRide);
					userReqList.add(getRides);
				}

			}
		}
		return userReqList;
	}

	public String convertDateToString(Date date) {
		return df.format(date);
	}

	private void sendEmailSubscription(OfferRide offerRide){
		if(!subscriptionList.isEmpty()) {
			for(Subscription sub:subscriptionList) {
				if(offerRide.getRide().getFromPoint().equalsIgnoreCase(sub.getFromPoint()) 
						&& offerRide.getRide().getToPoint().equalsIgnoreCase(sub.getToPoint()) 
						&& convertDateToString(offerRide.getRide().getDate()).equalsIgnoreCase(convertDateToString(sub.getDate()))) {
					sendEmail("Alert: A traveller is travelling on the same route", "Owner Id is :"+offerRide.getUser().getEmail(), sub.getEmailId());
				}
			}
		}
	}

	@POST
	@Path("bookARide")
	@Consumes(MediaType.APPLICATION_JSON)
	public void bookRide(String data) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		BookARide bookRide = mapper.readValue(data, BookARide.class);
		User currentUser = null;
		for (User user : users) {
			if (currentlyLoggedInUser.equalsIgnoreCase(user.getUserId())) {
				currentUser = user;
				break;
			}
		}
		List<Booked> bookedList = currentUser.getBookedList();
		Booked booked = new Booked();
		booked.setBookingId(CounterForBookARide.getInstance().getCounter());
		bookedList.add(booked);
		currentUser.setBookedList(bookedList);
		for (OfferRide eachRide : offerRideList) {
			if (eachRide.getOfferId() == bookRide.getOfferId()) {
				User userWhoOffered = eachRide.getUser();
				List<Offered> offeredList = userWhoOffered.getOfferedList();
				Offered offered = new Offered();
				List<CoPassenger> exisitingCoPassList = offered.getCoPassList();
				for (CoPassenger passeng : bookRide.getCoPassengerList()) {
					exisitingCoPassList.add(passeng);
				}
				offered.setCoPassList(exisitingCoPassList);
				int updateSeats = eachRide.getSeats() - bookRide.getCoPassengerList().size();
				eachRide.setSeats(updateSeats);
			}
		}
		System.out.println("Created offerRide");
	}

	@GET
	@Path("getHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public UserHistory getHistory() {
		UserHistory userHistory = new UserHistory();

		User currentUser = null;
		for (User user : users) {
			if (currentlyLoggedInUser.equalsIgnoreCase(user.getUserId())) {
				currentUser = user;
				break;
			}
		}
		userHistory.setBookedList(currentUser.getBookedList());
		userHistory.setOfferedList(currentUser.getOfferedList());
		return userHistory;
	}

	@POST
	@Path("subscribe")
	@Consumes(MediaType.APPLICATION_JSON)
	public void subscribeRide(String data) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Subscription subscribe = mapper.readValue(data, Subscription.class);
		subscriptionList.add(subscribe);
	}

}