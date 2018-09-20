package fr.kata;

public class CustomerOrder {

	public enum Drink {
		COFFE, CHOCOLATE, TEA
	}
	
	private final Drink drink;
	
	private final int sugar;
	
	private final int stick;
	
	public CustomerOrder(final Drink drink, final int sugar, final int stick) {
		this.drink = drink;
		this.sugar = sugar;
		this.stick = stick;
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
}
