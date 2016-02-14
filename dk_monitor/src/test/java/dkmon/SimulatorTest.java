package dkmon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.dkay29.load.sim.ComputeSim;
import com.dkay29.load.sim.ISimulatorThread;
import com.dkay29.load.sim.MemScanSim;
import com.dkay29.load.sim.MemWrite;
import com.dkay29.load.sim.Simulator;

public class SimulatorTest {

	@Test
	public void test() {
		List<ISimulatorThread> sims=new ArrayList<>(5);
		sims.add(new ComputeSim(4,20,10000));
		sims.add(new MemWrite(50L*1024L*1024L,3,20,10000));
		sims.add(new MemScanSim(50L*1024L*1024L,3,20,10000));
		Simulator sim=new Simulator(5000L,sims);
		ExecutorService pool=Executors.newFixedThreadPool(1);
		pool.execute(sim);
		try {Thread.sleep(1000L);} catch (InterruptedException e) {}
		for (ISimulatorThread t:sim.getSimThreads())
		{
			System.out.println(t+" cycles: "+t.getCycles());
		}
		pool.shutdown();
		
	}

}
