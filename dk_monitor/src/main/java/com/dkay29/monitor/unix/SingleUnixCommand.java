package com.dkay29.monitor.unix;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class SingleUnixCommand implements Callable<Integer> {
	public enum StateEnum {
		NOT_STARTED, RUNNING, KILLED, FINISHED_NORMALLY
	};
	public static final int DEFAUL_MAX_OUTPUT_SIZE = 30 *1024;
	private final String command;
	private int exitCode;
	private StringBuffer outBuffer = new StringBuffer();
	private final int maxOutputBytes;

	public SingleUnixCommand(String command) {
		this(command,DEFAUL_MAX_OUTPUT_SIZE);
	}
	
	public SingleUnixCommand(String command, int maxOutputBytes) {
		super();
		this.command = command;
		this.maxOutputBytes = maxOutputBytes;
	}

	@Override
	public Integer call() throws Exception {
		Process p = Runtime.getRuntime().exec(command);
		try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
			String line;
			while ((line = input.readLine()) != null) {
				outBuffer.append(line + "\n");
				if (outBuffer.length() > this.maxOutputBytes) {
					throw new Exception("Output too big " + outBuffer.length());
				}
			}
		}
		p.waitFor();
		return exitCode=p.exitValue();
	}
	public String getCommand() {
		return command;
	}
	public StringBuffer getOutBuffer() {
		return outBuffer;
	}
	public String[] getOutputLines() {
		return outBuffer.toString().split("\n");
	}
	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}
}
