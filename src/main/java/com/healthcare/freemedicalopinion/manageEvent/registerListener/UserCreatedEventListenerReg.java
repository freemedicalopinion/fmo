package com.healthcare.freemedicalopinion.manageEvent.registerListener;

import java.util.ArrayList;
import java.util.List;

import com.healthcare.freemedicalopinion.manageEvent.eventListener.UserCreatedEventListener;

public class UserCreatedEventListenerReg {

	private static List<UserCreatedEventListener> listeners;

	public static List<UserCreatedEventListener> getAllRegistedListener() {
		return listeners;
	}

	public static void registerListener(UserCreatedEventListener listener) {
		if (listeners == null) {
			listeners = new ArrayList<UserCreatedEventListener>();
		} else  {
			listeners.add(listener);
		}
	}

}
