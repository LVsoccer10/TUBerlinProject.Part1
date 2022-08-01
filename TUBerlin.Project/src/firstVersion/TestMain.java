package firstVersion;

import java.util.ArrayList;

public class TestMain {

	/**
	 * Main that includes the prompt in the Javadoc comment, and calculates the desired results using outside methods.
	 * 
	 * Cost: The shipping cost for a big container is 1800 Euro, regardless of the shipment weight. The shipping cost for a small
	 * container is 1000 Euro for a shipment of 500kg or less, and 1200 Euro for a shipment over 500kg. 
	 * 
	 * Containers: The small container has height 2.59m, width of 2.43m, and length of 6.06m, and the big container has 
	 * height 2.59m, width of 2.43m, and length of 12.01m. 
	 * 
	 * Shipping Items: a laptop has a box size of 60 x 50 x 50 cm, and weight of 6.5 kg. A mouse has a box size of 30 x 30 x 20 cm, 
	 * and weight of 0.200 kg. A desktop has a box size of 100 x 150 x 50 cm, and weight of 20 kg. And an LCD screen has a box size of
	 * 120 x 140 x 80 cm, and weight of 2.6 kg.
	 * 
	 * Step 4: To successfully complete the project, read the order provided in the command line. Save the order in an array or in
	 * an array list. Compute the total weight and the total volume of the shipment, and compute the total volume of the containers.
	 * Select the type of container needed for the order according to only shipment volume, and then compare and suitable and select
	 * the lowest priced shipping methods. Print the final result. 
	 * 
	 * @param args, the command-line order details (in the appropriate permutation)
	 * 
	 * NOTE: Please enter four numbers into the command-line. If the order does not include a specific object (e.g. a desktop),
	 * please input a 0 in the appropriate position in the command-line. The permutation of the order is specific, and it is
	 * laptop, mouse, desktop, and LCD screen (see permutation ArrayList for clearer information). 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numberOfLaptops = Integer.parseInt(args[0]);
		int numberOfMice = Integer.parseInt(args[1]);
		int numberOfDesktops = Integer.parseInt(args[2]);
		int numberOfScreens = Integer.parseInt(args[3]);
		
		ArrayList<Integer> order = new ArrayList<Integer>();
		order.add(numberOfLaptops);
		order.add(numberOfMice);
		order.add(numberOfDesktops);
		order.add(numberOfScreens);
		
		ArrayList<String> permutation = new ArrayList<String>();
		permutation.add("Laptop");
		permutation.add("Mouse");
		permutation.add("Desktop");
		permutation.add("LCD Screen");
		
		double orderVolume = 0;
		double orderWeight = 0;
		for (int i = 0; i < order.size(); i++) {
			orderVolume += calculateItemVolume(permutation.get(i), order.get(i));
			orderWeight += calculateItemWeight(permutation.get(i), order.get(i));
		}
		
		
		// Test to see what kind of container is needed for the total volume.
		// NOTE: Since the volume of the large container is approximately twice that of the small container, we will
		// consolidate some of our shipping options because using one large container is cheaper than using two smaller containers.
		
		if (orderVolume <= calculateContainerVolume("small")) {
			System.out.println("Given a volume of " + orderVolume + " meters cubed, the shipment will require one small container.");
			if (orderWeight > 500) System.out.println("Since the shipment's weight is " + orderWeight + "kg, the shipment will cost €1200.");
			else System.out.println("Since the shipment's weight is " + orderWeight + "kg, the shipment will cost €1000.");
		} else if (orderVolume <= calculateContainerVolume("big")) {
			System.out.println("Given a volume of " + orderVolume + " meters cubed, the shipment will require one large container "
					+ " and will cost €1800.");
		} else calculateShippingMethod(orderVolume, orderWeight);
		
		System.out.println();
	}
	
	
	/**
	 * Method takes a string that describes the specific item in the order and returns the volume of the shipment for that item. 
	 * @param item, a string defining an item in the order.
	 * @param count, how many of the given item are in the order.
	 * @return the volume of the specific item in the order given how many of that item are in the order, returning 0 if the order does
	 * not include the given item.
	 */
	public static double calculateItemVolume(String item, int count) {
		if (item == null) throw new IllegalArgumentException("Input cannot be null. Enter a new item from the order.");
		double volume = 0;
		if (count == 0) return volume;
		else {
			if (item == "Laptop") {
				volume += 0.6 * 0.5 * 0.5;		// Laptop has box dimensions of 60 x 50 x 50 cm, or 0.6 x 0.5 x 0.5 m.
				return volume *= count;
			}
			if (item == "Mouse") {
				volume += 0.3 * 0.3 * 0.2;		// Mouse has box dimensions of 30 x 30 x 20 cm, or 0.3 x 0.3 x 0.2 m.
				return volume *= count;
			}
			if (item == "Desktop") {
				volume += 1.0 * 1.5 * 0.5;		// Desktop has box dimensions of 100 x 150 x 50 cm, or 1.0 x 1.5 x 0.5 m.
				return volume *= count;
			}
			if (item == "LCD Screen" ) {
				volume += 1.2 * 1.4 * 0.8;		// LCD Screens has box dimensions of 120 x 140 x 80 cm, or 1.2 x 1.4 x 0.8 m.
				return volume *= count;
			} else throw new IllegalArgumentException("Input was not one of the avaiable choices. Enter a correct item from the order.");
		}
	}
	
	
	/**
	 * Method that takes in the size of the container and returns the volume of the container.
	 * @param size, a string that describes the size of the container. 
	 * @return the volume of the container according to the desired size. 
	 */
	public static double calculateContainerVolume(String size) {
		if (size == null) throw new IllegalArgumentException("Input size cannot be null. Enter a valid size.");
		if (size == "big") {
			return 2.59 * 2.43 * 12.01;		// Big container has height 2.59m, width of 2.43m, and length of 12.01m.
		} else if (size == "small") {
			return 2.59 * 2.43 * 6.06; 		// Small container has height 2.59m, width of 2.43m, and length of 6.06m.
		} else throw new IllegalArgumentException("Input size was not one of the available choices. Enter a valid size.");
	}
	
	
	/**
	 * Method takes a string that describes the specific item in the order and returns the weight of the shipment for that item. 
	 * @param item, a string defining an item in the order.
	 * @param count, how many of the given item are in the order.
	 * @return the weight of the specific item in the order given how many of that item are in the order, returning 0 if the order does
	 * not include the given item.
	 */
	public static double calculateItemWeight(String item, int count) {
		if (item == null) throw new IllegalArgumentException("Input parameter cannot be null. Enter a new object from the order.");
		if (count == 0) return 0.0;
		else {
			if (item == "Laptop") {
				return 6.5 * count; 		// Laptop has a weight of 6.5 kg.
			}
			if (item == "Mouse") {
				return 0.2 * count;			// Mouse has a weight of 0.200 kg.
			}
			if (item == "Desktop") {
				return 20 * count;			// Desktop has a weight of 20 kg.
			}
			if (item == "LCD Screen" ) {
				return 2.6 * count;			// LCD Screen has a weight of 2.6 kg.
			} else throw new IllegalArgumentException("Input parameter was not one of the avaiable choices. Enter a correct object from the order.");
		}
	}
	
	
	/**
	 * Method to calculate the cheapest shipping option given an order, using the fact that the large container has a volume approximately
	 * twice that of the small container (or the volume of small container is approximately half the volume of the large container). 
	 * This means that economically, it is cheaper to use one large container rather than two smaller containers (since we have already
	 * established that, when compared using volume, the larger is approximately twice the size). 
	 * @param volume, the total volume of an order.
	 * @param weight, the total weight of an order. 
	 */
	public static void calculateShippingMethod(double volume, double weight) {
		if (volume <= 0) throw new IllegalArgumentException("Calculated volume of the shipment is invalid.");
		if (weight <= 0) throw new IllegalArgumentException("Calculated weight of the shipment is invalid.");
		double volumeBig = calculateContainerVolume("big");
		double volumeSmall = calculateContainerVolume("small");
		 
		int numberOfBig = (int) volume / (int) volumeBig;
		if ((volume - (numberOfBig * volumeBig)) <= volumeSmall) {
			System.out.println("The volume of the shipment, " + volume + " meters cubed, fits into " + numberOfBig 
					+ " large container(s) fully and one small container.");
			System.out.println("For the " + (numberOfBig + 1) + " containers, the total shipping cost is €" + (1200 + numberOfBig * 1800) 
					+ " or €" + (1000 + numberOfBig * 1800) + ", depending on the weight of the small container when shipped.");
		} else {
			System.out.println("The volume of the shipment, " + volume + " meters cubed, fits into " + (numberOfBig + 1) + " large containers.");
			System.out.println("The total cost of the shipment is €" + ((numberOfBig + 1) * 1800) + ".");
		}
	}
	
	

}
