package com.dkay29.monitor.unix;

import java.util.List;

public class JstackInstance {
	private final JavaProcess javaProcess;
	private final long startTime;
	private final long endTime;
	private List<JstackThread> threads;
	/**
	 * @param javaProcess
	 * @param startTime
	 * @param endTime
	 * @param threads
	 */
	public JstackInstance(JavaProcess javaProcess, long startTime, long endTime, List<JstackThread> threads) {
		super();
		this.javaProcess = javaProcess;
		this.startTime = startTime;
		this.endTime = endTime;
		this.threads = threads;
	} 
}
