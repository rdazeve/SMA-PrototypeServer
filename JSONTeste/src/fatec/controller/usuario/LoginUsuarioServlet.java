package fatec.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fatec.bd.dao.UsuarioDAO;
import fatec.model.Usuario;

@SuppressWarnings("serial")
public class LoginUsuarioServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String nome = req.getParameter("nome");
		String senha = req.getParameter("senha");

		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = null;

		Long id = 0l;

		user = dao.getByLogin(nome);

		if (user != null && user.getSenha().equals(senha)) {
			id = user.getId_user();
		} else {
			id = -1l;
		}

		resp.setContentType("application/json");

		PrintWriter out = resp.getWriter();

		out.print(id);
		out.flush();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
