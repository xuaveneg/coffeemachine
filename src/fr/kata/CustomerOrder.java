package fr.kata;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CustomerOrder {

	public enum Drink {
		COFFE, CHOCOLATE, TEA
	}
	
	private final Drink drink;
	
	private int sugar;
	
	private boolean stick;
	
	public CustomerOrder(final Drink drink, final int sugar, final boolean stick) {
		this.drink = drink;
		this.sugar = sugar;
		this.stick = stick;
		if (this.sugar > 0) {
			this.stick = true;
		}
	}
	
	public CustomerOrder(final Drink drink) {
		this.drink = drink;
		this.sugar = 0;
		this.stick = false;
	}

	public Drink getDrink() {
		return drink;
	}

	public int getSugar() {
		return sugar;
	}

	public boolean getStick() {
		return stick;
	}
	
	public void addSugar(final int sugar) {
		this.sugar += sugar;
		if (this.sugar > 0) {
			this.stick = true;
		}
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
		sb.append(':');
		if (sugar > 0) {
			sb.append(sugar);
		}
		sb.append(':');
		if (stick) {
			sb.append(0);
		}
		return sb.toString();
	}
}
