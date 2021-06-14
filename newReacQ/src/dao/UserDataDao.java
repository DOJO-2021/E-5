package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//このmodel.BCを書くことで以下でBCに略すことができる
import model.UserData;

//ログインの処理
public class UserDataDao {
	// ログインできるならtrueを返す
	public boolean isLoginOK(String email, String password) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SELECT文を準備する
			String sql = "select count(*) as cnt from user_data where email = ? and password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, email);
			pStmt.setString(2, password);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
			rs.next();
			if (rs.getInt("cnt") == 1) {
				loginResult = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return loginResult;
	}


	//emailとpasswordからpositionを特定する
	// 引数userで検索項目を指定し、検索結果のリストを返す
	public int selectP(String email) {
		Connection conn = null;
		int position;


		try {
			// h2に接続するためのJDBCドライバを読み込む
			// クラスっていう名前のクラス
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select position from user_data where email=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, email);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			//System.out.println(rs.toString());
			//position = rs.getByte("position");

			rs.next();
			position = rs.getInt("position");

		}

		//position = 0;
		//JDBS関連の
		catch (SQLException e) {
			e.printStackTrace();
			position = 2;
		}
		//ドライバがない時の処理
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			position = 2;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					position = 2;
				}
			}
		}
		// 結果を返す
		return position;
	}


	//emailで人を特定する
	// 引数userで検索項目を指定し、検索結果のリストを返す
	public List<UserData> select(UserData user) {
		Connection conn = null;
		//検索結果を入れるコレクションを用意
		List<UserData> userList = new ArrayList<UserData>();

		try {
			// h2に接続するためのJDBCドライバを読み込む
			// クラスっていう名前のクラス
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "select * from user_data where email = ? and password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, user.getEmail());
			pStmt.setString(2, user.getPassword());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				UserData userdata = new UserData(
				rs.getInt("id"),
				rs.getString("email"),
				rs.getString("password"),
				rs.getString("name"),
				rs.getInt("position")
				);
				userList.add(userdata);
			}
		}
		//JDBS関連の
		catch (SQLException e) {
			e.printStackTrace();
			//検索結果は空っぽでしたって返す
			userList = null;
		}
		//ドライバがない時の処理
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			userList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					userList = null;
				}
			}
		}

		// 結果を返す
		return userList;
	}

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(UserData user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "insert into user_data values (null, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, user.getEmail());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getName());
			pStmt.setInt(4, user.getPosition());


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

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(UserData user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "update user_data set email=?, password=?, name=? where email=? and password=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (user.getEmail() != null) {
				pStmt.setString(1, user.getEmail());
			}
			else {
				pStmt.setString(1, "null");
			}
			if (user.getPassword() != null) {
				pStmt.setString(2, user.getPassword());
			}
			else {
				pStmt.setString(2, "null");
			}
			if (user.getName() != null) {
				pStmt.setString(3, user.getName());
			}
			else {
				pStmt.setString(3, "null");
			}
			pStmt.setString(4, user.getEmail());
			pStmt.setString(5, user.getPassword());

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
	public boolean delete(UserData user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/E-5/newReacQ", "sa", "");

			// SQL文を準備する
			String sql = "delete from user_data where email=? and password=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, user.getEmail());
			pStmt.setString(2, user.getPassword());

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

