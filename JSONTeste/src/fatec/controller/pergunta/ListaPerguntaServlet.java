package fatec.controller.pergunta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import fatec.bd.dao.PerguntaDAO;
import fatec.model.Pergunta;

public class ListaPerguntaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long idUser = Long.parseLong(req.getParameter("idUser"));

		PerguntaDAO pdao = new PerguntaDAO();
		ArrayList<Pergunta> vPerguntas = pdao.getList(idUser);

		JSONArray jArray = new JSONArray();
		JSONObject json = null;
		if (vPerguntas != null) {
			for (Pergunta p : vPerguntas) {
				json = new JSONObject();
				json.put("id", p.getAsk_id());
				json.put("user", p.getUser_id());
				json.put("pergunta", p.getAsk_pergunta());
				json.put("user_name", p.getUser_name());
				json.put("status", p.getStatus());
				jArray.add(json);
			}
		}

		resp.setContentType("application/json");

		PrintWriter out = resp.getWriter();

		out.print(jArray.toString());
		out.flush();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
