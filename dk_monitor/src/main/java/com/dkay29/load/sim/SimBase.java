package com.dkay29.load.sim;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SimBase implements ISimulatorThread{
	final org.slf4j.Logger _log = LoggerFactory.getLogger(SimBase.class);
	protected final int sleepMs;
	protected final int runMs;
	private long lastStopTime=System.currentTimeMillis();
	protected long stopTime;
	protected long cycles=0L;
	public SimBase(int sleepMs, int runMs, long stopTime) {
		super();
		this.sleepMs = sleepMs;
		this.runMs = runMs;
		this.stopTime = stopTime<10000000L?System.currentTimeMillis()+(long)stopTime:(long)stopTime;
		_log.info("sleepMs={} runMs={} stopTime={}",this.sleepMs,this.runMs,this.stopTime);
	}
	public void stop()
	{
		stopTime=System.currentTimeMillis();
	}

	protected boolean yield()
	{
		if (System.currentTimeMillis()>lastStopTime+runMs)
		{
			try {Thread.sleep(sleepMs);} catch (InterruptedException e) {}
			lastStopTime=System.currentTimeMillis();
		}
		long msToGo=stopTime-System.currentTimeMillis();
		return !(msToGo>0L);
	}
	public long getCycles()
	{
		return cycles;
	}
	@Override
	public String toString() {
		return this.getClass().getName()+" [sleepMs=" + sleepMs + ", runMs=" + runMs
				+ ", lastStopTime=" + lastStopTime + ", stopTime=" + stopTime
				+ ", cycles=" + cycles + "]";
	}
}
