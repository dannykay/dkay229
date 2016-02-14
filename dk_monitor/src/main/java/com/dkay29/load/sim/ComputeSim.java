package com.dkay29.load.sim;

import org.slf4j.LoggerFactory;

public class ComputeSim extends SimBase {
	static final org.slf4j.Logger _log = LoggerFactory.getLogger(ComputeSim.class);

	public ComputeSim(int sleepMs, int runMs) {
		this(sleepMs, runMs, System.currentTimeMillis() + 5 * 1000);
	}

	public ComputeSim(int sleepMs, int runMs, long stopTime) {
		super(sleepMs, runMs, stopTime);
	}
	public ComputeSim(long stopTime) {
		this(20,3,stopTime);
	}

	public void run() {
		_log.info("STARTED");
		while (!yield()) {
			float f = 3.14F;
			for (int i = 0; i < 100000; i++) {
				f = f * f;
				cycles++;
			}
		}
		_log.info("FINISHED after {} cycles",cycles);
	}
}
