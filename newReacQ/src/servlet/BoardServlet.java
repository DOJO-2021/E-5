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
import model.BoardAll;
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

		BoardDao bDao = new BoardDao();
		List<BoardAll> Alllist = bDao.boardJoin(new BoardAll(0, "", 0, 0, "", "", "", "", 0));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("Alllist", Alllist);
		// 掲示板ページにフォワードする
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
		String email = (String)session.getAttribute("email");

		BoardDao bDao = new BoardDao();

		if (question == "") {
			if (request.getParameter("qsort").equals("すべて")) {
				List<BoardAll> Alllist = bDao.boardJoin(new BoardAll(0, "", 0, 0, "", "", "", "", 0));

				// 検索結果をリクエストスコープに格納する
				request.setAttribute("Alllist", Alllist);
				// 掲示板ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/board.jsp");
				dispatcher.forward(request, response);
			}
			else if (request.getParameter("qsort").equals("回答受付中")) {
				List<BoardAll> Alllist = bDao.Allselect(new BoardAll(0, "", 0, 0, "", "", "", "", 0));

				// 検索結果をリクエストスコープに格納する
				request.setAttribute("Alllist", Alllist);
				// 掲示板ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/board.jsp");
				dispatcher.forward(request, response);
			}
			else if (request.getParameter("qsort").equals("解決済み")) {
				List<BoardAll> Alllist = bDao.Allselect(new BoardAll(0, "", 1, 0, "", "", "", "", 0));

				// 検索結果をリクエストスコープに格納する
				request.setAttribute("Alllist", Alllist);
				// 掲示板ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/board.jsp");
				dispatcher.forward(request, response);
			}
			else if (request.getParameter("qsort").equals("気になる")) {
				List<BoardAll> Alllist = bDao.LikeAllselect(email);

				// 検索結果をリクエストスコープに格納する
				request.setAttribute("Alllist", Alllist);
				// 掲示板ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/board.jsp");
				dispatcher.forward(request, response);
			}
		}
		else {
			//検索処理を行う
			List<BoardAll> Alllist = bDao.select(new BoardAll(0, "", 0, 0, question, "", "", "", 0));

			// 検索結果をリクエストスコープに格納する
			 request.setAttribute("Alllist", Alllist);
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/board.jsp");
			dispatcher.forward(request, response);
		}
	}
}