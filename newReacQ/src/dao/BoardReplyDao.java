package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BoardReply;

public class BoardReplyDao {
	/*
	 * 既に返信されているかどうかの確認
	 */
	public boolean confirm(int code) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "select count(id) as count from board_reply where q_reply_code = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, code);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			int count = rs.getInt("count");
			// SQL文を実行する
			if (count == 0) {
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


	public boolean insert(BoardReply b) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "insert into Board_reply values (null, ?, ?, ?, CURRENT_TIMESTAMP())";
			PreparedStatement pStmt = conn.prepareStatement(sql);

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

	/*
	 *  引数idで指定されたレコードを削除し、成功したらtrueを返す
	 */
	public boolean delete(int code) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/ReacQ", "sa", "");

			String sql = "delete from Board_reply where q_reply_code=?";
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