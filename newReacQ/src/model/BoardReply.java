package model;
import java.io.Serializable;

public class BoardReply implements Serializable {
	private int id;
	private String question_reply;
	private String email;
	private String reply_date;

	//引数があるコンストラクタ
	public BoardReply(int id, String question_reply, String email, int reply_status, String question, String reply_date) {
		super();
		this.id = id;
		this.question_reply = question_reply;
		this.email = email;
		this.reply_date = reply_date;
	}

	//引数があるコンストラクタ
	public BoardReply() {
		super();
		this.id = 0;
		this.question_reply = "";
		this.email = "";
		this.reply_date = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion_reply() {
		return question_reply;
	}

	public void setQuestion_reply(String question_reply) {
		this.question_reply = question_reply;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}

}