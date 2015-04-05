package com.healthcare.freemedicalopinion.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ReadValueFromMessageSource {

	@Autowired
	MessageSource messageSource;

	public String readValueByKey(String key) {

		return messageSource.getMessage(key, null,
				LocaleContextHolder.getLocale());

	}
}
