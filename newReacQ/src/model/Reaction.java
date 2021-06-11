package model;

import java.io.Serializable;

public class Reaction implements Serializable {
	private int id;
	private String email;
	private String reaction;
	private String reply_date;

	//引数があるコンストラクタ
	public Reaction(int id, String email, String reaction, String reply_date) {
		super();
		this.id = id;
		this.email = email;
		this.reaction = reaction;
		this.reply_date = reply_date;
	}

	public Reaction() {
		super();
		this.id = 0;
		this.email = "";
		this.reaction = "";
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

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
}
