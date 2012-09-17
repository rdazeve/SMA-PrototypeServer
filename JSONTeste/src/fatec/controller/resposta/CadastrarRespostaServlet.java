package fatec.controller.resposta;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fatec.bd.dao.RespostaDAO;
import fatec.model.Resposta;

@SuppressWarnings("serial")
public class CadastrarRespostaServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String ask_id = req.getParameter("ask_id");
		String user_id = req.getParameter("user_id");
		String user_name = req.getParameter("user_name");
		String asw_resposta = req.getParameter("asw_resposta");

		RespostaDAO dao = new RespostaDAO();
		Resposta resposta = new Resposta();

		resposta.setAsk_id(Long.parseLong(ask_id));
		resposta.setUser_id(Long.parseLong(user_id));
		resposta.setUser_name(user_name);
		resposta.setAsw_resposta(asw_resposta);
		

		try {
			dao.adicionar(resposta);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setContentType("application/json");

		PrintWriter out = resp.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object
		out.print("01");
		out.flush();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);

	}
}
