package fatec.controller.pergunta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fatec.bd.dao.PerguntaDAO;
import fatec.bd.dao.RespostaDAO;
import fatec.bd.dao.UsuarioDAO;
import fatec.model.Pergunta;
import fatec.model.Resposta;

@SuppressWarnings("serial")
public class FinalizarPerguntaServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long ask_id = Long.parseLong(req.getParameter("ask_id"));
		String veredito = req.getParameter("veredito");

		PerguntaDAO pdao = new PerguntaDAO();
		Pergunta perg = pdao.getPergunta(ask_id);
		perg.setStatus(false);
		
		try {
			pdao.update(perg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		UsuarioDAO udao = new UsuarioDAO();
		RespostaDAO rdao = new RespostaDAO();
		ArrayList<Resposta> vResposta = rdao.getListByPergunta(ask_id);
		
		for (Resposta r : vResposta) {
			long user_id = r.getUser_id();
			if (r.getAsw_resposta().equals(veredito)) {
				udao.inserirReputacao(user_id, true);
			} else {
				udao.inserirReputacao(user_id, false);
			}
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
