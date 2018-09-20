package fr.kata;

import java.io.IOException;

import fr.kata.CustomerOrder.Drink;
import fr.kata.coffeMachine.CoffeeMachine;
import fr.kata.coffeMachine.EmailNotifier;

public class Main {

	public static void main(String[] args) throws IOException {
		{
			// Iteration 5
			System.out.println("-----------------------------------");
			System.out.println("----- Tests for iteration 5 : -----");
			System.out.println("-----------------------------------");
			final EmailNotifier notifier = (s) -> {System.out.println("> Sending mail : " + s + " is empty.");};
			// Machine always empty
			System.out.println();
			System.out.println(">>> Empty Machine <<<");
			System.out.println();
			CoffeeMachine.initCoffeeMachine(
					(s) -> {return true;},
					notifier);
			doTests("empty.csv");
			// Machine always full (tests like before)
			System.out.println();
			System.out.println(">>> Full Machine <<<");
			System.out.println();
			Reporting.reset();
			CoffeeMachine.initCoffeeMachine(
					(s) -> {return false;},
					notifier);
			doTests("full.csv");
			// Only 1 of each drink (orange juice won't get empty)
			System.out.println();
			System.out.println(">>> Only 1 of each <<<");
			System.out.println();
			Reporting.reset();
			CoffeeMachine.initCoffeeMachine(
					(s) -> {
						if (Reporting.getStat(Drink.fromCode(s)).getNbServed() >= 1) {
							return true;
						} else {
							return false;
						}
					},
					notifier);
			doTests("oneOfEach.csv");
			// Only 4 of each drink (only coffee will run empty)
			System.out.println();
			System.out.println(">>> Only 4 of each <<<");
			System.out.println();
			Reporting.reset();
			CoffeeMachine.initCoffeeMachine(
					(s) -> {
						if (Reporting.getStat(Drink.fromCode(s)).getNbServed() >= 4) {
							return true;
						} else {
							return false;
						}
					},
					notifier);
			doTests("fourOfEach.csv");
			// 0 Orange juice, 3 Tea, 2 Chocolate, 4 Coffee
			System.out.println();
			System.out.println(">>> 0 O; 3 T; 2 H; 4 C <<<");
			System.out.println();
			Reporting.reset();
			CoffeeMachine.initCoffeeMachine(
					(s) -> {
						final int limit;
						switch (Drink.fromCode(s)) {
						case ORANGE_JUICE:
							limit = 0;
							break;
						case TEA:
							limit = 3;
							break;
						case CHOCOLATE:
							limit = 2;
							break;
						case COFFEE:
							limit = 4;
							break;
						default:
							return true;
						}
						if (Reporting.getStat(Drink.fromCode(s)).getNbServed() >= limit) {
							return true;
						} else {
							return false;
						}
						
					},
					notifier);
			doTests("customLimits.csv");
		}
	}
	
	private static void doTests(final String csvFilePath) throws IOException {
		{
			// Iteration 1
			System.out.println("----- Tests for iteration 1 : ");
			// Printing simples customer orders
			final CustomerOrder order1 = new CustomerOrder(Drink.TEA, false, 1, true, 1);
			System.out.println("Tea with 1 sugar and stick : ");
			System.out.println("->" + order1.translateToDrinkMaker());
			final CustomerOrder order2 = new CustomerOrder(Drink.CHOCOLATE, false, 1);
			System.out.println("Chocolate with no sugar and no stick : ");
			System.out.println("->" + order2.translateToDrinkMaker());
			final CustomerOrder order3 = new CustomerOrder(Drink.COFFEE, false, 2, true, 1);
			System.out.println("Coffee with 2 sugars and a stick : ");
			System.out.println("->" + order3.translateToDrinkMaker());
			// Adding one or two sugars
			order1.addSugar(1);
			System.out.println("Adding a sugar to tea with 1 sugar and stick : ");
			System.out.println("->" + order1.translateToDrinkMaker());
			order3.addSugar(2);
			System.out.println("Adding two sugars to coffee with 2 sugars and a stick : ");
			System.out.println("->" + order3.translateToDrinkMaker());
			// Putting stick if the order contains sugar
			order2.addSugar(1);
			System.out.println("Adding a sugar to chocolate with no sugar and no stick : ");
			System.out.println("->" + order2.translateToDrinkMaker());
			final CustomerOrder order4 = new CustomerOrder(Drink.COFFEE, false, 1, false, 1);
			System.out.println("Creating an order for Coffee with a sugar and no stick : ");
			System.out.println("->" + order4.translateToDrinkMaker());
		}
		{
			// Iteration 2
			System.out.println("----- Tests for iteration 2 : ");
			// Tests if too much money on iteration 1
			{
				// Just the correct amount
				System.out.println("Just the correct amount for each drink :");
				final CustomerOrder order1 = new CustomerOrder(Drink.TEA, false, 1, true, 0.4);
				System.out.println("->" + order1.translateToDrinkMaker());
				final CustomerOrder order2 = new CustomerOrder(Drink.CHOCOLATE, false, 0.5);
				System.out.println("->" + order2.translateToDrinkMaker());
				final CustomerOrder order3 = new CustomerOrder(Drink.COFFEE, false, 2, true, 0.6);
				System.out.println("->" + order3.translateToDrinkMaker());
			}
			{
				// No money
				System.out.println("No money : ");
				final CustomerOrder order1 = new CustomerOrder(Drink.TEA, false, 1, true, 0);
				System.out.println("->" + order1.translateToDrinkMaker());
				final CustomerOrder order2 = new CustomerOrder(Drink.CHOCOLATE, false, 0);
				System.out.println("->" + order2.translateToDrinkMaker());
				final CustomerOrder order3 = new CustomerOrder(Drink.COFFEE, false, 2, true, 0);
				System.out.println("->" + order3.translateToDrinkMaker());
			}
			{
				// Just not enough money
				System.out.println("Just not enough money : ");
				final CustomerOrder order1 = new CustomerOrder(Drink.TEA, false, 1, true, 0.39);
				System.out.println("->" + order1.translateToDrinkMaker());
				final CustomerOrder order2 = new CustomerOrder(Drink.CHOCOLATE, false, 0.49);
				System.out.println("->" + order2.translateToDrinkMaker());
				final CustomerOrder order3 = new CustomerOrder(Drink.COFFEE, false, 2, true, 0.59);
				System.out.println("->" + order3.translateToDrinkMaker());
			}
		}
		{
			// Iteration 3
			// The tests in previous iteration must still be working
			System.out.println("----- Tests for iteration 3 : ");
			// Orange juice
			final CustomerOrder order1 = new CustomerOrder(Drink.ORANGE_JUICE, false, 1);
			System.out.println("Buying orange juice : ");
			System.out.println("->" + order1.translateToDrinkMaker());
			final CustomerOrder order2 = new CustomerOrder(Drink.ORANGE_JUICE, false, 0.5);
			System.out.println("Not enough money to buy orange juice : ");
			System.out.println("->" + order2.translateToDrinkMaker());
			// Hot drink
			System.out.println("Trying to but hot drinks : ");
			final CustomerOrder order3 = new CustomerOrder(Drink.COFFEE, true, 1);
			System.out.println("->" + order3.translateToDrinkMaker());
			final CustomerOrder order4 = new CustomerOrder(Drink.CHOCOLATE, true, 1, true, 1);
			System.out.println("->" + order4.translateToDrinkMaker());
			final CustomerOrder order5 = new CustomerOrder(Drink.TEA, true, 2, true, 1);
			System.out.println("->" + order5.translateToDrinkMaker());
		}
		{
			// Iteration 4
			System.out.println("----- Tests for iteration 4 : ");
			Reporting.showConsole();
			Reporting.toCsvFile(csvFilePath);
		}
	}

}
