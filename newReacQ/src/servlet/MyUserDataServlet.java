package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.BoardDao;
import dao.ReactionDao;
import dao.UserDataDao;
import model.Board;
import model.Result;
import model.UserData;

/**
 * Servlet implementation class MyUserDataServlet
 */
@WebServlet("/MyUserDataServlet")
public class MyUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/newReacQ/LoginServlet");
			return;
		}

		String pos = (String)session.getAttribute("position");

		//講師か受講者かを判別するpositionを取得
		if (pos.equals("1")) {

			/*
			 * ユーザデータの表示
			 */
			String email = (String)session.getAttribute("email");

			// 処理を行う
			UserDataDao uDao = new UserDataDao();
			List<UserData> userList = uDao.select(email);

			//リクエストスコープに格納する
			request.setAttribute("userList", userList);

			/*
			 * 掲示板の表示
			 */
			// 処理を行う(select、emailが受講者、日付も選択可)
			// 処理を行う(select、日付選択可)
			Date d = new Date();
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	        String date = dt.format(d);

			BoardDao bDao = new BoardDao();
			List<Board> myboardList = bDao.selectmypage(new Board(0, email, 0, 0, "", date));

			//リクエストスコープに格納する
			request.setAttribute("myboardList", myboardList);

			/*
			 * リアクションの表示
			 */
			// 処理を行う(select、emailが受講者、日付も選択可)
			//セッションアトリビュートでemailを取得

			ReactionDao rDao = new ReactionDao();
			int reaction0 = rDao.countmypage(email, 0, date);
			int reaction1 = rDao.countmypage(email, 1, date);
			int reaction2 = rDao.countmypage(email, 2, date);
			int reaction3 = rDao.countmypage(email, 3, date);

			//リクエストスコープに格納する
			request.setAttribute("myrea0", reaction0);
			request.setAttribute("myrea1", reaction1);
			request.setAttribute("myrea2", reaction2);
			request.setAttribute("myrea3", reaction3);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			dispatcher.forward(request, response);
		}

		else if (pos.equals("0")) {

			String email = (String)session.getAttribute("email");

			// 処理を行う
			UserDataDao uDao = new UserDataDao();
			List<UserData> userList = uDao.select(email);

			//リクエストスコープに格納する
			request.setAttribute("userList", userList);

			/*
			 * リアクションの表示
			 */
			// 処理を行う(select、日付選択可)
			Date d = new Date();
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	        String date = dt.format(d);

			ReactionDao rDao = new ReactionDao();
			int reaction0 = rDao.countmyT(0, date);
			int reaction1 = rDao.countmyT(1, date);
			int reaction2 = rDao.countmyT(2, date);
			int reaction3 = rDao.countmyT(3, date);

			//リクエストスコープに格納する
			request.setAttribute("myrea0", reaction0);
			request.setAttribute("myrea1", reaction1);
			request.setAttribute("myrea2", reaction2);
			request.setAttribute("myrea3", reaction3);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypageT.jsp");
			dispatcher.forward(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	**/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/nerReacQ/LoginServlet");
			return;
		}

		//講師か受講者かを判別するpositionを取得
		String pos =(String)session.getAttribute("position");
		if (pos.equals("1")) {

			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			int id = Integer.parseInt(request.getParameter("ID"));
			String email = request.getParameter("EMAIL");
			String password = request.getParameter("PASSWORD");
			String name = request.getParameter("NAME");
			int position = Integer.parseInt(request.getParameter("POSITION"));

			// 更新または削除を行う
			UserDataDao uDao = new UserDataDao();
			if (request.getParameter("SUBMIT").equals("更新")) {


				// 更新成功/mypageに戻る
				if (uDao.update(new UserData(id, email, password, name, position))) {
					// 処理を行う
					List<UserData> userList = uDao.select(email);
					//リクエストスコープに格納する
					request.setAttribute("userList", userList);
					// 結果ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
					dispatcher.forward(request, response);
				}
				// 更新失敗/mypageに戻る
				else {
					request.setAttribute("result",
							new Result("更新失敗！", "/newReacQ/MyUserDataServlet"));
				}
			}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
		}

		else if (pos.equals("0")) {

			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			int id = Integer.parseInt(request.getParameter("ID"));
			String email = request.getParameter("EMAIL");
			String password = request.getParameter("PASSWORD");
			String name = request.getParameter("NAME");
			int position = Integer.parseInt(request.getParameter("POSITION"));

			// 更新または削除を行う
			UserDataDao uDao = new UserDataDao();
			if (request.getParameter("SUBMIT").equals("更新")) {

				// 更新成功/メニューに戻る
				if (uDao.update(new UserData(id, email, password, name, position))) {

					// 処理を行う
					List<UserData> userList = uDao.select(email);
					//リクエストスコープに格納する
					request.setAttribute("userList", userList);
					// 結果ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypageT.jsp");
					dispatcher.forward(request, response);
				}
				// 更新失敗/検索に戻る
				else {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "更新失敗");
				}
			}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypageT.jsp");
		dispatcher.forward(request, response);
		}
	}
}
