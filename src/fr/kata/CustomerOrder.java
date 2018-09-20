package fr.kata;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CustomerOrder {

	public enum Drink {
		COFFE, CHOCOLATE, TEA
	}
	
	private final Drink drink;
	
	private final Integer sugar;
	
	private final Integer stick;
	
	public CustomerOrder(final Drink drink, final int sugar, final int stick) {
		this.drink = drink;
		this.sugar = sugar;
		this.stick = stick;
	}
	
	public CustomerOrder(final Drink drink) {
		this.drink = drink;
		this.sugar = null;
		this.stick = null;
	}

	public Drink getDrink() {
		return drink;
	}

	public int getSugar() {
		return sugar;
	}

	public int getStick() {
		return stick;
	}
	
	public String translateToDrinkMaker() {
		final StringBuilder sb = new StringBuilder();
		switch (drink) {
			case CHOCOLATE:
				sb.append('H');
				break;
			case COFFE:
				sb.append('C');
				break;
			case TEA:
				sb.append('T');
				break;
			default:
				throw new NotImplementedException();
		}
		sb
			.append(':')
			.append(sugar)
			.append(':')
			.append(stick);
		return sb.toString();
	}
}
