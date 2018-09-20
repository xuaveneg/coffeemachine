package fr.kata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import fr.kata.CustomerOrder.Drink;

public class Reporting {
	
	public static class DrinkStat {
		private int nbServed;
		private int nbHotServed;
		private double moneyEarned;
		private double moneyHotEarned;
		
		public int getNbServed() {
			return this.nbServed;
		}
		
		public int getNbHotServed() {
			return this.nbHotServed;
		}
		
		public void add(final DrinkStat stat) {
			if (stat != null) {				
				this.nbServed += stat.nbServed;
				this.nbHotServed += stat.nbHotServed;
				this.moneyEarned += stat.moneyEarned;
				this.moneyHotEarned += stat.moneyHotEarned;
			}
		}
	}
	
	private static final Map<Drink, DrinkStat> stats = new EnumMap<>(Drink.class);
	
	public static void serve(final Drink drink, final boolean hot) {
		if (!stats.containsKey(drink)) {
			stats.put(drink, new DrinkStat());
		}
		stats.get(drink).nbServed ++;
		stats.get(drink).moneyEarned += drink.getCost();
		if (hot) {
			stats.get(drink).nbHotServed ++;
			stats.get(drink).moneyHotEarned += drink.getCost();
		}
	}
	
	public static void reset() {
		stats.clear();
	}
	
	public static DrinkStat getStat(final Drink drink) {
		return Optional.ofNullable(stats.get(drink)).orElse(new DrinkStat());
	}
	
	private static StringBuilder statDisplayBuilder(final DrinkStat stat) {
		final StringBuilder sb = new StringBuilder();
		sb.append(stat.nbServed)
			.append(';')
			.append(stat.nbHotServed)
			.append(';')
			.append(new DecimalFormat("0.00").format(stat.moneyEarned))
			.append(';')
			.append(new DecimalFormat("0.00").format(stat.moneyHotEarned));
		
		return sb;
	}
	
	private static String buildHeaderLine() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Drink")
			.append(';')
			.append("Nb of drink served")
			.append(';')
			.append("Nb of hot drink served")
			.append(';')
			.append("Money earned with drink")
			.append(';')
			.append("Money earned with hot drink");
		return sb.toString();
	}
	
	private static String buildDrinkStat(final Drink drink) {
		final DrinkStat stat;
		if (stats.containsKey(drink)) {
			stat = stats.get(drink);
		} else {
			stat = new DrinkStat();
		}
		final StringBuilder sb = new StringBuilder();
		sb.append(drink.getLabel())
			.append(';')
			.append(statDisplayBuilder(stat));
		return sb.toString();
	}
	
	public static void showConsole() {
		final DrinkStat total = new DrinkStat();
		System.out.println(buildHeaderLine());
		for (final Drink drink : CustomerOrder.Drink.values()) {
			total.add(stats.get(drink));
			System.out.println(buildDrinkStat(drink));
		}
		System.out.println(new StringBuilder("TOTAL;")
				.append(statDisplayBuilder(total))
				.toString());
	}
	
	public static void toCsvFile(final String filePath) throws IOException {
		final StringBuilder sb = new StringBuilder();
		final DrinkStat total = new DrinkStat();
		sb.append(buildHeaderLine())
			.append('\n');
		for (final Drink drink : CustomerOrder.Drink.values()) {
			total.add(stats.get(drink));
			sb.append(buildDrinkStat(drink))
				.append('\n');
		}
		sb.append(new StringBuilder("TOTAL;")
				.append(statDisplayBuilder(total))
				.append('\n')
				.toString());
		final File f = new File(filePath);
		try (final FileOutputStream fos = new FileOutputStream(f)) {
			fos.write(sb.toString().getBytes());
		}
	}
}
