package com.aplaline.baibulo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Version extractor retrieving information from __version cookie 
 */
public class CookieVersionExtractor implements VersionExtractor {
	private static final Logger log = LoggerFactory.getLogger(CookieVersionExtractor.class);

	public static final String VERSION_COOKIE_NAME = "__version";

	@Override
	public String extractVersionFromRequest(HttpServletRequest request) {
		return getCookieValue(request.getCookies());
	}

	private String getCookieValue(final Cookie[] cookies) {
		final Cookie cookie = getCookieFromList(cookies);
		return cookie == null ? null : cookie.getValue();
	}

	private Cookie getCookieFromList(final Cookie[] cookies) {
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(VERSION_COOKIE_NAME)) {
					log.info("Found " + VERSION_COOKIE_NAME + " cookie with value " + cookies[i].getValue());
					return cookies[i];
				}
			}
		}
		return null;
	}

	public static void setVersionCookie(HttpServletResponse response, String version) {
		response.addCookie(new Cookie(VERSION_COOKIE_NAME, version));
	}
}
