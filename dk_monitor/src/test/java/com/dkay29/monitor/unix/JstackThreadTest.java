package com.dkay29.monitor.unix;

import static org.junit.Assert.*;

import org.junit.Test;

public class JstackThreadTest {

	String t1[]={			
			" \"pool-1-thread-1\" #8 prio=5 os_prio=0 tid=0x00007f140429d000 nid=0xc66 waiting on condition [0x00007f13e71c1000]",
			"   java.lang.Thread.State: TIMED_WAITING (sleeping)",
			"        at java.lang.Thread.sleep(Native Method)",
			"        at com.dkay29.load.sim.SimBase.yield(SimBase.java:29)",
			"        at com.dkay29.load.sim.ComputeSim.run(ComputeSim.java:21)",
			"        at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)",
			"        at java.util.concurrent.FutureTask.run(FutureTask.java:266)",
			"        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)",
			"        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)",
			"        at java.lang.Thread.run(Thread.java:745)"};

	@Test
	public void test() {
		JstackThread jst=new JstackThread(t1);
		System.out.println(jst.getThreadName());
		assertEquals("Threadname","pool-1-thread-1",jst.getThreadName());
		assertEquals("threadNumber",8,jst.getThreadNumber());
		assertEquals("jvmPriority",5,jst.getJvmPriority());
		assertEquals("jvmOsPriority",0,jst.getOsPriority());
		assertEquals("tid",0x00007f140429d000L,jst.getTid());
		assertEquals("desc","waiting on condition [0x00007f13e71c1000]",jst.getThreadDescription());
		assertEquals("state",java.lang.Thread.State.TIMED_WAITING,jst.getState());
	}

}
