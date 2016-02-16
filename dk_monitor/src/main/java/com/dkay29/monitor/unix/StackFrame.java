package com.dkay29.monitor.unix;

public class StackFrame extends StackEntry {
	 	private final String object;
	 	private final String method;
	 	private final String sourceFile;
	 	private final Integer lineNum;
		/**
		 * Constructed from thingslike: <pre>
	    at java.lang.Thread.sleep(Native Method)
        at com.dkay29.load.sim.SimBase.yield(SimBase.java:29)
        at com.dkay29.load.sim.ComputeSim.run(ComputeSim.java:21)
   </pre>
		 * @param depth
		 * @param line
		 */
		public StackFrame(int depth, String line) {
			super(depth, Type.FRAME);
			String objAndMethod= line.replaceFirst("at ([^(]+).*","$1");
			this.object = objAndMethod.replaceFirst("\\.[^.]+$", "");
			this.method = objAndMethod.replaceFirst("^.*\\.", "");
			String src= line.replaceFirst(".*\\(([^)]+).*","$1");
			this.sourceFile = src.replaceFirst(":.*", "");
			this.lineNum = sourceFile.matches(":[0-9]+)")?Integer.parseInt(sourceFile.replaceFirst(".*:([0-9]+).*", "$1")):null;
		}
		public static boolean isStackFrameLine(String line)
		{
			return line.matches("at [^ (]\\([^\\)]+\\)");
		}
}
