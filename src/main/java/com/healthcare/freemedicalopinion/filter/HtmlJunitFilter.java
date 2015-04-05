package com.healthcare.freemedicalopinion.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public final class HtmlJunitFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String queryString = httpRequest.getRequestURL().toString();
		String url_with_hash_fragment = null;

		if ((queryString != null)
				&& (queryString.contains("_escaped_fragment_"))) {
			// rewrite the URL back to the original #! version
			// remember to unescape any %XX characters
			url_with_hash_fragment = queryString.replace("_escaped_fragment_",
					"#!");

			// use the headless browser to obtain an HTML snapshot
			final WebClient webClient = new WebClient();
			HtmlPage page = webClient.getPage(url_with_hash_fragment);

			// important! Give the headless browser enough time to execute
			// JavaScript
			// The exact time to wait may depend on your application.
			webClient.waitForBackgroundJavaScript(2000);

			// return the snapshot
			System.out.println(page.asXml());
		} else {
			try {
				// not an _escaped_fragment_ URL, so move up the chain of
				// servlet (filters)
				chain.doFilter(request, response);
			} catch (ServletException e) {
				System.err.println("Servlet exception caught: " + e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	
}