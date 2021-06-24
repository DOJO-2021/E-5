package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
import model.Board;
import model.Result;

/**
 * Servlet implementation class BoardPostServlet
 */
@WebServlet("/BoardPostServlet")
public class BoardPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/newReacQ/LoginServlet");
			return;
		}

		String email = (String)session.getAttribute("email");

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String question =request.getParameter("QUESTION");

		//投稿内容をデータベースに反映する
		BoardDao bDao = new BoardDao();
		int q_sum = bDao.count() + 1;
		if (request.getParameter("SUBMIT").equals("投稿")) {
			if (bDao.insert(new Board(0, email, 0, q_sum, question, "" ))) {
				request.setAttribute("result",
				new Result("投稿を送信しました！", "/newReacQ/BoardServlet"));
			}
			else {
				request.setAttribute("result",
				new Result("投稿を送信できませんでした。", "/newReacQ/BoardServlet"));
			}
		}
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
		dispatcher.forward(request, response);
	}
}