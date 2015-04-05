package com.healthcare.freemedicalopinion.manageEvent.eventGenertor;

import java.util.Iterator;
import java.util.List;

import com.healthcare.freemedicalopinion.manageEvent.event.UserCreatedEvent;
import com.healthcare.freemedicalopinion.manageEvent.eventListener.UserCreatedEventListener;
import com.healthcare.freemedicalopinion.manageEvent.registerListener.UserCreatedEventListenerReg;

public class UserCreatedEventGenertor {

	// call this method whenever you want to notify
	// the event listeners of the particular event
	public synchronized void fireEvent(Object e) {
		final UserCreatedEvent event = new UserCreatedEvent(e);

		final List<UserCreatedEventListener> allListener = UserCreatedEventListenerReg
				.getAllRegistedListener();
		if (allListener != null) {

			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					Iterator<UserCreatedEventListener> i = allListener
							.iterator();
					while (i.hasNext()) {
						((UserCreatedEventListener) i.next())
								.handleUserCreatedEvent(event);
					}

				}
			});
			t.start();

		}

	}
}
