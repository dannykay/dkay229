package com.dkay29.load.sim;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.slf4j.LoggerFactory;

public class Simulator implements Runnable {
	static final org.slf4j.Logger _log = LoggerFactory.getLogger(Simulator.class);
	private final long stopTime;
	private final List<ISimulatorThread> simThreads;

	public Simulator(long stopTime, List<ISimulatorThread> simThreads) {
		super();
		this.stopTime = stopTime < 10000000L ? System.currentTimeMillis()
				+ (long) stopTime : (long) stopTime;
		this.simThreads = simThreads;
	}

	public void run() {
		ExecutorService pool = Executors.newFixedThreadPool(simThreads.size());
		for (ISimulatorThread st : simThreads)
			pool.submit(st);
		long sleepMs = stopTime - System.currentTimeMillis();
		_log.info("sleep is " + sleepMs+" ms");
		try {
			Thread.sleep(sleepMs);
		} catch (InterruptedException e) {
		}
		for (ISimulatorThread st : simThreads)
			st.stop();
		pool.shutdown();
		int i = 0;
		for (ISimulatorThread t : simThreads) {
			_log.info("Thread " + (i++) + " " + t + " executed "
					+ t.getCycles() + " cycles");
		}
	}

	public List<ISimulatorThread> getSimThreads() {
		return simThreads;
	}

	private static class Args {
		@Argument
		List<String> arguments = new ArrayList<String>();
		@Option(name = "-c", usage = "number of compute sim threads")
		int numCompute = 0;
		@Option(name = "-s", usage = "number of mem scan sim threads")
		int numMemScan = 0;
		@Option(name = "-sm", usage = "MBs on each scan sim thread")
		int scanSize = 1;
		@Option(name = "-w", usage = "number of mem write sim threads")
		int numMemWrite = 0;
		@Option(name = "-wm", usage = "MBs on each write sim thread")
		int writeSize = 1;
		@Option(name = "-t", usage = "number of secs to run")
		int numSecs = 10;
	}

	public static void main(String args[]) {
		Args options=new Args();
		CmdLineParser parser = new CmdLineParser(options);
		try {
			parser.parseArgument(args);
			;
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			System.err.println("java SampleMain [options...] arguments...");
			// print the list of available options
			parser.printUsage(System.err);
			System.err.println();
			System.exit(1);;
		}
		List<ISimulatorThread> threads=new ArrayList<>();
		_log.info("Adding {} compute threads",options.numCompute);
		for (int i=0;i<options.numCompute;i++)
		{
			threads.add(new ComputeSim(options.numSecs*1000));
		}
		_log.info("Adding {} memscan threads of size {}Mb",options.numMemScan,options.scanSize*1024*1024);
		for (int i=0;i<options.numMemScan;i++)
		{
			threads.add(new MemScanSim(options.scanSize*1024*1024,3, 4, options.numSecs*1005));
		}
		_log.info("Adding {} memwrite threads of size {}Mb",options.numMemWrite,options.writeSize*1024*1024);
		for (int i=0;i<options.numMemWrite;i++)
		{
			threads.add(new MemScanSim(options.writeSize*1024*1024,3, 4, options.numSecs*1005));
		}
		if (threads.size()==0)
		{
			parser.printUsage(System.err);
			System.exit(1);;
		}
		_log.info("Starting simulator with run time of {} secs",options.numSecs);
		Simulator simulator=new Simulator((options.numSecs)*1000,threads);
		long t0=System.currentTimeMillis();
		simulator.run();
		long t1=System.currentTimeMillis();
		_log.info(String.format("Simulator ran for %1.1f secs",((float)(t1-t0))/1000f));
	}
}
