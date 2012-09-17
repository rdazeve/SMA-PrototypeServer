package fatec.bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fatec.bd.connection.ConnectionFactory;
import fatec.model.Usuario;

public class UsuarioDAO {

	public void adicionar(Usuario user) throws Exception {
		Connection con = ConnectionFactory.createConnection();
		String sql = "insert into usuario (user_nome, user_senha) values (?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, user.getNome());
		st.setString(2, user.getSenha());
		st.execute();
		st.close();
		con.close();
	}

	public void remover(Long id_user) {
		try {
			Connection con = ConnectionFactory.createConnection();
			String sql = "delete from usuario where user_id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id_user);
			st.execute();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Usuario> getList() {
		try {
			Connection con = ConnectionFactory.createConnection();
			ArrayList<Usuario> usuario = new ArrayList<Usuario>();
			String sql = "select * from usuario";
			PreparedStatement st = con.prepareStatement(sql);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId_user(rs.getLong("user_id"));
				user.setNome(rs.getString("user_nome"));
				user.setSenha(rs.getString("user_senha"));
				usuario.add(user);
			}
			st.close();
			con.close();
			return usuario;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int update(Usuario user) {
		try {
			Connection con = ConnectionFactory.createConnection();
			String sql = "update usuario set user_senha=?, user_nome=? where user_id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getSenha());
			st.setString(2, user.getNome());
			st.setLong(3, user.getId_user());
			int updated = st.executeUpdate();
			st.close();
			con.close();
			return updated;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Usuario getUsuario(Long id_user) {
		try {
			Connection con = ConnectionFactory.createConnection();
			String sql = "select * from usuario where user_id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id_user);
			st.execute();
			ResultSet rs = st.getResultSet();
			Usuario user = new Usuario();
			rs.next();
			user.setId_user(id_user);
			user.setNome(rs.getString("user_nome"));
			user.setSenha(rs.getString("user_senha"));
			user.setReputacao(rs.getLong("reputacao"));
			rs.close();
			st.close();
			con.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario getByLogin(String username) {
		try {
			Connection con = ConnectionFactory.createConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from usuario where user_nome='" + username + "'");
			Usuario user = new Usuario();
			if (rs.next()) {
				user.setId_user(rs.getLong("user_id"));
				user.setNome(rs.getString("user_nome"));
				user.setSenha(rs.getString("user_senha"));
			}
			rs.close();
			st.close();
			con.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int inserirReputacao(long user_id, boolean tipo) {
		try {
			Connection con = ConnectionFactory.createConnection();
			String sql;
			if (tipo) {
				sql = "update usuario set reputacao = (reputacao + 1) where user_id = ?";
			} else {
				sql = "update usuario set reputacao = (reputacao - 1) where user_id = ?";
			}
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, user_id);
			int updated = st.executeUpdate();
			st.close();
			con.close();
			return updated;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}