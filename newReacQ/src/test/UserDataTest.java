package test;

import dao.ReactionDao;
import model.Reaction;

public class UserDataTest {
	public static void main(String[] args) {
		//UserDataDao uDao = new UserDataDao();

		// selectP()のテスト
		//System.out.println("---------- selectP()のテスト ----------");
		//int pos = (int)uDao.selectP("Teacher1@email.jp");
		//System.out.println("position：" + pos);

		/*
		// ()のテスト
		System.out.println("---------- Board()のテスト ----------");
		BoardDao bDao = new BoardDao();
		List<Board> QList = bDao.selectmypage(new Board(0, "Student1@email.jp", 0, 0, "", "2021-06-15"));
		for (Board board : QList) {
			System.out.println("id：" + board.getId());
			System.out.println("email:" + board.getEmail());
			System.out.println("reply_status：" + board.getReply_status());
			System.out.println("qustion_code:" + board.getQuestion_code());
			System.out.println("question：" + board.getQuestion());
			System.out.println("reply_date：" + board.getReply_date());
		}


		// ()のテスト
		System.out.println("---------- Reaction()のテスト ----------");
		ReactionDao rDao = new ReactionDao();
		int reaction0 = rDao.countmypage("Student2@email.jp", 0, "2021-06-15");
		int reaction1 = rDao.countmypage("Student2@email.jp", 1, "2021-06-15");
		int reaction2 = rDao.countmypage("Student2@email.jp", 2, "2021-06-15");
		int reaction3 = rDao.countmypage("Student2@email.jp", 3, "2021-06-15");
		System.out.println("0:" + reaction0);
		System.out.println("1:" + reaction1);
		System.out.println("2:" + reaction2);
		System.out.println("3:" + reaction3);
		*/

		/*
		// ()のテスト
		System.out.println("---------- Board()のテスト ----------");
		BoardDao bDao = new BoardDao();
		List<Board> Alllist = bDao.select(new Board(0, "", 0, 0, "", ""));
		for (Board board : Alllist) {
			System.out.println("id：" + board.getId());
			System.out.println("email:" + board.getEmail());
			System.out.println("reply_status：" + board.getReply_status());
			System.out.println("qustion_code:" + board.getQuestion_code());
			System.out.println("question：" + board.getQuestion());
			System.out.println("reply_date：" + board.getReply_date());
		}
		*/

		/*
		// ()のテスト
		System.out.println("---------- Reaction()のテスト ----------");
		ReactionDao rDao = new ReactionDao();
		String date = rDao.reset(new Reaction(0, "Teacher1@email.jp", 0, ""));
		System.out.println("日時:" + date);
		*/

		// ()のテスト
		System.out.println("---------- Reaction()のテスト ----------");
		ReactionDao rDao = new ReactionDao();
		String date=rDao.reset(new Reaction (0,"",4,""));
		System.out.println("日時:" + date);

		int reaction0 = rDao.resetcount(0, date);
		int reaction1 = rDao.resetcount(1, date);
		int reaction2 = rDao.resetcount(2, date);
		int reaction3 = rDao.resetcount(3, date);
		System.out.println("0:" + reaction0);
		System.out.println("1:" + reaction1);
		System.out.println("2:" + reaction2);
		System.out.println("3:" + reaction3);

	}
}