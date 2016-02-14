package dkmon;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dkay29.load.sim.ComputeSim;
import com.dkay29.load.sim.MemScanSim;

public class MemScanSimTest {

	@Test
	public void test() {
		MemScanSim computeSim = new MemScanSim(50L*1024L*1024L,3,20,2000);
		long t0=System.currentTimeMillis();
		computeSim.run();
		long t1=System.currentTimeMillis();
		assertTrue("Ran expected time",t1-t0>=1000L);
		long cycles=computeSim.getCycles();
		assertTrue("Executed more than 1,000 cycles",cycles>=1000L);
	
	}

}
