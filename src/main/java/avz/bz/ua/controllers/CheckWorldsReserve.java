package avz.bz.ua.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import avz.bz.ua.dao.Manager;

public class CheckWorldsReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String nameOfJsp;
	private Manager manager;

	public CheckWorldsReserve() {
		super();
		manager = new Manager();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("saveMe") != null) {
			manager.saveAll();
		}

		nameOfJsp = request.getParameter("target") + ".jsp";

		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/pages/"
				+ nameOfJsp);
		rq.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
