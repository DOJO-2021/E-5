package model;
import java.io.Serializable;

public class BoardReply implements Serializable {
	private int id;
	private String email;
	private int q_reply_code;
	private String question_reply;
	private String reply_date;

	//引数があるコンストラクタ
	public BoardReply(int id, String email, int q_reply_code,  String question_reply, String reply_date) {
		super();
		this.id = id;
		this.email = email;
		this.q_reply_code = q_reply_code;
		this.question_reply = question_reply;
		this.reply_date = reply_date;
	}

	//引数があるコンストラクタ
	public BoardReply() {
		super();
		this.id = 0;
		this.email = "";
		this.q_reply_code = 0;
		this.question_reply = "";
		this.reply_date = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getQ_reply_code() {
		return q_reply_code;
	}

	public void setQ_reply_code(int q_reply_code) {
		this.q_reply_code = q_reply_code;
	}

	public String getQuestion_reply() {
		return question_reply;
	}

	public void setQuestion_reply(String question_reply) {
		this.question_reply = question_reply;
	}

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}

}