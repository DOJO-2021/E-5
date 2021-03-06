package model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    //private String name;
    //private int position;

	//引数があるコンストラクタ
	public User(String email, String password) {
		this.email = email;
		this.password = password;
		//this.position = position;
	}

	//引数がないコンストラクタ（デフォルトコンストラクタ）
	public User() {
		this.email = null;
		this.password = null;
		//this.position = 0;
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
	/*
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
	*/


}