package com.dkay29.load.sim;

import org.slf4j.LoggerFactory;

public class MemWrite extends MemScanSim {
	static final org.slf4j.Logger _log = LoggerFactory.getLogger(MemWrite.class);

	public MemWrite(long memSize, int sleepMs, int runMs, long stopTime) {
		super(memSize, sleepMs, runMs, stopTime);
	}

	public void run() {
		_log.info("STARTED");
		byte buffer[]=new byte[16*1024];
		while (true)
		{
			for(int page=0;page<mem.length;page++)
			{
				for(int i=0;i<mem[page].length;i+=buffer.length)
				{
					if (yield())
					{
						_log.info("FINISHED after {} cycles",cycles);
						return;
					}
					
					System.arraycopy(buffer, 0, mem[page], i, Math.min(mem[page].length-i,buffer.length));
					cycles++;
				}
			}
		}
	}
}
