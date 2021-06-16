package test;

import java.util.List;

import dao.BoardDao;
import model.Board;

public class UserDataTest {
	public static void main(String[] args) {
		//UserDataDao uDao = new UserDataDao();

		// selectP()のテスト
		//System.out.println("---------- selectP()のテスト ----------");
		//int pos = (int)uDao.selectP("Teacher1@email.jp");
		//System.out.println("position：" + pos);

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
	}
}