package dkmon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.dkay29.load.sim.SimBase;

public class SimBaseTest {

	private class Foo extends SimBase
	{
		public Foo() {
			super(3, 10, System.currentTimeMillis()+2000L);
		}
		public void run() {
			try{Thread.sleep(2000L);} catch (InterruptedException e) {}
			yield();
		}
	}
	@Test
	public void test() {
		ExecutorService pool=Executors.newFixedThreadPool(1);
		Foo foo=null;
		pool.submit(foo=new Foo());
		try{Thread.sleep(300L);} catch (InterruptedException e) {}
		foo.stop();
		pool.shutdown();
	}

}
