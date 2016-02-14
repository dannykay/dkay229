package com.dkay29.load.sim;

public interface ISimulatorThread extends Runnable {
	public void stop();
	public long getCycles();
}
