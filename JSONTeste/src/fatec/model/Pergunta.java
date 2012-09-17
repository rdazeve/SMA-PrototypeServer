package fatec.model;

public class Pergunta {

	private Long ask_id;
	private Long user_id;
	private String ask_pergunta;
	private String user_name;
	private boolean status;

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

	public String getAsk_pergunta() {
		return ask_pergunta;
	}

	public void setAsk_pergunta(String ask_pergunta) {
		this.ask_pergunta = ask_pergunta;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
