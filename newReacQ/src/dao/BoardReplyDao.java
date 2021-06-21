package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BoardReply;

public class BoardReplyDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	//検索結果
	public List<BoardReply> select(int reply_code) {
		Connection conn = null;
		List<BoardReply> Replylist = new ArrayList<BoardReply>();

		try {

			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/ReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select id, email, q_reply_code, question_reply, reply_date from board_reply WHERE q_reply_code = ? ORDER BY id";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, reply_code);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				BoardReply b = new BoardReply(
					0,
					rs.getString("email"),
					rs.getInt("q_reply_code"),
					rs.getString("question_reply"),
					rs.getString("reply_date")
			);
			Replylist.add(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			Replylist = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			Replylist = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					Replylist = null;
				}
			}
		}
			// 結果を返す
		return Replylist;
	}

	public boolean insert(BoardReply b) {
		Connection conn = null;
		boolean result = false;
		//Timestamp ts = Timestamp.valueOf(b.getReply_date());

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "insert into Board_reply values (null, ?, ?, ?, CURRENT_TIMESTAMP())";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, b.getEmail());
			pStmt.setInt(2, b.getQ_reply_code());
			pStmt.setString(3, b.getQuestion_reply());

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
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/ReacQ", "sa", "");

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
}