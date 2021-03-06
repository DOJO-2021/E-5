package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//このmodel.BCを書くことで以下でBCに略すことができる
import model.Reaction;

public class ReactionDao {
	/*
	 * リセットボタンを押してからのカウント
	 */
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public int resetcount(int reaction, String date) {
		Connection conn = null;
		int count;
		try {
			// h2に接続するためのJDBCドライバを読み込む
			// クラスっていう名前のクラス
			Class.forName("org.h2.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "select count(*) as cnt from reaction where reaction = ? and reply_date > ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, reaction);
			pStmt.setString(2, date);

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

	/*
	 * メニュー講師のリセットボタンを押した時間を取得
	 */
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public String reset(Reaction rea) {
		Connection conn = null;
		String date;
		try {
			// h2に接続するためのJDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "select reply_date from reaction where reaction = ? order by reply_date desc limit 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, rea.getReaction());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			date = rs.getString("reply_date");
		}
		//JDBS関連の
		catch (SQLException e) {
			e.printStackTrace();
			//検索結果は空っぽでしたって返す
			date = null;
		}
		//ドライバがない時の処理
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			date = null;;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					date = null;
				}
			}
		}
		// 結果を返す
		return date;
	}

	/*
	 * (mypage)受講者、日付ごとのリアクション表示
	 */
	public int countmypage(String email, int reaction, String date) {
		Connection conn = null;
		int count;
		try {
			// h2に接続するためのJDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "select count(*) as cnt from reaction where email = ? and reaction = ? and reply_date like ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, email);
			pStmt.setInt(2, reaction);
			pStmt.setString(3, date + "%");

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

	/*
	 * (mypageT)日付ごとのリアクションの表示
	 */
	public int countmyT(int reaction, String date) {
		Connection conn = null;
		int count;
		try {
			// h2に接続するためのJDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "select count(*) as cnt from reaction where reaction = ? and reply_date like ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, reaction);
			pStmt.setString(2, date + "%");

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

	/*
	 * リアクションの登録
	 */
	public boolean insert(Reaction reaction) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			String sql = "insert into Reaction values (null, ?, ?, CURRENT_TIMESTAMP(), ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if (reaction.getEmail() != null) {
				pStmt.setString(1, reaction.getEmail());
			}
			else {
				pStmt.setString(1, "null");
			}
			if (reaction.getReaction() != 0) {
				pStmt.setInt(2, reaction.getReaction());
			}
			else {
				pStmt.setInt(2, 0);
			}
			if (reaction.getReact_title() != null) {
				pStmt.setString(3, reaction.getReact_title());
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