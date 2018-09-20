package fr.kata;

import fr.kata.CustomerOrder.Drink;

public class Main {

	public static void main(String[] args) {
		{
			// Iteration 1
			System.out.println("----- Tests for iteration 1 : ");
			// Printing simples customer orders
			final CustomerOrder order1 = new CustomerOrder(Drink.TEA, false, 1, true, 1);
			System.out.println("Tea with 1 sugar and stick : " + order1.translateToDrinkMaker());
			final CustomerOrder order2 = new CustomerOrder(Drink.CHOCOLATE, false, 1);
			System.out.println("Chocolate with no sugar and no stick : " + order2.translateToDrinkMaker());
			final CustomerOrder order3 = new CustomerOrder(Drink.COFFE, false, 2, true, 1);
			System.out.println("Coffee with 2 sugars and a stick : "+ order3.translateToDrinkMaker());
			// Adding one or two sugars
			order1.addSugar(1);
			System.out.println("Adding a sugar to tea with 1 sugar and stick : " + order1.translateToDrinkMaker());
			order3.addSugar(2);
			System.out.println("Adding two sugars to coffee with 2 sugars and a stick : " + order3.translateToDrinkMaker());
			// Putting stick if the order contains sugar
			order2.addSugar(1);
			System.out.println("Adding a sugar to chocolate with no sugar and no stick : " + order2.translateToDrinkMaker());
			final CustomerOrder order4 = new CustomerOrder(Drink.COFFE, false, 1, false, 1);
			System.out.println("Creating an order for Coffee with a sugar and no stick : " + order4.translateToDrinkMaker());
		}
		{
			// Iteration 2
			System.out.println("----- Tests for iteration 2 : ");
			// Tests if too much money on iteration 1
			{
				// Just the correct amount
				final CustomerOrder order1 = new CustomerOrder(Drink.TEA, false, 1, true, 0.4);
				System.out.println(order1.translateToDrinkMaker());
				final CustomerOrder order2 = new CustomerOrder(Drink.CHOCOLATE, false, 0.5);
				System.out.println(order2.translateToDrinkMaker());
				final CustomerOrder order3 = new CustomerOrder(Drink.COFFE, false, 2, true, 0.6);
				System.out.println(order3.translateToDrinkMaker());
			}
			{
				// No money
				final CustomerOrder order1 = new CustomerOrder(Drink.TEA, false, 1, true, 0);
				System.out.println(order1.translateToDrinkMaker());
				final CustomerOrder order2 = new CustomerOrder(Drink.CHOCOLATE, false, 0);
				System.out.println(order2.translateToDrinkMaker());
				final CustomerOrder order3 = new CustomerOrder(Drink.COFFE, false, 2, true, 0);
				System.out.println(order3.translateToDrinkMaker());
			}
			{
				// Just not enough money
				final CustomerOrder order1 = new CustomerOrder(Drink.TEA, false, 1, true, 0.39);
				System.out.println(order1.translateToDrinkMaker());
				final CustomerOrder order2 = new CustomerOrder(Drink.CHOCOLATE, false, 0.49);
				System.out.println(order2.translateToDrinkMaker());
				final CustomerOrder order3 = new CustomerOrder(Drink.COFFE, false, 2, true, 0.59);
				System.out.println(order3.translateToDrinkMaker());
			}
		}
		{
			// Iteration 3
			// The tests in previous iteration must still be working
			System.out.println("----- Tests for iteration 3 : ");
			// Orange juice
			final CustomerOrder order1 = new CustomerOrder(Drink.ORANGE_JUICE, false, 1);
			System.out.println(order1.translateToDrinkMaker());
			final CustomerOrder order2 = new CustomerOrder(Drink.ORANGE_JUICE, false, 0.5);
			System.out.println(order2.translateToDrinkMaker());
			// Hot drink
			final CustomerOrder order3 = new CustomerOrder(Drink.COFFE, true, 1);
			System.out.println(order3.translateToDrinkMaker());
			final CustomerOrder order4 = new CustomerOrder(Drink.CHOCOLATE, true, 1, true, 1);
			System.out.println(order4.translateToDrinkMaker());
			final CustomerOrder order5 = new CustomerOrder(Drink.TEA, true, 2, true, 1);
			System.out.println(order5.translateToDrinkMaker());
		}
		{
			// Iteration 4
			System.out.println("----- Tests for iteration 4 : ");
			Reporting.showConsole();
			Reporting.toCsvFile("report.csv");
		}
	}

}
