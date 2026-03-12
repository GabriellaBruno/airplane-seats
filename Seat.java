/*
 * Name: Gabriella Bruno
 * Project 2
 * Title: Seat.java
 * Description: information about a seat on an airplane 
 * Date: 3/23/25
 * 
 */
public class Seat {

	//instance variables
	private String passengerName;
	private boolean available;
	enum SeatType {STANDARD, EXTRA_LEGROOM};
	private String type;
	
	//default constructor
	public Seat() {
		passengerName = "";
		available = true;
		type = "STANDARD";
	}
	
	//overloaded constructor
	public Seat(String pN, boolean a, String t) {
		passengerName = pN;
		available = a;
		type = t;
	}
	
	//get and set methods
	public String getPassengerName() {
		return passengerName;
	}
	
	public void setPassengerName(String pN) {
		passengerName = pN;
	}
	
	public boolean getAvailable() {
		return available;
	}
	
	public void setAvailable(boolean a) {
		available = a;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String t) {
		type = t;
	}
	
	//toString
	public String toString() {
		if (available == false) {
			String x = "Passenger Name: " + passengerName + "\n   Seat Type: " + type;
			return x;
		} else {
			String x = "Available. Seat Type: " + type;
			return x;
		}
	}
	
}//end class
