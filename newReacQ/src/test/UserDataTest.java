package test;

import dao.BoardReplyDao;

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


		//request.setCharacterEncoding("UTF-8");
		//String date = request.getParameter("REPLY_DATE_R");

		System.out.println("---------- Reaction()のテスト ----------");
		ReactionDao rDao = new ReactionDao();
		int reaction0 = rDao.countmyT(0, "2021-06-15");
		int reaction1 = rDao.countmyT(1, "2021-06-15");
		int reaction2 = rDao.countmyT(2, "2021-06-15");
		int reaction3 = rDao.countmyT(3, "2021-06-15");

		System.out.println("0:" + reaction0);
		System.out.println("1:" + reaction1);
		System.out.println("2:" + reaction2);
		System.out.println("3:" + reaction3);


		BoardDao bDao = new BoardDao();
		int q_sum = bDao.count() +1;
		System.out.println("最新のquestion_code:" + q_sum);


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


		// ()のテスト
		System.out.println("---------- Reaction()のテスト ----------");
		ReactionDao rDao = new ReactionDao();
		String date=rDao.reset(new Reaction (0,"",4,"",""));
		System.out.println("日時:" + date);



		boolean result = rDao.insert(new Reaction(0, "Student1@email.jp", 0, "", "github"));
		System.out.println(result);

		int reaction0 = rDao.resetcount(0, date);
		int reaction1 = rDao.resetcount(1, date);
		int reaction2 = rDao.resetcount(2, date);
		int reaction3 = rDao.resetcount(3, date);
		System.out.println("0:" + reaction0);
		System.out.println("1:" + reaction1);
		System.out.println("2:" + reaction2);
		System.out.println("3:" + reaction3);


		// ()のテスト

		BoardDao bDao = new BoardDao();
		int q_code = bDao.q_code("たちつてと");
		System.out.println("q_code:" + q_code);


		BoardReplyDao brDao = new BoardReplyDao();
		boolean result = brDao.insert(new BoardReply(0, "Student2@email.com", q_code, "さしす", ""));
		System.out.println("insert:" + result);

		System.out.println("---------- Board()のテスト ----------");
		List<BoardAll> Alllist = bDao.boardJoin(new BoardAll(0, "", 0, 0, "", "", "", ""));
		for (BoardAll board : Alllist) {
			System.out.println("id：" + board.getId());
			System.out.println("email:" + board.getEmail());
			System.out.println("reply_status：" + board.getReply_status());
			System.out.println("qustion_code:" + board.getQuestion_code());
			System.out.println("question：" + board.getQuestion());
			System.out.println("reply_date：" + board.getReply_date());
			System.out.println("question：" + board.getQuestion_reply());
			System.out.println("reply_date：" + board.getBr_reply_date());
		}


		System.out.println("---------- Board()のテスト ----------");
		BoardDao bDao = new BoardDao();
		boolean result = bDao.insertlike(new Like(0, "Student2@email.jp", 1, ""));
		System.out.println(result);


		System.out.println("---------- Board()のテスト ----------");
		BoardDao bDao = new BoardDao();
		List<BoardAll> Alllist = bDao.Allselect(new BoardAll(0, "", 1, 0, "", "", "", "", 0));
		for (BoardAll board : Alllist) {
			System.out.println("id：" + board.getId());
			System.out.println("email:" + board.getEmail());
			System.out.println("reply_status：" + board.getReply_status());
			System.out.println("qustion_code:" + board.getQuestion_code());
			System.out.println("question：" + board.getQuestion());
			System.out.println("reply_date：" + board.getReply_date());
			System.out.println("question：" + board.getQuestion_reply());
			System.out.println("reply_date：" + board.getBr_reply_date());
		}


		System.out.println("---------- Board()のテスト ----------");
		BoardDao bDao = new BoardDao();
		boolean result = bDao.insertlike(new Like(0, "Student1@email.jp", 67, ""));
		System.out.println(result);
		List<BoardAll> Alllist = bDao.boardJoin();
		for (BoardAll board : Alllist) {
			System.out.println("id：" + board.getId());
			System.out.println("email:" + board.getEmail());
			System.out.println("reply_status：" + board.getReply_status());
			System.out.println("qustion_code:" + board.getQuestion_code());
			System.out.println("question：" + board.getQuestion());
			System.out.println("reply_date：" + board.getReply_date());
			System.out.println("questio_replyn：" + board.getQuestion_reply());
			System.out.println("reply_date：" + board.getBr_reply_date());
			System.out.println("count：" + board.getCount());
		}

		*/
		BoardReplyDao bDao = new BoardReplyDao();
		boolean result = bDao.confirm(5);
		System.out.println(result);
	}
}