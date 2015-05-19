package org.bankofspring.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class TestAppender extends AppenderSkeleton {
    private List<String> messages = new ArrayList<String>();

    public void doAppend(LoggingEvent event) {
        messages.add( event.getMessage().toString() );
    }

	@Override
	public void close() {
		// nothing
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected void append(LoggingEvent event) {
		doAppend(event);
	}
	
	public List<String> getMessages() {
		return messages;
	}
}