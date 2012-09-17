package fatec.bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fatec.bd.connection.ConnectionFactory;
import fatec.model.Pergunta;

public class PerguntaDAO {

	public void adicionar(Pergunta ask) throws Exception {
		Connection con = ConnectionFactory.createConnection();
		String sql = "insert into pergunta (user_id, ask_pergunta, user_name) values (?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, ask.getUser_id());
		st.setString(2, ask.getAsk_pergunta());
		st.setString(3, ask.getUser_name());
		st.execute();
		st.close();
		con.close();
	}

	public void remover(Long id_ask) {
		try {
			Connection con = ConnectionFactory.createConnection();
			String sql = "delete from pergunta where user_id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id_ask);
			st.execute();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Pergunta> getList(long idUser) {
		try {
			Connection con = ConnectionFactory.createConnection();
			ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();
			String sql = 
					"select * from pergunta p1 where p1.user_id = ? " +
					"union " +
					"select * from pergunta p2 where p2.user_id <> ? " +
					"and " +
					"p2.status = 1 " +
					"and " +
					"(select count(*) from resposta where ask_id = p2.ask_id and user_id = ?) = 0";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, idUser);
			st.setLong(2, idUser);
			st.setLong(3, idUser);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Pergunta ask = new Pergunta();
				ask.setAsk_id(rs.getLong("ask_id"));
				ask.setUser_id(rs.getLong("user_id"));
				ask.setAsk_pergunta(rs.getString("ask_pergunta"));
				ask.setUser_name(rs.getString("user_name"));
				ask.setStatus(rs.getBoolean("status"));
				pergunta.add(ask);
			}
			st.close();
			con.close();
			return pergunta;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int update(Pergunta ask) {
		try {
			Connection con = ConnectionFactory.createConnection();
			String sql = "update pergunta set user_id=?, ask_pergunta=?, status=? where ask_id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, ask.getUser_id());
			st.setString(2, ask.getAsk_pergunta());
			st.setInt(3, ask.getStatus()? 1 : 0);
			st.setLong(4, ask.getAsk_id());
			int updated = st.executeUpdate();
			st.close();
			con.close();
			return updated;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Pergunta getPergunta(Long id_ask) {
		try {
			Connection con = ConnectionFactory.createConnection();
			String sql = "select * from pergunta where ask_id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id_ask);
			st.execute();
			ResultSet rs = st.getResultSet();
			Pergunta ask = new Pergunta();
			rs.next();
			ask.setAsk_id(id_ask);
			ask.setUser_id(rs.getLong("user_id"));
			ask.setAsk_pergunta(rs.getString("ask_pergunta"));
			rs.close();
			st.close();
			con.close();
			return ask;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}