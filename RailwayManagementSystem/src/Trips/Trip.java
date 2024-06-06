package Trips;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Employees.Employee;
import Passengers.Passenger;
import Trains.Train;

public class Trip {
	
	private int id;
	private String start;
	private String destination;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private LocalDate date;
	private int bookedSeats;
	private double price;
	private Employee driver;
	private Train train;
	private ArrayList<Passenger> passengers;
	
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	public Trip() {}
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepartureTime() {
		return departureTime.format(timeFormatter);
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = LocalTime.parse(departureTime, timeFormatter);
	}
	public String getArrivalTime() {
		return arrivalTime.format(timeFormatter);
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = LocalTime.parse(arrivalTime, timeFormatter);
	}
	public String getDate() {
		return date.format(dateFormatter);
	}
	public void setDate(String date) {
		this.date = LocalDate.parse(date, dateFormatter);
	}
	public int getBookedSeats() {
		return bookedSeats;
	}
	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Employee getDriver() {
		return driver;
	}
	public void setDriver(Employee driver) {
		this.driver = driver;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}
	public void addPassenger(Passenger passenger) {
		passengers.add(passenger);
	}
	public String getDay() {
		return date.format(DateTimeFormatter.ofPattern("dd"));
	}
	public String getMonth() {
		return date.format(DateTimeFormatter.ofPattern("MM"));
	}
	public String getYear() {
		return date.format(DateTimeFormatter.ofPattern("yyyy"));
	}
	public String getDeptHour() {
		return departureTime.format(DateTimeFormatter.ofPattern("HH"));
	}
	public String getDeptMin() {
		return departureTime.format(DateTimeFormatter.ofPattern("mm"));
	}
	public String getArrHour() {
		return arrivalTime.format(DateTimeFormatter.ofPattern("HH"));
	}
	public String getArrMin() {
		return arrivalTime.format(DateTimeFormatter.ofPattern("mm"));
	}

}
