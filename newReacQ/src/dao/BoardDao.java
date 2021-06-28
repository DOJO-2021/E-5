package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.BoardAll;
import model.Like;

public class BoardDao {
	/*
	 *  検索結果を表示する
	 */
	public List<BoardAll> select(BoardAll param) {
		Connection conn = null;
		List<BoardAll> Alllist = new ArrayList<BoardAll>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "select b.id as bid, b.email as bemail, b.reply_status as bstatus, b.question_code as bcode, b.question as bq, b.reply_date as bdate, br.question_reply as brq, br.reply_date as brdate, count(l.id) as count, u.name as name from ((board as b left join board_reply as br on b.question_code = br.q_reply_code) left join likes as l on br.q_reply_code = l.question_code) LEFT JOIN user_data as u ON b.email = u.email where b.question like ? group by bid, l.question_code order by count desc, bdate desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if (param.getQuestion() != null) {
				pStmt.setString(1, "%" + param.getQuestion() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				BoardAll b = new BoardAll(
						0,
						rs.getString("bemail"),
						rs.getInt("bstatus"),
						rs.getInt("bcode"),
						rs.getString("bq"),
						rs.getString("bdate"),
						rs.getString("brq"),
						rs.getString("brdate"),
						rs.getInt("count"),
						rs.getString("name")
			);
			Alllist.add(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			Alllist = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			Alllist = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					Alllist = null;
				}
			}
		}
		// 結果を返す
		return Alllist;
	}

	/*
	 *  board、board_reply、likes全てを結合させた表の表示
	 */
	public List<BoardAll> boardJoin(BoardAll param) {
		Connection conn = null;
		List<BoardAll> resultlist = new ArrayList<BoardAll>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "SELECT b.id as bid, b.email as bemail, b.reply_status as bstatus, b.question_code as bcode, b.question as bq, b.reply_date as bdate, count(l.id) as count, br.question_reply as brq, br.reply_date as brdate, u.name as name FROM ((board as b LEFT JOIN likes as l ON b.question_code = l.question_code) LEFT JOIN board_reply as br ON br.q_reply_code = l.question_code) LEFT JOIN user_data as u ON b.email = u.email GROUP BY b.id, l.question_code ORDER BY count desc, bdate desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				BoardAll b = new BoardAll(
						0,
						rs.getString("bemail"),
						rs.getInt("bstatus"),
						rs.getInt("bcode"),
						rs.getString("bq"),
						rs.getString("bdate"),
						rs.getString("brq"),
						rs.getString("brdate"),
						rs.getInt("count"),
						rs.getString("name")
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

	/*
	 *  回答状況による表示
	 */
	public List<BoardAll> Allselect(BoardAll param) {
		Connection conn = null;
		List<BoardAll> resultlist = new ArrayList<BoardAll>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "SELECT b.id as bid, b.email as bemail, b.reply_status as bstatus, b.question_code as bcode, b.question as bq, b.reply_date as bdate, count(l.id) as count, br.question_reply as brq, br.reply_date as brdate, u.name as name FROM ((board as b LEFT JOIN likes as l ON b.question_code = l.question_code) LEFT JOIN board_reply as br ON br.q_reply_code = l.question_code) LEFT JOIN user_data as u ON b.email = u.email WHERE b.reply_status = ? GROUP BY b.id, l.question_code ORDER BY count desc, bdate desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, param.getReply_status());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				BoardAll b = new BoardAll(
						0,
						rs.getString("bemail"),
						rs.getInt("bstatus"),
						rs.getInt("bcode"),
						rs.getString("bq"),
						rs.getString("bdate"),
						rs.getString("brq"),
						rs.getString("brdate"),
						rs.getInt("count"),
						rs.getString("name")
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

	/*
	 * 自分がお気に入りした質問の表示
	 */
	public List<BoardAll> LikeAllselect(String email) {
		Connection conn = null;
		List<BoardAll> resultlist = new ArrayList<BoardAll>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "SELECT b.id as bid, b.email as bemail, b.reply_status as bstatus, b.question_code as bcode, b.question as bq, b.reply_date as bdate, count(l.id) as count, br.question_reply as brq, br.reply_date as brdate, u.name as name FROM ((board as b LEFT JOIN likes as l ON b.question_code = l.question_code) LEFT JOIN board_reply as br ON br.q_reply_code = l.question_code) LEFT JOIN user_data as u ON b.email = u.email WHERE l.email = ? GROUP BY b.id, l.question_code ORDER BY count desc, bdate desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, email);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				BoardAll b = new BoardAll(
						0,
						rs.getString("bemail"),
						rs.getInt("bstatus"),
						rs.getInt("bcode"),
						rs.getString("bq"),
						rs.getString("bdate"),
						rs.getString("brq"),
						rs.getString("brdate"),
						rs.getInt("count"),
						rs.getString("name")
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

	/*
	 * 最新の投稿
	 */
	public List<Board> selectlatest(Board board) {
		Connection conn = null;
		List<Board> newlist = new ArrayList<Board>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "select id, email, reply_status, question_code, question, reply_date  from board order by id desc limit 5";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Board b = new Board(
						0,
						rs.getString("email"),
						rs.getInt("reply_status"),
						rs.getInt("question_code"),
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

	/*
	 * 自分の最新の投稿
	 */
	public List<Board> selectmynewlist(Board board) {
		Connection conn = null;
		List<Board> mynewlist = new ArrayList<Board>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "select id, email, reply_status, question_code, question, reply_date  from board where email = ? order by id desc limit 2";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,board.getEmail());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Board b = new Board(
						0,
						rs.getString("email"),
						rs.getInt("reply_status"),
						rs.getInt("question_code"),
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

	/*
	 * (mypage)emailとdateで検索項目を指定し、検索結果のリストを返す
	 */
	public List<Board> selectmypage(Board myb) {
		Connection conn = null;
		List<Board> Qlist = new ArrayList<Board>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "select * from board WHERE email = ? and reply_date like ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, myb.getEmail());
			pStmt.setString(2, myb.getReply_date() + "%");

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
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

	/*
	 * 現在の質問数の合計
	 */
	public int count() {
		Connection conn = null;
		int count;
		try {
			// h2に接続するためのJDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select question_code from board order by question_code desc limit 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			count = rs.getInt("question_code");
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

	/*
	 *  引数cardで指定されたレコードを登録し、成功したらtrueを返す
	 */
	public boolean insert(Board board) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "insert into Board values (null, ?, ?, ?, ?, CURRENT_TIMESTAMP())";
			PreparedStatement pStmt = conn.prepareStatement(sql);

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

	/*
	 *  reply_satusの更新
	 */
	public boolean update(int code) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "update board set reply_status = 1 where question_code = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, code);

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

	/*
	 *  削除
	 */
	public boolean delete(int code) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "delete from Board where question_code = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, code);

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

	/*
	 * お気に入りの登録
	 */
	public boolean insertlike(Like like) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "insert into Likes values (null, ?, ?, CURRENT_TIMESTAMP())";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if (like.getEmail() != null) {
				pStmt.setString(1, like.getEmail());
			}
			else {
				pStmt.setString(1, "null");
			}
			if (like.getQuestion_code() != 0) {
				pStmt.setInt(2, like.getQuestion_code());
			}
			else {
				pStmt.setInt(2, 0);
			}

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

	/*
	 *  削除
	 */
	public boolean deletelike(int code) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "delete from likes where question_code = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, code);

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
}