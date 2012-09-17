package fatec.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import fatec.bd.dao.UsuarioDAO;
import fatec.model.Usuario;

@SuppressWarnings("serial")
public class DadosUsuarioServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String user_id = req.getParameter("user_id");

		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = dao.getUsuario(Long.parseLong(user_id));
		
		
		JSONObject json = new JSONObject();
		try {
			json.put("usuario", user.getNome());
			json.put("reputacao", user.getReputacao());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		resp.setContentType("application/json");

		PrintWriter out = resp.getWriter();

		out.print(json);
		out.flush();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
