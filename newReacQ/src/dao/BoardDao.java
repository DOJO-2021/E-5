package dao;
​
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
​
import model.Board;
​
public class BoardDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
		public List<> select( param) {
			Connection conn = null;
			List<> cardList = new ArrayList<>();
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");
​
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ReacQ", "sa", "");
​
				// SQL文を準備する
				String sql = "select id,  from  WHERE ORDER BY id";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (param.get() != null) {
					pStmt.setString(1, "%" + param.get() + "%");
				}
				else {
					pStmt.setString(1, "%");
				}
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
​
				// 結果表をコレクションにコピーする
				while (rs.next()) {
					 /* リスト名 変数 */  = new /* 変数 */(
							rs.getInt("id"),
​
					);
					/* リスト名 */.add();
				}
}
