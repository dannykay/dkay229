package com.dkay29.monitor.unix;

public class LockDependency extends StackEntry {
	enum Dep { LOCKED,AWAITING };
	private final Dep dep;
	private final long lockId;
	private final String objName;
	public LockDependency(int depth, Dep dep, long lockId, String objName) {
		super(depth, Type.LOCK_DEPENDENCY);
		this.dep = dep;
		this.lockId = lockId;
		this.objName = objName;
	}
	public Dep getDep() {
		return dep;
	}
	public long getLockId() {
		return lockId;
	}
	public String getObjName() {
		return objName;
	}

}
