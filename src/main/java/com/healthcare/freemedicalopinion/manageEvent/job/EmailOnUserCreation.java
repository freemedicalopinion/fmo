package com.healthcare.freemedicalopinion.manageEvent.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.manageEvent.event.UserCreatedEvent;
import com.healthcare.freemedicalopinion.manageEvent.eventListener.UserCreatedEventListener;
import com.healthcare.freemedicalopinion.utility.MailApi;
import com.healthcare.freemedicalopinion.utility.ReadValueFromMessageSource;
import com.healthcare.freemedicalopinion.valueobject.UserVO;

@Component
public class EmailOnUserCreation extends UserCreatedEventListener {

	@Autowired
	ReadValueFromMessageSource messageSource;

	@Override
	public void handleUserCreatedEvent(UserCreatedEvent e) {
		UserVO user = (UserVO) e.getSource();

		String body = messageSource.readValueByKey("user.otp1") + "\n  "
				+ user.getUsername() + "   "
				+ messageSource.readValueByKey("user.otp2") + "     "
				+ user.getPassword();

		MailApi.sendMail(user.getUsername(),
				messageSource.readValueByKey("user.otpSubject"), body);

	}
}
