package fatec.controller.resposta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fatec.bd.dao.RespostaDAO;
import fatec.model.Resposta;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ListaRespostaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RespostaDAO rdao = new RespostaDAO();
		ArrayList<Resposta> vRespostas = rdao.getList();

		JSONArray jArray = new JSONArray();
		JSONObject json = null;
		if (vRespostas != null) {
			for (Resposta p : vRespostas) {
				json = new JSONObject();
				json.put("id", p.getAsw_id());
				json.put("pergunta", p.getAsk_id());
				json.put("usuario", p.getUser_id());
				json.put("user_name", p.getUser_name());
				json.put("resposta", p.getAsw_resposta());
				jArray.add(json);
			}
		}

		resp.setContentType("application/json");

		PrintWriter out = resp.getWriter();

		out.print(jArray.toString());
		out.flush();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);

	}

}
