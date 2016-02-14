package dkmon;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dkay29.load.sim.ComputeSim;

public class ComputeSimTest {

	@Test
	public void test() {
		ComputeSim computeSim = new ComputeSim(4,20,1000);
		long t0=System.currentTimeMillis();
		computeSim.run();
		long t1=System.currentTimeMillis();
		assertTrue("Ran expected time",t1-t0>=100L);
		long cycles=computeSim.getCycles();
		assertTrue("Executed more than 1m cycles",cycles>=1000000L);
	}

	@Test
	public void test2() {
		ComputeSim computeSim = new ComputeSim(4,20);
		long t0=System.currentTimeMillis();
		computeSim.run();
		long t1=System.currentTimeMillis();
		assertTrue("Ran expected time",t1-t0>=100L);
		long cycles=computeSim.getCycles();
		assertTrue("Executed more than 1m cycles",cycles>=1000000L);
	}

}
