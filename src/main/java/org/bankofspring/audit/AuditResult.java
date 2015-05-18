package org.bankofspring.audit;

import org.bankofspring.model.User;

public class AuditResult {
	private String actionName;
	private User user;
	private boolean wasSuccess;
	private long startTime;
	private long endTime;
	private Throwable thrown;
	
	public AuditResult( String actionName, User user, long startTime ) {
		this.actionName = actionName;
		this.user = user;
		this.startTime = startTime;
	}

	public AuditResult( String actionName, User user ) {
		this( actionName, user, System.currentTimeMillis() );
	}
	
	public String getActionName() {
		return this.actionName;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public long getStart() {
		return this.startTime;
	}
	
	public long getEnd() {
		return this.endTime;
	}
	
	public void setEnd( long endTime ) {
		this.endTime = endTime;
	}
	
	public long getDuration() {
		return this.endTime - this.startTime;
	}
	
	public boolean wasSuccess() {
		return this.wasSuccess;
	}
	
	public void setSuccess( boolean wasSuccess ) {
		this.wasSuccess = wasSuccess;
	}
	
	public Throwable getThrown() {
		return this.thrown;
	}
	
	public void setThrown( Throwable thrown ) {
		this.thrown = thrown;
	}
}
