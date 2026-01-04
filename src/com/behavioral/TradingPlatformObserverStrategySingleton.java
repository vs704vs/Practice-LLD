/*
 * Trading Platform (Observer + Strategy + Singleton)
Asked in: Bloomberg, Goldman Sachs, HFT startups

Requirement
Market price feed (single source)
Multiple trading algorithms subscribe
Algorithms can be switched at runtime
Only one market feed instance allowed

Patterns Used
Observer → price updates
Strategy → trading logic
Singleton → market data feed

Follow-ups
Thread safety
Latency guarantees
Backpressure handling
 */ 


package com.behavioral;

import java.util.List;
import java.util.ArrayList;

/* ===================== STRATEGY ===================== */

interface TradingStrategy {
	void execute(int price);
}

class MeanReversionStrategy implements TradingStrategy {
	@Override
	public void execute(int price) {
		System.out.println("Mean Reversion Strategy executing at price " + price);
	}
}

class MomentumStrategy implements TradingStrategy {
	@Override
	public void execute(int price) {
		System.out.println("Momentum Strategy executing at price " + price);
	}
}

/* ===================== OBSERVER (CONCRETE) ===================== */

class TradingAlgorithm {

	private final String name;
	private TradingStrategy strategy;

	public TradingAlgorithm(String name, TradingStrategy strategy) {
		this.name = name;
		this.strategy = strategy;
	}

	public void setStrategy(TradingStrategy strategy) {
		this.strategy = strategy;
	}

	public void onPriceUpdate(int price) {
		System.out.print(name + " received price → ");
		strategy.execute(price);
	}
}

/* ===================== SINGLETON + SUBJECT ===================== */

class MarketDataFeed {

	private volatile int marketPrice;
	private final List<TradingAlgorithm> subscribers =
			new ArrayList<>();

	private MarketDataFeed() {}

	private static class Holder {
		private static final MarketDataFeed INSTANCE = new MarketDataFeed();
	}

	public static MarketDataFeed getInstance() {
		return Holder.INSTANCE;
	}

	public void register(TradingAlgorithm algo) {
		subscribers.add(algo);
	}

	public void unregister(TradingAlgorithm algo) {
		subscribers.remove(algo);
	}

	public void setMarketPrice(int price) {
		this.marketPrice = price;
		notifySubscribers();
	}

	private void notifySubscribers() {
		for (TradingAlgorithm algo : subscribers) {
			algo.onPriceUpdate(marketPrice);
		}
	}
}

public class TradingPlatformObserverStrategySingleton {
	public static void main(String[] args) {

		MarketDataFeed marketFeed = MarketDataFeed.getInstance();

		TradingAlgorithm algo1 =
				new TradingAlgorithm("Algo-MeanReversion",
						new MeanReversionStrategy());

		TradingAlgorithm algo2 =
				new TradingAlgorithm("Algo-Momentum",
						new MomentumStrategy());

		marketFeed.register(algo1);
		marketFeed.register(algo2);

		marketFeed.setMarketPrice(100);

		System.out.println("---- Switching Strategy at Runtime ----");

		algo1.setStrategy(new MomentumStrategy());

		marketFeed.setMarketPrice(120);
	}
}
