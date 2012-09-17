package fatec.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import fatec.bd.dao.UsuarioDAO;
import fatec.model.Usuario;

public class ListaUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UsuarioDAO udao = new UsuarioDAO();
		ArrayList<Usuario> vUsuarios = udao.getList();

		JSONArray jArray = new JSONArray();
		JSONObject json = null;
		for (Usuario u : vUsuarios) {
			json = new JSONObject();
			json.put("id", u.getId_user());
			json.put("nome", u.getNome());
			json.put("senha", u.getSenha());
			jArray.add(json);
		}

		resp.setContentType("application/json");

		PrintWriter out = resp.getWriter();

		out.print(jArray);
		out.flush();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}
}
