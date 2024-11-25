package exam;

/**
 * This class is just for testing the methods of
 * the Office and Employee classes. It creates random
 * values and therefore will have different
 * number results each time it is ran.
 * You do not need to write anything in this class and
 * you are not required to use it for testing
 * (but you may).
 */

import java.util.ArrayList;

public class OfficePayroll {

	public static void main(String[] args) {
		
		//Testing code for Employee and Office

		int floors = 3;
		int rooms = 6;
		Office office = new Office(floors, rooms);
		
		for (int floor = 0; floor < floors; floor++) {
			office.assignRoom(new Employee("Project Lead"), floor, 0);
			office.assignRoom(new Employee("Senior Software Engineer"), floor, 1);
			for (int room = 0; room < rooms; room++) {
				if (Math.random() < .8) {
					Employee e;
					if (Math.random() < .2) {
						e = new Employee("Senior Software Engineer");
					}
					else {
						e = new Employee("Software Engineer");
					}
					
					for (int i = 0; i < Math.random() * 20; i++) {
						e.logHours(Math.random() * 12);
					}
					office.assignRoom(e, floor, room);
				}
			}
		}
		
		office.swapRooms(0, 1, 2, 5);
		office.swapRooms(0, 0, 2, 2);
		
		double fp[] = office.getEachFloorPay();
		for (int i = 0; i < fp.length; i++) {
			System.out.printf("Floor %d pay: %.2f\n", i, fp[i]);
		}
		
	}
	
	
	
	
	
}


