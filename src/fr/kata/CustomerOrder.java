package fr.kata;

import java.text.DecimalFormat;

import fr.kata.coffeMachine.CoffeeMachine;

public class CustomerOrder {

	public enum Drink {
		COFFEE("Coffee", 'C', 0.6),
		CHOCOLATE("Chocolate", 'H', 0.5),
		TEA("Tea", 'T', 0.4),
		ORANGE_JUICE("Orange Juice", 'O', 0.6);
		
		private final char code;
		
		private final double cost;
		
		private final String label;
		
		private Drink(final String label, final char code, final double cost) {
			this.label = label;
			this.code = code;
			this.cost = cost;
		}
		
		public double getCost() {
			return this.cost;
		}
		
		public String getLabel() {
			return this.label;
		}
		
		public static Drink fromCode(final String code) {
			for (final Drink drink : Drink.values()) {
				if (Character.toString(drink.code).equals(code)) {
					return drink;
				}
			}
			return null;
		}
	}
	
	private final Drink drink;
	
	private int sugar;
	
	private boolean stick;
	
	private double money;
	
	private boolean hot;
	
	public CustomerOrder(final Drink drink, final boolean hot,
			final int sugar, final boolean stick,
			final double money) {
		this.drink = drink;
		this.hot = hot;
		this.sugar = sugar;
		this.stick = stick;
		if (this.sugar > 0) {
			this.stick = true;
		}
		this.money = money;
	}
	
	public CustomerOrder(final Drink drink, final boolean hot,
			final double money) {
		this.drink = drink;
		this.hot = hot;
		this.sugar = 0;
		this.stick = false;
		this.money = money;
	}
	
	public void addSugar(final int sugar) {
		this.sugar += sugar;
		if (this.sugar > 0) {
			this.stick = true;
		}
	}
	
	public String translateToDrinkMaker() {
		final StringBuilder sb = new StringBuilder();
		if (this.money < drink.cost) {
			sb.append("M:Missing money (")
				.append(new DecimalFormat("0.00").format(drink.cost - this.money))
				.append(')');
		} else if (CoffeeMachine.getCoffeeMachine().getChecker().isEmpty(Character.toString(drink.code))) {
			sb.append("M:No more ")
				.append(drink.label);
			CoffeeMachine.getCoffeeMachine().getNotifier().notifyMissingDrink(Character.toString(drink.code));
		} else {
			sb.append(drink.code);
			if (hot) {
				sb.append('h');
			}
			sb.append(':');
			if (sugar > 0) {
				sb.append(sugar);
			}
			sb.append(':');
			if (stick) {
				sb.append(0);
			}
			Reporting.serve(drink, hot);
		}
		return sb.toString();
	}
}
