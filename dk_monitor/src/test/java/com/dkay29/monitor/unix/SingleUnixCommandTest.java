package com.dkay29.monitor.unix;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingleUnixCommandTest {

	@Test
	public void test() {
		SingleUnixCommand cmd = new SingleUnixCommand("jps -m");
		Exception e1=null;
		int exitCode=-1;
		try {
			exitCode=cmd.call();
		} catch (Exception e) {
			e.printStackTrace();
			e1=e;
		}
		assertEquals("No exception",null,e1);
		assertEquals("Zero exit code",0,exitCode);
		System.out.println(cmd.getOutBuffer().toString());
	}

}
