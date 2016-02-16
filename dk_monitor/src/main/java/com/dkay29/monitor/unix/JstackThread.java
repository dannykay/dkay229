package com.dkay29.monitor.unix;

import java.util.List;

/**
 * Captures the single thread information output in this format.
 * <pre>
 "pool-1-thread-1" #8 prio=5 os_prio=0 tid=0x00007f140429d000 nid=0xc66 waiting on condition [0x00007f13e71c1000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at com.dkay29.load.sim.SimBase.yield(SimBase.java:29)
        at com.dkay29.load.sim.ComputeSim.run(ComputeSim.java:21)
        at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
        at java.util.concurrent.FutureTask.run(FutureTask.java:266)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)

 * </pre>
 * @author dkay29
 *
 */
public class JstackThread {
	
	private  String threadName;
	private  int threadNumber;
	private  int jvmPriority;
	private  int osPriority;
	private  long tid;
	private  int nid;
	private  java.lang.Thread.State state;
	private  String stateDescription;
	private  String threadDescription;
	private  List<StackEntry> stack;
	
	JstackThread(String[] lines)
	{
		this.threadName=lines[0].replaceFirst("^[^\"]+\"([^\"]+).*","$1");
		this.threadNumber=Integer.parseInt(lines[0].replaceFirst("^[^#]+#([0-9]+).*","$1"));
		this.jvmPriority=Integer.parseInt(lines[0].replaceFirst("^.* prio=([0-9-]+).*","$1"));
		this.osPriority=Integer.parseInt(lines[0].replaceFirst("^.* os_prio=([0-9-]+).*","$1"));
		this.tid=Long.decode(lines[0].replaceFirst("^.* tid=(0x[a-f0-9]+).*","$1"));
		this.nid=Integer.decode(lines[0].replaceFirst("^.* nid=(0x[a-f0-9]+).*","$1"));
		this.threadDescription=lines[0].replaceFirst(".*nid=0x[a-f0-9]+ ","");
		this.state=java.lang.Thread.State.valueOf(lines[1].replaceFirst(".*: ([A-Z_]+).*", "$1"));
		this.stateDescription=lines[1].replaceFirst(".*: ([A-Z_]+ )", "");
	}

	public String getThreadName() {
		return threadName;
	}

	public int getThreadNumber() {
		return threadNumber;
	}

	public int getJvmPriority() {
		return jvmPriority;
	}

	public int getOsPriority() {
		return osPriority;
	}

	public long getTid() {
		return tid;
	}

	public int getNid() {
		return nid;
	}

	public java.lang.Thread.State getState() {
		return state;
	}

	public String getStateDescription() {
		return stateDescription;
	}

	public List<StackEntry> getStack() {
		return stack;
	}

	public String getThreadDescription() {
		return threadDescription;
	}
	
}
