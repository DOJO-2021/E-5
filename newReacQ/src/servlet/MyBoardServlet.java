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

import dao.BoardDao;
import model.Board;

/**
 * Servlet implementation class MyBoardServlet
 */
@WebServlet("/MyBoardServlet")
public class MyBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/simpleBC/LoginServlet");
			return;
		}

		//講師か受講者かを判別するpositionを取得
		String pos =(String)session.getAttribute("position");

		if (pos.equals("1")) {
			// 処理を行う(select、emailが受講者、日付も選択可)
			//セッションアトリビュートでemailを取得
			String email = (String)session.getAttribute("email");

			// 処理を行う(select、日付選択可)
			Date d = new Date();
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
	        String date = dt.format(d);

			BoardDao bDao = new BoardDao();
			List<Board> myboardList = bDao.selectmypage(new Board(0, email, 0, 0, "", date));

			//リクエストスコープに格納する
			request.setAttribute("myboardList", myboardList);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/nerReacQ/LoginServlet");
			return;
		}

		// 処理を行う(select、emailが受講者、日付も選択可)
		//セッションアトリビュートでemailを取得
		String email = (String)session.getAttribute("email");

		//リクエストパラメータ(日付)を取得する
		request.setCharacterEncoding("UTF-8");
		String selectdate = request.getParameter("REPLY_DATE_B");

		BoardDao bDao = new BoardDao();
		List<Board> myboardList = bDao.selectmypage(new Board(0, email, 0, 0, "", selectdate));

		//リクエストスコープに格納する
		request.setAttribute("myboardList", myboardList);

		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);

	}

}
