package model;
import java.io.Serializable;

public class Like implements Serializable {
	private int id;
	private String email;
	private int question_code;
	private String reply_date;

	//引数があるコンストラクタ
	public Like(int id, String email, int question_code, String reply_date) {
		super();
		this.id = id;
		this.email = email;
		this.question_code = question_code;
		this.reply_date = reply_date;
	}

	//引数があるコンストラクタ
	public Like() {
		super();
		this.id = 0;
		this.email = "";
		this.question_code = 0;
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

	public int getQuestion_code() {
		return question_code;
	}

	public void setQuestion_code(int question_code) {
		this.question_code = question_code;
	}

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
}