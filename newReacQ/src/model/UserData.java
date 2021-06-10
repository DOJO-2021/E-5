package model;

import java.io.Serializable;

public class UserData implements Serializable {
	private int id;
	private String email;
	private String password;
	private String name;
	private int position;

	//引数があるコンストラクタ
	public UserData(int id, String email, String password, String name, int position) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.position = position;
	}

	//引数がないコンストラクタ（デフォルトコンストラクタ）
	public UserData() {
		super();
		this.id = 0;
		this.email = "";
		this.password = "";
		this.name = "";
		this.position = 0;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}