package com.carconnect.domain.objects;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterForBookARide {
	private AtomicInteger atomicInteger;
	private static CounterForBookARide obj = null;

	private CounterForBookARide(int initialValue) {
		this.atomicInteger = new AtomicInteger(initialValue);
	}

	public static CounterForBookARide getInstance() {
		if (obj == null) {
			obj = new CounterForBookARide(200);
		}
		return obj;
	}

	public int getCounter() {
		return atomicInteger.getAndIncrement();
	}
}
