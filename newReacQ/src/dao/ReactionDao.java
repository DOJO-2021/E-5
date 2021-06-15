package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

//このmodel.BCを書くことで以下でBCに略すことができる
import model.Reaction;

public class ReactionDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public int countmenu(int reaction, String date) {
		Connection conn = null;
		//リアクションの数を入れるコレクションを用意
		int count;
		Timestamp ts = Timestamp.valueOf(date);

		try {
			// h2に接続するためのJDBCドライバを読み込む
			// クラスっていう名前のクラス
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select reply_date, count(*) as cnt from reaction where reaction = ? and reply_date > ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, reaction);
			pStmt.setTimestamp(2, ts);

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

	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public int countmypage(String email, int reaction, String date) {
		Connection conn = null;
		//リアクションの数を入れるコレクションを用意
		int count;
		Timestamp ts = Timestamp.valueOf(date);

		try {
			// h2に接続するためのJDBCドライバを読み込む
			// クラスっていう名前のクラス
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select reply_date, count(*) as cnt from reaction where email = ? reaction = ? and reply_date like ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, email);
			pStmt.setInt(2, reaction);
			pStmt.setTimestamp(3, ts);

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

	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public int countmyT(int reaction, String date) {
		Connection conn = null;
		//リアクションの数を入れるコレクションを用意
		int count;
		Timestamp ts = Timestamp.valueOf(date);

		try {
			// h2に接続するためのJDBCドライバを読み込む
			// クラスっていう名前のクラス
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select reply_date, count(*) as cnt from reaction where reaction = ? and reply_date = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, reaction);
			pStmt.setTimestamp(2, ts);

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
	public boolean insert(Reaction reaction) {
		Connection conn = null;
		boolean result = false;
		Timestamp ts = Timestamp.valueOf(reaction.getReply_date());

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "insert into Reaction values (null, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (reaction.getEmail() != null) {
				pStmt.setString(1, reaction.getEmail());
			}
			else {
				pStmt.setString(1, "null");
			}
			if (reaction.getReaction() != null) {
				pStmt.setString(2, reaction.getReaction());
			}
			else {
				pStmt.setString(2, "null");
			}
			if (ts != null) {
				pStmt.setTimestamp(3, ts);
			}
			else {
				pStmt.setString(3, "null");
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
}