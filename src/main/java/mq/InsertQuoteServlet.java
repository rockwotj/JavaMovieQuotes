package mq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insertquote")
public class InsertQuoteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int id;
		String movie = req.getParameter("movie");
		String quote = req.getParameter("quote");
		try {
			id = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException exception) {
			// Must be a new quote
			id = -1;
		}
		MovieQuote mq = new MovieQuote(id, movie, quote);
		Database.getInstance().insert(mq);
		resp.sendRedirect(req.getHeader("referer"));
	}
	
}
