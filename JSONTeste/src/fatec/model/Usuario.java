package fatec.model;

public class Usuario {

	private Long id_user = 0l;
	private String nome = "";
	private String senha = "";
	private Long reputacao = 0l;

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getReputacao() {
		return reputacao;
	}

	public void setReputacao(Long reputacao) {
		this.reputacao = reputacao;
	}

}
