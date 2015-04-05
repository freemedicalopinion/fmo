package com.healthcare.freemedicalopinion.manageEvent.eventListener;

import java.util.EventListener;

import com.healthcare.freemedicalopinion.manageEvent.event.UserCreatedEvent;
import com.healthcare.freemedicalopinion.manageEvent.registerListener.UserCreatedEventListenerReg;

public abstract class UserCreatedEventListener implements EventListener {

	public UserCreatedEventListener() {
		UserCreatedEventListenerReg.registerListener(this);
	}

	public abstract void handleUserCreatedEvent(UserCreatedEvent e);

}
