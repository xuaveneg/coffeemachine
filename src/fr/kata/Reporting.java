package fr.kata;

import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.Map;

import fr.kata.CustomerOrder.Drink;

public class Reporting {
	
	public static class DrinkStat {
		private int nbServed;
		private int nbHotServed;
		private double moneyEarned;
		
		public void add(final DrinkStat stat) {
			if (stat != null) {				
				this.nbServed += stat.nbServed;
				this.nbHotServed += stat.nbHotServed;
				this.moneyEarned += stat.moneyEarned;
			}
		}
	}
	
	private static final Map<Drink, DrinkStat> stats = new EnumMap<>(Drink.class);
	
	public static void serve(final Drink drink, final boolean hot) {
		if (!stats.containsKey(drink)) {
			stats.put(drink, new DrinkStat());
		}
		stats.get(drink).nbServed ++;
		if (hot) {
			stats.get(drink).nbHotServed ++;
		}
		stats.get(drink).moneyEarned += drink.getCost();
	}
	
	private static StringBuilder statDisplayBuilder(final DrinkStat stat) {
		final StringBuilder sb = new StringBuilder();
		sb.append(stat.nbServed)
			.append(';')
			.append(stat.nbHotServed)
			.append(';')
			.append(new DecimalFormat("0.00").format(stat.moneyEarned));
		return sb;
	}
	
	private static String buildDrinkStat(final Drink drink) {
		final DrinkStat stat;
		if (stats.containsKey(drink)) {
			stat = stats.get(drink);
		} else {
			stat = new DrinkStat();
		}
		final StringBuilder sb = new StringBuilder();
		sb.append(drink.name())
			.append(';')
			.append(statDisplayBuilder(stat));
		return sb.toString();
	}
	
	public static void showConsole() {
		final DrinkStat total = new DrinkStat();
		for (final Drink drink : CustomerOrder.Drink.values()) {
			total.add(stats.get(drink));
			System.out.println(buildDrinkStat(drink));
		}
		System.out.println(new StringBuilder("TOTAL;")
				.append(statDisplayBuilder(total))
				.toString());
	}
	
	public static void toCsvFile(final String filePath) {
		
	}
}
