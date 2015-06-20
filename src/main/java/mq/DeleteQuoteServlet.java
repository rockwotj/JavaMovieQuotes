package mq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/deletequote")
public class DeleteQuoteServlet extends HttpServlet {

	private static final long serialVersionUID = 2L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id;
		try {
			id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException exception) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		Database.getInstance().delete(id);
		resp.sendRedirect(req.getHeader("referer"));
	}
	
}
