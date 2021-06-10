package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Board;

public class BoardDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
		public List<Board> select(Board param) {
			Connection conn = null;
			List<Board> Qlist = new ArrayList<Board>();
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ReacQ", "sa", "");

				// SQL文を準備する
				String sql = "select id, email, reply_status, question, reply_date  from Qlist WHERE question LIKE? ORDER BY id";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
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
					Board b = new Board(
							0,
							rs.getString("email"),
							rs.getInt("reply_status"),
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
}