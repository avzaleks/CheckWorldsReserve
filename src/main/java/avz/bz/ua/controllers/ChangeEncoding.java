package avz.bz.ua.controllers;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.openqa.selenium.NoSuchElementException;

import avz.bz.ua.googleassist.GoogleAssistent;

public class ChangeEncoding implements Filter {

	GoogleAssistent assistent = new GoogleAssistent();
	Thread thread = new Thread();

	public ChangeEncoding() {
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		if (request.getParameter("google") != null) {

			System.out.println(request.getParameter("google"));
			System.out.println(request.getParameter("directOfTrans"));
			try {

				response.getWriter().print(
						assistent.getTranslate(
								request.getParameter("directOfTrans"),
								request.getParameter("google")));

				new Thread(new Runnable() {

					@Override
					public void run() {
						assistent.rerunDriver();
					}
				}).run();

				System.out.println(System.currentTimeMillis() + "fff");
			} catch (NoSuchElementException e) {
				response.getWriter().print(
						"error:Google does not know such word");
				System.out.println("error:Google does not know such word");
				assistent.killDriver();
				assistent.rerunDriver();
			}

			return;

		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
