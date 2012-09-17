package fatec.bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fatec.bd.connection.ConnectionFactory;
import fatec.model.Resposta;

public class RespostaDAO {

	public void adicionar(Resposta answ) throws Exception {
		Connection con = ConnectionFactory.createConnection();
		String sql = "insert into resposta (ask_id, user_id, asw_resposta, user_name) values (?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, answ.getAsk_id());
		st.setLong(2, answ.getUser_id());
		st.setString(3, answ.getAsw_resposta());
		st.setString(4, answ.getUser_name());
		st.execute();
		st.close();
		con.close();
	}

	public void remover(Long id_answ) {
		try {
			Connection con = ConnectionFactory.createConnection();
			String sql = "delete from resposta where user_id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id_answ);
			st.execute();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Resposta> getList() {
		try {
			Connection con = ConnectionFactory.createConnection();
			ArrayList<Resposta> resposta = new ArrayList<Resposta>();
			String sql = "select * from resposta";
			PreparedStatement st = con.prepareStatement(sql);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Resposta answer = new Resposta();
				answer.setAsw_id(rs.getLong("asw_id"));
				answer.setAsk_id(rs.getLong("ask_id"));
				answer.setUser_id(rs.getLong("user_id"));
				answer.setAsw_resposta(rs.getString("asw_resposta"));
				answer.setUser_name(rs.getString("user_name"));
				resposta.add(answer);
			}
			st.close();
			con.close();
			return resposta;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Resposta> getListByPergunta(long ask_id) {
		try {
			Connection con = ConnectionFactory.createConnection();
			ArrayList<Resposta> resposta = new ArrayList<Resposta>();
			String sql = "select * from resposta where ask_id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, ask_id);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Resposta answer = new Resposta();
				answer.setAsw_id(rs.getLong("asw_id"));
				answer.setAsk_id(rs.getLong("ask_id"));
				answer.setUser_id(rs.getLong("user_id"));
				answer.setAsw_resposta(rs.getString("asw_resposta"));
				answer.setUser_name(rs.getString("user_name"));
				resposta.add(answer);
			}
			st.close();
			con.close();
			return resposta;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int update(Resposta answer) {
		try {
			Connection con = ConnectionFactory.createConnection();
			String sql = "update resposta set ask_id=?, user_id=?, asw_resposta=?, user_name=? where asw_id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, answer.getAsk_id());
			st.setLong(2, answer.getUser_id());
			st.setString(3, answer.getAsw_resposta());
			st.setString(4, answer.getUser_name());
			st.setLong(5, answer.getAsw_id());
			int updated = st.executeUpdate();
			st.close();
			con.close();
			return updated;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Resposta getResposta(Long id_answer) {
		try {
			Connection con = ConnectionFactory.createConnection();
			String sql = "select * from resposta where asw_id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id_answer);
			st.execute();
			ResultSet rs = st.getResultSet();
			Resposta resp = new Resposta();
			rs.next();
			resp.setAsw_id(id_answer);
			resp.setAsk_id(rs.getLong("ask_id"));
			resp.setUser_id(rs.getLong("user_id"));
			resp.setAsw_resposta(rs.getString("asw_resposta"));
			resp.setUser_name(rs.getString("user_name"));
			rs.close();
			st.close();
			con.close();
			return resp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}