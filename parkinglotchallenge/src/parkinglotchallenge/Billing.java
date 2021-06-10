package parkinglotchallenge;

import java.util.Date;
import java.util.Scanner;

public class Billing {
	
	String vehicleNumber;
	Date exitTime;
	int hours;
	double price;
	boolean ifMovieTicket;
	
	public void getBill(Vehicle v) {
		Scanner sc=new Scanner(System.in);
		Date currentTime=new Date();
		vehicleNumber=v.plateNumber;
		exitTime=currentTime;
		hours=hoursDifference(exitTime,v.startTime);
		price=getPrice(v.type);
		System.out.println("Do you have a movie ticket(yes/no)");
		String choice;
		choice=sc.nextLine();
		if(choice.equals("yes")) {
			price=0.9 * price;
		}
		System.out.println("10% Movie Ticket Discount Applied");
		displayBill(exitTime,hours,price,vehicleNumber);
		
	}
	
	public double getPrice(String vehicleType) {
		if(vehicleType.equals("car")) {
			return 40;
		}
		else if(vehicleType.equals("bike")) {
			return 20;
		}
		else
			return 0;
		
	}
	
	public static int hoursDifference(Date date1, Date date2) {

	    final float MILLI_TO_HOUR = 1000 * 60 * 60;
	    float temp=(date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;
	    int hrs= (int) Math.ceil(temp);
	    return hrs ;
	}
	
	public void displayBill(Date eTime,int totalHrs,double finalPrice,String vPlateNo) {
		System.out.println("Vehicle Plate Number "+vPlateNo);
		System.out.println("Exit Time "+eTime);
		System.out.println("Parking Duration "+totalHrs+" Hours");
		System.out.println("Total Price Rs."+finalPrice);
		System.out.println("Thank You ! Visit Again!");
		
	}

}
