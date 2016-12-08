package com.carconnect.domain.objects;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterForOfferARide {
	private AtomicInteger atomicInteger;
	private static CounterForOfferARide obj = null;

	private CounterForOfferARide(int initialValue) {
		this.atomicInteger = new AtomicInteger(initialValue);
	}

	public static CounterForOfferARide getInstance() {
		if (obj == null) {
			obj = new CounterForOfferARide(100);
		}
		return obj;
	}

	public int getCounter() {
		return atomicInteger.getAndIncrement();
	}
}
