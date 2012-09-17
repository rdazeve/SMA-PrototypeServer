package fatec.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Server
 */
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Server() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("message", "Esta funcionando o JSON");

		if (request.getParameter("nome") != null) {
			System.out.println(request.getParameter("nome"));
		}

		JSONObject json = JSONObject.fromObject(hm);
		JSONArray jArray = new JSONArray();
		jArray.add(json);

		System.out.println(json);

		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		out.print(jArray);
		out.flush();
	}

}
