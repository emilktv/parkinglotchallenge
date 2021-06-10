package parkinglotchallenge;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;  
public class ParkingLot {
	
	public final int MAX_CAR_COUNT=300;
	public final int MAX_BIKE_COUNT=200;
	
	int carCount=0;
	int bikeCount=0;
	
	HashMap<String, Vehicle> vehicleDetails = new HashMap<String, Vehicle>();
	HashMap<String, Billing> billingDetails = new HashMap<String, Billing>();
	
	Scanner sc=new Scanner(System.in);
	
	public void checkSlotAvailable() {
		System.out.println("Enter Vehicle Type");
		String vType=sc.nextLine();
		if( (!isCarSpotFull()) && vType.equals("car")) {
			System.out.println("Car Slots Available");
			newParking(vType);
		}
		else if( (!isBikeSpotFull()) && vType.equals("bike")) {
			System.out.println("Bike Slots Available");
			newParking(vType);
		}
	}
	
	public void newParking(String vehicleType) {
		 
		Vehicle vehicleObj=new Vehicle();
		System.out.println("New Parking");
		System.out.println("Enter Vehicle Number");
		vehicleObj.plateNumber=sc.nextLine();
		vehicleObj.type=vehicleType;
		Date currentTime=new Date();
		vehicleObj.startTime=currentTime;
		vehicleDetails.put(vehicleObj.plateNumber, vehicleObj);
		if(vehicleType.equals("car")) {
			carCount++;
		}
		else if(vehicleType.equals("bike")) {
			bikeCount++;
		}
		System.out.println("Please Park at any slot");
	}
	
	public void exitParking() {
		String exitVehicleNo;
		String type;
		Billing bill=new Billing();
		Vehicle vehicle=new Vehicle();
		System.out.println("Enter Vehicle Number");
		exitVehicleNo=sc.nextLine();
		vehicle=vehicleDetails.get(exitVehicleNo);
		bill.getBill(vehicle);
		type=vehicle.type;
		billingDetails.put(exitVehicleNo,bill);
		if(type.equals("car")) {
			carCount--;
		}
		else if(type.equals("bike")) {
			bikeCount--;
		}
	}
	
	public void getTotalCollection(Date today) {
		double collection=0;
		for (Billing bills : billingDetails.values()) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
			if(fmt.format(today).equals(fmt.format(bills.exitTime))){
				collection+=bills.price;
			}	 
		}
		System.out.println("Today is "+today+". Today's Collection is Rs."+collection);
		 
	}
	
	public boolean isCarSpotFull(){
		if(carCount>=MAX_CAR_COUNT) {
			return true;
		}
		else 
			return false;
	}
	
	public boolean isBikeSpotFull(){
		if(bikeCount>=MAX_BIKE_COUNT) {
			return true;
		}
		else 
			return false;
	}
	
	public void getAvailableSpaces() {
		System.out.println("No. of occupied CAR parking spaces = "+carCount);
		System.out.println("No. of occupied BIKE parking spaces = "+bikeCount);
	};
	
	public static void main(String[] args)   
	{  
		Scanner sc=new Scanner(System.in);
		ParkingLot p=new ParkingLot();
		int ch=999;
		
		while(ch!=0) {
			System.out.println("Welcome");
			System.out.println("1.Park 2.Exit 3.Today's Collection 4.Occupied Spaces");
			ch=sc.nextInt();
			switch(ch) {
				
			case 1:
				p.checkSlotAvailable();
				break;
			case 2:
				p.exitParking();
				break;
			case 3:
				Date today=new Date();
				p.getTotalCollection(today);
				break;
			case 4:
				p.getAvailableSpaces();
				break;
			case 0:
				break ;
				
			}
		}
		
		
		
		
		
		
		
		
		
	}
	

}
