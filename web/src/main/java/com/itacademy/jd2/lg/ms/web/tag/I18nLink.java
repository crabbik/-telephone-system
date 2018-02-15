package com.itacademy.jd2.lg.ms.web.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.itacademy.jd2.lg.ms.web.filter.I18nFilter;

public class I18nLink extends SimpleTagSupport {
	private String key;

	private static final Map<String, ResourceBundle> bundles = new HashMap<>();

	@Override
	public void doTag() throws JspException, IOException {
		String language = (String) getJspContext().findAttribute(I18nFilter.USER_LANGUAGE);

		if (language == null) {
			language = "ru";
		}

		getJspContext().getOut().println(getBundle(language).getString(getKey()));
	}

	public static ResourceBundle getBundle(String language) {
		if (!bundles.containsKey(language)) {
			ResourceBundle bundle = ResourceBundle.getBundle("i18n", new Locale(language));
			bundles.put(language, bundle);
		}
		return bundles.get(language);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}