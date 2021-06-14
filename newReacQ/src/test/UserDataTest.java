package test;

import dao.UserDataDao;

public class UserDataTest {
	public static void main(String[] args) {
		UserDataDao uDao = new UserDataDao();

		// selectP()のテスト
		System.out.println("---------- selectP()のテスト ----------");
		int pos = (int)uDao.selectP("Teacher1@email.jp");
		System.out.println("position：" + pos);

		// ()のテスト

	}
}