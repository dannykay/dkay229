package com.dkay29.load.sim;

import org.slf4j.LoggerFactory;

public class MemScanSim extends SimBase{
	static final org.slf4j.Logger _log = LoggerFactory.getLogger(MemScanSim.class);
	protected byte[][] mem;
	public MemScanSim(long memSize,int sleepMs, int runMs, long stopTime) {
		super(sleepMs, runMs, stopTime);
		int fullPages=(int)(memSize/Integer.MAX_VALUE);
		int lastPageSize=(int)(memSize%Integer.MAX_VALUE);
		mem = new byte[fullPages+1][];
		for (int page=0;page<fullPages;page++)
			mem[page]=new byte[Integer.MAX_VALUE];
		mem[fullPages]=new byte[lastPageSize];
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
					System.arraycopy(mem[page], i, buffer, 0, Math.min(mem[page].length-i,buffer.length));
					cycles++;
				}
			}
		}
	}
	
}
