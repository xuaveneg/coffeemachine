package fr.kata;

import fr.kata.CustomerOrder.Drink;

public class Main {

	public static void main(String[] args) {
		// Printing simples customer orders
		final CustomerOrder order1 = new CustomerOrder(Drink.TEA, 1, true);
		System.out.println(order1.translateToDrinkMaker());
		final CustomerOrder order2 = new CustomerOrder(Drink.CHOCOLATE);
		System.out.println(order2.translateToDrinkMaker());
		final CustomerOrder order3 = new CustomerOrder(Drink.COFFE, 2, true);
		System.out.println(order3.translateToDrinkMaker());
		// Adding one or two sugars
		order1.addSugar(1);
		System.out.println(order1.translateToDrinkMaker());
		order3.addSugar(2);
		System.out.println(order3.translateToDrinkMaker());
		// Putting stick if the order contains sugar
		order2.addSugar(1);
		System.out.println(order2.translateToDrinkMaker());
		final CustomerOrder order4 = new CustomerOrder(Drink.COFFE, 1, false);
		System.out.println(order4.translateToDrinkMaker());
	}

}
