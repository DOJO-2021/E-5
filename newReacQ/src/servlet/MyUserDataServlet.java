package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import dao.UserDataDao;
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

		//講師か受講者かを判別するpositionを取得
		int position =(int)session.getAttribute("position");

		if (position == 1) {
			String email = (String)session.getAttribute("email");
			String password = (String)session.getAttribute("password");

			// 処理を行う
			UserDataDao uDao = new UserDataDao();
			List<UserData> userList = uDao.select(new UserData(0, email, password, "", 0));

			//リクエストスコープに格納する
			request.setAttribute("userList", userList);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			dispatcher.forward(request, response);
		}
		else if (position == 0) {
			String email = (String)session.getAttribute("email");
			String password = (String)session.getAttribute("password");

			// 処理を行う
			UserDataDao uDao = new UserDataDao();
			List<UserData> userList = uDao.select(new UserData(0, email, password, "", 0));

			//リクエストスコープに格納する
			request.setAttribute("userList", userList);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypageT.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/nerReacQ/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("ID"));
		String email = request.getParameter("EMAIL");
		String password = request.getParameter("PASSWORD");
		String name = request.getParameter("NAME");
		int position = Integer.parseInt(request.getParameter("POSITION"));


		//講師か受講者かを判別するpositionを取得
		int pos =(int)session.getAttribute("position");

		if (pos == 1) {
			// 更新または削除を行う
			UserDataDao uDao = new UserDataDao();
			if (request.getParameter("SUBMIT").equals("更新")) {
				// 更新成功/メニューに戻る
				if (uDao.update(new UserData(id, email, password, name, position))) {
					// 処理を行う
					List<UserData> userList = uDao.select(new UserData(id, email, password, name, position));
					//リクエストスコープに格納する
					request.setAttribute("userList", userList);
					// 結果ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
					dispatcher.forward(request, response);
				}
				// 更新失敗/検索に戻る
				else {
					JOptionPane.showMessageDialog(null, "更新失敗");
				}
			}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
		}

		else if (pos == 0) {
			// 更新または削除を行う
			UserDataDao uDao = new UserDataDao();
			if (request.getParameter("SUBMIT").equals("更新")) {
				// 更新成功/メニューに戻る
				if (uDao.update(new UserData(id, email, password, name, position))) {
					// 処理を行う
					List<UserData> userList = uDao.select(new UserData(id, email, password, name, position));
					//リクエストスコープに格納する
					request.setAttribute("userList", userList);
					// 結果ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypageT.jsp");
					dispatcher.forward(request, response);
				}
				// 更新失敗/検索に戻る
				else {
					JOptionPane.showMessageDialog(null, "更新失敗");
				}
			}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultT.jsp");
		dispatcher.forward(request, response);
		}

	}

}
