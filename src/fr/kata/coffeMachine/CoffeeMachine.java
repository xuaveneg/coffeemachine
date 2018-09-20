package fr.kata.coffeMachine;

public class CoffeeMachine {
	
	private final BeverageQuantityChecker checker;
	
	private final EmailNotifier notifier;
	
	public BeverageQuantityChecker getChecker() {
		return checker;
	}
	
	public EmailNotifier getNotifier() {
		return notifier;
	}
	
	private CoffeeMachine(final BeverageQuantityChecker checker,
			final EmailNotifier notifier) {
		this.checker = checker;
		this.notifier = notifier;
	}
	
	private static CoffeeMachine coffeeMachine;
	
	public static void initCoffeeMachine(final BeverageQuantityChecker checker,
			final EmailNotifier notifier) {
		coffeeMachine = new CoffeeMachine(checker, notifier);
	}
	
	public static CoffeeMachine getCoffeeMachine() {
		return coffeeMachine;
	}
}
