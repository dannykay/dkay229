package com.dkay29.monitor.unix;

public class RunningJavaJvm {
	private final int pid;
	private final String commandLine;
	/**
	 * @param pid
	 * @param commandLine
	 */
	public RunningJavaJvm(int pid, String commandLine) {
		super();
		this.pid = pid;
		this.commandLine = commandLine;
	}
	public int getPid() {
		return pid;
	}
	public String getCommandLine() {
		return commandLine;
	}
	
}
