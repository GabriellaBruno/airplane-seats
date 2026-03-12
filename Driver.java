/*
 * Name: Gabriella Bruno
 * Project 2
 * Title: Driver.java
 * Description: uses seat class for an airplane, has menu options that use the class and static methods 
 * Date: 3/23/25
 * 
 */

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner k = new Scanner(System.in);

        /**
         * Declaring 12 seats that will be added to the 2D array
         */
        Seat oneA = new Seat("Kathryn Janeway", false, "STANDARD");
        Seat oneB= new Seat();
        Seat oneC= new Seat("Arthur Dent", false, "STANDARD");
        Seat twoA= new Seat();
        Seat twoB= new Seat("Han Solo", false, "STANDARD");
        Seat twoC= new Seat();
        Seat threeA= new Seat("James Kirk", false, "EXTRA_LEGROOM");
        Seat threeB= new Seat();
        Seat threeC= new Seat();
        Seat fourA= new Seat("Jean-Luc Picard", false,"STANDARD");
        Seat fourB= new Seat();
        Seat fourC= new Seat();

        /**
         * planeSeats is our 2D array, the plane has four rows and three seats in each row
         */
        Seat [][] planeSeats= {{oneA, oneB, oneC},
                               {twoA, twoB, twoC},
                               {threeA, threeB, threeC},
                               {fourA, fourB, fourC}};

	/*
	*Add additional code for main method here.
	*/
        
        int selection = 0;
    	System.out.println("Welcome to the plane seat selection program!");

        do {
        	System.out.println("\nMain Menu:");
        	System.out.println("1 - Print Current Seat Status");
        	System.out.println("2 - Print Current Passenger List");
        	System.out.println("3 - Reserve a Seat");
        	System.out.println("4 - Search For a Passenger");
        	System.out.println("5 - EXIT");
        	selection = k.nextInt();
        	
        	if (selection == 1) {                   //Print Current Seat Status
                printCurrentSeatStatus(planeSeats);
        		
                
        	} else if (selection == 2) {            //Print Current Passenger List
                printPassengerList(planeSeats);

                
        	} else if (selection == 3) {            //Reserve a Seat
        		String enterName;
        		int enterRow;
        		String enterSeat;
        		int c;
        		
        		do {
            		k.nextLine();
        			System.out.println("Enter your name:");
        			enterName = k.nextLine();

            		System.out.println("Enter preferred row (1, 2, 3, or 4):");
            		enterRow = k.nextInt();

            		System.out.println("Enter preferred seat in row " + enterRow + " (A, B, or C):");
            		enterSeat = k.next();
            		
            		if (enterSeat.equalsIgnoreCase("A")) {
            			c = 0;
            		} else if (enterSeat.equalsIgnoreCase("B")) {
            			c = 1;
            		} else {
            			c = 2;
            		}
            		
        		} while (selectSeat(enterRow, enterSeat, enterName, planeSeats) == false);
        		
        		
        	} else if (selection == 4) {            //extra credit - search for passenger
        		k.nextLine();

        		System.out.println("Enter the name of the passenger:");
        		String search = k.nextLine();
        		searchPassenger(search, planeSeats);
        		
        		
        	} else if (selection == 5) {            //exit
        		System.out.println("Thank you for using the program. Goodbye!");
        		
        	} else {
        		System.out.println("Error! Please enter a valid menu option");
        	}
        	
        } while (selection !=5);

    }//end main


//Static Methods -------

    /**
     * This method iterates through all seats in the 2D array and checks whether they are available using
     * the .getAvailable method. If they are available, the method prints out the seat identifier (1A, 3B, etc)
     * and the letter "A". If the set is not available, the method prints out the seat identifier with the letter "X"
     *
     * NOTE that the seat identifier is based on the position of the seat in the 2D array.
     * Seat 1A would be in the array at index [0][0], seat 3B would be in the array at index [2][1]
     *
     * @param x - the 2D array of plane seats
     */
    public static void printCurrentSeatStatus(Seat [][] x){
        for (int r = 0; r < x.length; r++) {
        	for (int c = 0; c < x[0].length; c++) {
        		if (c == 0) {
        			if (x[r][c].getAvailable() == true) {
            			System.out.print((r+1) + "A" + "  A       ");
            		} else {
            			System.out.print((r+1) + "A" + "  X       ");
            		}
        		
        		} else if (c == 1) {
        			if (x[r][c].getAvailable() == true) {
            			System.out.print((r+1) + "B" + "  A       ");
            		} else {
            			System.out.print((r+1) + "B" + "  X       ");
            		}
        		
        		} else {
        			if (x[r][c].getAvailable() == true) {
            			System.out.print((r+1) + "C" + "  A       ");
            		} else {
            			System.out.print((r+1) + "C" + "  X       ");
            		}
        		}
        	}
        	System.out.print("\n");
        }

    }//end printCurrentSeatStatus method

    
    /**
     * This method iterates through all seats the 2D array and checks whether the seat is available (getAvaialble method
     * returns true) or is unavailable (getAvailable method returns false). If the seat is unavailable, the method
     * prints out the seat identifier (1A, 3B, etc) followed by the seat's information using the toString of the
     * Seat Class.
     *
     * @param x - the 2D array of plane seats
     */
    public static void printPassengerList(Seat [][] x){
    	System.out.println("------Passenger List------");
    	for (int r = 0; r < x.length; r++) {
        	for (int c = 0; c < x[0].length; c++) {
        		if (x[r][c].getAvailable() == false) {
        			if (c == 0) {
        				System.out.println((r+1) + "A " + x[r][c].toString());
        			} else if (c == 1) {
        				System.out.println((r+1) + "B " + x[r][c].toString());
        			} else {
        				System.out.println((r+1) + "C " + x[r][c].toString());
        			}
        		}
        	}
    	}

    }//end printPassengerList method

    
    /**
     * The selectSeat method is used to assign a passenger to a seat. The seat is determined based on the row and
     * letter provided. Example, if 2 and "B" are the provided values, the passenger to should be assigned to the
     * seat in the 2D array at index [1][1]. Remember. the rows start counting at 1 but they are stored in the
     * 2D array beginning at index 0!
     *
     * @param row - int value, what row the seat is in (1,2,3, or 4)
     * @param letter - String value, should be A, B, C, or D)
     * @param passengerName - Name of passenger who is reserving the seat
     * @param x - the 2D array of plane seats
     * @return - returns a boolean value, true if the seat was successfully reserved, false if the passenger selected a seat that was already taken
     */
    public static boolean selectSeat (int row, String letter, String passengerName, Seat [][] x){
    	int c = 0; //column (as a number)
    	if (letter.equalsIgnoreCase("A")) {
    		c = 0;
    		if (x[(row-1)][c].getAvailable() == true) {
    			x[row-1][c].setPassengerName(passengerName);
				x[row-1][c].setAvailable(false);
				x[row-1][c].setType("STANDARD");
    			System.out.println("Success! Seat " + row + letter + " has been reserved.");
    			return true;
    		} else {
    			System.out.println("Error! Seat " + row + letter + " is unavailable.");
    			return false;
    		}
    		
    	} else if (letter.equalsIgnoreCase("B")) {
    		c = 1;
    		if (x[(row-1)][c].getAvailable() == true) {
    			x[row-1][c].setPassengerName(passengerName);
				x[row-1][c].setAvailable(false);
				x[row-1][c].setType("STANDARD");
    			System.out.println("Success! Seat " + row + letter + " has been reserved.");
    			return true;
    		} else {
    			System.out.println("Error! Seat " + row + letter + " is unavailable.");
    			return false;
    		}
    		
    	} else {
    		c = 2;
    		if (x[(row-1)][c].getAvailable() == true) {
    			x[row-1][c].setPassengerName(passengerName);
				x[row-1][c].setAvailable(false);
				x[row-1][c].setType("STANDARD");
    			System.out.println("Success! Seat " + row + letter + " has been reserved.");
    			return true;
    		} else {
    			System.out.println("Error! Seat " + row + letter + " is unavailable. Please try again.");
    			return false;
    		}
    	}

    }//end selectSeat method

    
    //extra credit - search for passenger    
    public static boolean searchPassenger (String passengerName, Seat [][] x) {
    	String cLetter = "";
    	boolean check = false;
    	for (int r = 0; r < x.length; r++) {
        	for (int c = 0; c < x[0].length; c++) {
    			
        		if (x[r][c].getPassengerName().equals(passengerName)) {
        			if (c == 0) {
        				cLetter = "A";
            			System.out.println(passengerName + " is at seat " + (r+1) + cLetter);
            			check = true;

        			} else if (c == 1) {
        				cLetter = "B";
            			System.out.println(passengerName + " is at seat " + (r+1) + cLetter);
            			check = true;

        			} else {
        				cLetter = "C";
            			System.out.println(passengerName + " is at seat " + (r+1) + cLetter);
            			check = true;

        			}
        		}
        		
        	}
    	}
    	if (check == true) {
    		return true;
    	} else {
    		System.out.println(passengerName + " is not on the flight.");
    		return false;
        }
    }//end searchPassenger method
    
    
}// end class

/*
 * console output:
 * 
Welcome to the plane seat selection program!

Main Menu:
1 - Print Current Seat Status
2 - Print Current Passenger List
3 - Reserve a Seat
4 - Search For a Passenger
5 - EXIT
99
Error! Please enter a valid menu option

Main Menu:
1 - Print Current Seat Status
2 - Print Current Passenger List
3 - Reserve a Seat
4 - Search For a Passenger
5 - EXIT
1
1A  X       1B  A       1C  X       
2A  A       2B  X       2C  A       
3A  X       3B  A       3C  A       
4A  X       4B  A       4C  A       

Main Menu:
1 - Print Current Seat Status
2 - Print Current Passenger List
3 - Reserve a Seat
4 - Search For a Passenger
5 - EXIT
2
------Passenger List------
1A Passenger Name: Kathryn Janeway
   Seat Type: STANDARD
1C Passenger Name: Arthur Dent
   Seat Type: STANDARD
2B Passenger Name: Han Solo
   Seat Type: STANDARD
3A Passenger Name: James Kirk
   Seat Type: EXTRA_LEGROOM
4A Passenger Name: Jean-Luc Picard
   Seat Type: STANDARD

Main Menu:
1 - Print Current Seat Status
2 - Print Current Passenger List
3 - Reserve a Seat
4 - Search For a Passenger
5 - EXIT
3
Enter your name:
Katniss Everdeen
Enter preferred row (1, 2, 3, or 4):
1
Enter preferred seat in row 1 (A, B, or C):
A
Error! Seat 1A is unavailable.
Enter your name:
Katniss Everdeen
Enter preferred row (1, 2, 3, or 4):
4
Enter preferred seat in row 4 (A, B, or C):
C
Success! Seat 4C has been reserved.

Main Menu:
1 - Print Current Seat Status
2 - Print Current Passenger List
3 - Reserve a Seat
4 - Search For a Passenger
5 - EXIT
1
1A  X       1B  A       1C  X       
2A  A       2B  X       2C  A       
3A  X       3B  A       3C  A       
4A  X       4B  A       4C  X       

Main Menu:
1 - Print Current Seat Status
2 - Print Current Passenger List
3 - Reserve a Seat
4 - Search For a Passenger
5 - EXIT
2
------Passenger List------
1A Passenger Name: Kathryn Janeway
   Seat Type: STANDARD
1C Passenger Name: Arthur Dent
   Seat Type: STANDARD
2B Passenger Name: Han Solo
   Seat Type: STANDARD
3A Passenger Name: James Kirk
   Seat Type: EXTRA_LEGROOM
4A Passenger Name: Jean-Luc Picard
   Seat Type: STANDARD
4C Passenger Name: Katniss Everdeen
   Seat Type: STANDARD

Main Menu:
1 - Print Current Seat Status
2 - Print Current Passenger List
3 - Reserve a Seat
4 - Search For a Passenger
5 - EXIT
4
Enter the name of the passenger:
Harry Potter
Harry Potter is not on the flight.

Main Menu:
1 - Print Current Seat Status
2 - Print Current Passenger List
3 - Reserve a Seat
4 - Search For a Passenger
5 - EXIT
4
Enter the name of the passenger:
Han Solo
Han Solo is at seat 2B

Main Menu:
1 - Print Current Seat Status
2 - Print Current Passenger List
3 - Reserve a Seat
4 - Search For a Passenger
5 - EXIT
5
Thank you for using the program. Goodbye!
 * 
 */
