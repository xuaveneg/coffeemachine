package fr.kata;

import fr.kata.CustomerOrder.Drink;

public class Main {

	public static void main(String[] args) {
		final CustomerOrder order1 = new CustomerOrder(Drink.TEA, 1, 0);
		System.out.println(order1.translateToDrinkMaker());
	}

}
