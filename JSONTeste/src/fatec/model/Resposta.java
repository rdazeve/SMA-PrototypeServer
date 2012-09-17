package fatec.model;

public class Resposta {

	private Long asw_id;
	private Long ask_id;
	private Long user_id;
	private String asw_resposta;
	private String user_name;

	public Long getAsw_id() {
		return asw_id;
	}

	public void setAsw_id(Long asw_id) {
		this.asw_id = asw_id;
	}

	public Long getAsk_id() {
		return ask_id;
	}

	public void setAsk_id(Long ask_id) {
		this.ask_id = ask_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getAsw_resposta() {
		return asw_resposta;
	}

	public void setAsw_resposta(String asw_resposta) {
		this.asw_resposta = asw_resposta;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}