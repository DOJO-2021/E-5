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

import dao.BoardDao;
import dao.UserDataDao;
import model.Board;
import model.UserData;

/**
 * Servlet implementation class MyBoardServlet
 */
@WebServlet("/MyBoardServlet")
public class MyBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/nerReacQ/LoginServlet");
			return;
		}

		/*
		 * ユーザデータの表示
		 */
		String email = (String)session.getAttribute("email");

		// 処理を行う
		UserDataDao uDao = new UserDataDao();
		List<UserData> userList = uDao.select(email);

		//リクエストスコープに格納する
		request.setAttribute("userList", userList);

		//講師か受講者かを判別するpositionを取得
		String pos =(String)session.getAttribute("position");

		if (pos.equals("1")) {
			// 処理を行う(select、emailが受講者、日付も選択可)
			//リクエストパラメータ(日付)を取得する
			request.setCharacterEncoding("UTF-8");
			String selectdate = request.getParameter("REPLY_DATE_B");

			BoardDao bDao = new BoardDao();
			List<Board> myboardList = bDao.selectmypage(new Board(0, email, 0, 0, "", selectdate));

			//リクエストスコープに格納する
			request.setAttribute("date_b", selectdate);
			request.setAttribute("myboardList", myboardList);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			dispatcher.forward(request, response);
		}

	}

}
