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
import model.Board;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/newReacQ/LoginServlet");
			return;
		}

		// 検索ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/board.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/newReacQ/LoginServlet");
			return;
		}

		// 検索ワードを取得する
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String question =request.getParameter("QUESTION");

		//検索処理を行う
		BoardDao bDao = new BoardDao();
		List<Board> resultlist = bDao.select(new Board(0, "", 0, 0, question, ""));

		// 検索結果をリクエストスコープに格納する
		  request.setAttribute("resultlist", resultlist);

		// 結果ページにフォワードする
		  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/searchresult.jsp");
		  dispatcher.forward(request, response);
	}
}