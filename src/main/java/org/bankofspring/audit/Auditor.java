package org.bankofspring.audit;

public class Auditor {
	public void auditBefore() {
		System.out.println("auditing before operation");
	}
	public void auditAfter() {
		System.out.println("auditing after operation");
	}
}
