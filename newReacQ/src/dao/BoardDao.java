package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Board;

public class BoardDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	//検索結果
	public List<Board> select(Board param) {
		Connection conn = null;
		List<Board> resultlist = new ArrayList<Board>();

		try {

			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select id, email, reply_status, question_code, question, reply_date  from board WHERE reply_status = ? and question LIKE? ORDER BY id";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (param.getReply_status() != 0) {
				pStmt.setInt(1, param.getReply_status());
			}
			else {
				pStmt.setInt(1, 0);
			}
			if (param.getQuestion() != null) {
				pStmt.setString(2, "%" + param.getQuestion() + "%");
			}
			else {
				pStmt.setString(2, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Board b = new Board(
						0,
						rs.getString("email"),
						rs.getInt("reply_status"),
						rs.getInt("qustion_code"),
						rs.getString("question"),
						rs.getString("reply_date")
			);
			resultlist.add(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			resultlist = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			resultlist = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					resultlist = null;
				}
			}
		}
			// 結果を返す
		return resultlist;
	}


	//最新を出す
	public List<Board> selectlatest() {
		Connection conn = null;
		List<Board> newlist = new ArrayList<Board>();

		try {

			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select id, email, reply_status, question_code, question, reply_date  from board order by id desc limit 5";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Board b = new Board(
						0,
						rs.getString("email"),
						rs.getInt("reply_status"),
						rs.getInt("qustion_code"),
						rs.getString("question"),
						rs.getString("reply_date")
			);
			newlist.add(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			newlist = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			newlist = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					newlist = null;
				}
			}
		}
			// 結果を返す
		return newlist;
	}


	//自分の最新を出す
	public List<Board> selectmynew(String email) {
		Connection conn = null;
		List<Board> mynewlist = new ArrayList<Board>();

		try {

			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select id, email, reply_status, question_code, question, reply_date  from board where email = ? order by id desc limit 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, email);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Board b = new Board(
						0,
						rs.getString("email"),
						rs.getInt("reply_status"),
						rs.getInt("qustion_code"),
						rs.getString("question"),
						rs.getString("reply_date")
			);
			mynewlist.add(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			mynewlist = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			mynewlist = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					mynewlist = null;
				}
			}
		}
			// 結果を返す
		return mynewlist;
	}

	// dateで検索項目を指定し、検索結果のリストを返す
	//mypageTで使用
	public List<Board> selectmenu(String date) {
		Connection conn = null;
		List<Board> Qlist = new ArrayList<Board>();
		Timestamp ts = Timestamp.valueOf(date);

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select id, email, reply_status, qustion_code, question, reply_date  from board WHERE reply_date = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setTimestamp(1, ts);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Board b = new Board(
					0,
					rs.getString("email"),
					rs.getInt("reply_status"),
					rs.getInt("qustion_code"),
					rs.getString("question"),
					rs.getString("reply_date")
				);
			Qlist.add(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			Qlist = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			Qlist = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					Qlist = null;
				}
			}
		}
			// 結果を返す
		return Qlist;
	}

	// emailとdateで検索項目を指定し、検索結果のリストを返す
	// mypageで使用
	public List<Board> selectmypage(Board myb) {
		Connection conn = null;
		List<Board> Qlist = new ArrayList<Board>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select * from board WHERE email = ? and reply_date like ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, myb.getEmail());
			//pStmt.setTimestamp(2, ts);
			pStmt.setString(2, myb.getReply_date() + "%");

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Board b = new Board(
					rs.getInt("id"),
					rs.getString("email"),
					rs.getInt("reply_status"),
					rs.getInt("question_code"),
					rs.getString("question"),
					rs.getString("reply_date")
				);
			Qlist.add(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			Qlist = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			Qlist = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					Qlist = null;
				}
			}
		}
			// 結果を返す
		return Qlist;
	}

	// 現在の質問数の合計
	public int count() {
		Connection conn = null;
		//リアクションの数を入れるコレクションを用意
		int count;

		try {
			// h2に接続するためのJDBCドライバを読み込む
			// クラスっていう名前のクラス
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select reply_date, count(*) as cnt from board";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			rs.next();
			count = rs.getInt("cnt");
		}
		//JDBS関連の
		catch (SQLException e) {
			e.printStackTrace();
			//検索結果は空っぽでしたって返す
			count = 0;
		}
		//ドライバがない時の処理
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			count = 0;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					count = 0;
				}
			}
		}

		// 結果を返す
		return count;
	}

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Board board) {
		Connection conn = null;
		boolean result = false;
		Timestamp ts = Timestamp.valueOf(board.getReply_date());

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "insert into Board values (null, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (board.getEmail() != null) {
				pStmt.setString(1, board.getEmail());
			}
			else {
				pStmt.setString(1, "null");
			}
			if (board.getReply_status() != 0) {
				pStmt.setInt(2, board.getReply_status());
			}
			else {
				pStmt.setInt(2, 0);
			}
			if (board.getQuestion_code() != 0) {
				pStmt.setInt(3, board.getQuestion_code());
			}
			else {
				pStmt.setInt(3, 0);
			}
			if (board.getQuestion() != null) {
				pStmt.setString(4, board.getQuestion());
			}
			else {
				pStmt.setString(4, "null");
			}
			if (ts != null) {
				pStmt.setTimestamp(5, ts);
			}
			/*
			 else {
				pStmt.setTimestamp(5, 0000-00-00 00:00:00);
			}
			*/

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
			// 結果を返す
		return result;
	}

	// 引数idで指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(int id) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "delete from Board where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, id);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	//「気になる」の数を集計
	public List<Board> selectlike(String email) {
		Connection conn = null;
		List<Board> Likelist = new ArrayList<Board>();

		try {

			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select email from likes";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, "email");

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Board b = new Board(
						0,
						rs.getString("email"),
						rs.getInt("reply_status"),
						rs.getInt("qustion_code"),
						rs.getString("question"),
						rs.getString("reply_date")
			);
			Likelist.add(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			Likelist = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			Likelist = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					Likelist = null;
				}
			}
		}
			// 結果を返す
		return Likelist;
	}
}