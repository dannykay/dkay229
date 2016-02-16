package com.dkay29.monitor.unix;

public class StackEntry {
	enum Type {FRAME,LOCK_DEPENDENCY};
	private final int depth;
	private final Type type;
	public StackEntry(int depth, Type type) {
		super();
		this.depth = depth;
		this.type = type;
	}
	public int getDepth() {
		return depth;
	}
	public Type getType() {
		return type;
	}
}
