package model;

import java.io.Serializable;

public class Reaction implements Serializable {
	private int id;
	private String email;
	private int reaction;
	private String reply_date;
	private String react_title;

	//引数があるコンストラクタ
	public Reaction(int id, String email, int reaction, String reply_date, String react_title) {
		super();
		this.id = id;
		this.email = email;
		this.reaction = reaction;
		this.reply_date = reply_date;
		this.react_title = react_title;
	}

	public Reaction() {
		super();
		this.id = 0;
		this.email = "";
		this.reaction = 0;
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

	public int getReaction() {
		return reaction;
	}

	public void setReaction(int reaction) {
		this.reaction = reaction;
	}

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}

	public String getReact_title() {
		return react_title;
	}

	public void setReact_title(String react_title) {
		this.react_title = react_title;
	}
}