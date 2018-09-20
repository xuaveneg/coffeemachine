package fr.kata;

import fr.kata.CustomerOrder.Drink;

public class Main {

	public static void main(String[] args) {
		// Printing simples customer orders
		final CustomerOrder order1 = new CustomerOrder(Drink.TEA, 1, true);
		System.out.println("Tea with 1 sugar and stick : " + order1.translateToDrinkMaker());
		final CustomerOrder order2 = new CustomerOrder(Drink.CHOCOLATE);
		System.out.println("Chocolate with no sugar and no stick : " + order2.translateToDrinkMaker());
		final CustomerOrder order3 = new CustomerOrder(Drink.COFFE, 2, true);
		System.out.println("Coffee with 2 sugars and a stick : "+ order3.translateToDrinkMaker());
		// Adding one or two sugars
		order1.addSugar(1);
		System.out.println("Adding a sugar to tea with 1 sugar and stick : " + order1.translateToDrinkMaker());
		order3.addSugar(2);
		System.out.println("Adding two sugars to coffee with 2 sugars and a stick : " + order3.translateToDrinkMaker());
		// Putting stick if the order contains sugar
		order2.addSugar(1);
		System.out.println("Adding a sugar to chocolate with no sugar and no stick : " + order2.translateToDrinkMaker());
		final CustomerOrder order4 = new CustomerOrder(Drink.COFFE, 1, false);
		System.out.println("Creating an order for Coffee with a sugar and no stick : " + order4.translateToDrinkMaker());
	}

}
