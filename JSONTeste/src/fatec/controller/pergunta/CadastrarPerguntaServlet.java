package fatec.controller.pergunta;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fatec.bd.dao.PerguntaDAO;
import fatec.model.Pergunta;

@SuppressWarnings("serial")
public class CadastrarPerguntaServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String user_id = req.getParameter("user_id");
		String pergunta = req.getParameter("pergunta");
		String user_name = req.getParameter("user_name");

		PerguntaDAO pao = new PerguntaDAO();
		Pergunta perg = new Pergunta();
		perg.setUser_id(Long.parseLong(user_id));
		perg.setAsk_pergunta(pergunta);
		perg.setUser_name(user_name);

		try {
			pao.adicionar(perg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setContentType("application/json");

		PrintWriter out = resp.getWriter();
		
		out.print("01");
		out.flush();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);

	}
}
