package fr.kata;

import java.util.EnumMap;
import java.util.Map;

import fr.kata.CustomerOrder.Drink;

public class Reporting {
	
	public static class DrinkStat {
		private int nbServed;
	}
	
	private static final Map<Drink, DrinkStat> stats = new EnumMap<>(Drink.class);
	
	public static void serve(final Drink drink) {
		if (!stats.containsKey(drink)) {
			stats.put(drink, new DrinkStat());
		}
		stats.get(drink).nbServed ++;
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
			.append(stat.nbServed);
		return sb.toString();
	}
	
	public static void showConsole() {
		for (final Drink drink : CustomerOrder.Drink.values()) {
			System.out.println(buildDrinkStat(drink));
		}
	}
	
	public static void toCsvFile(final String filePath) {
		
	}
}
