package model;
import java.io.Serializable;

public class BoardAll implements Serializable {
	private int id;
	private String email;
	private int reply_status;
	private int question_code;
	private String question;
	private String reply_date;
	private String question_reply;
	private String br_reply_date;

	//引数があるコンストラクタ
	public BoardAll(int id, String email, int reply_status, int question_code, String question, String reply_date, String question_reply, String br_reply_date) {
		super();
		this.id = id;
		this.email = email;
		this.reply_status = reply_status;
		this.question_code = question_code;
		this.question = question;
		this.reply_date = reply_date;
		this.question_reply = question_reply;
		this.br_reply_date = br_reply_date;
	}

	//引数があるコンストラクタ
	public BoardAll() {
		super();
		this.id = 0;
		this.email = "";
		this.reply_status = 0;
		this.question_code = 0;
		this.question = "";
		this.reply_date = "";
		this.question_reply = "";
		this.br_reply_date = "";
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

	public int getReply_status() {
		return reply_status;
	}

	public void setReply_status(int reply_status) {
		this.reply_status = reply_status;
	}

	public int getQuestion_code() {
		return question_code;
	}

	public void setQuestion_code(int question_code) {
		this.question_code = question_code;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}

	public String getQuestion_reply() {
		return question_reply;
	}

	public void setQuestion_reply(String question_reply) {
		this.question_reply = question_reply;
	}

	public String getBr_reply_date() {
		return br_reply_date;
	}

	public void setBr_reply_date(String br_reply_date) {
		this.br_reply_date = br_reply_date;
	}

}
