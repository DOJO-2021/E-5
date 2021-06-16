package servlet;

import java.io.IOException;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/newReacQ/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("ID"));
		String email =request.getParameter("EMAIL");
		int reply_status =Integer.parseInt(request.getParameter("REPLY_STATUS"));
		int question_code =Integer.parseInt(request.getParameter("QUESTION_CODE"));
		String question =request.getParameter("QUESTION");
		String reply_date =request.getParameter("REPLY_DATE");


		//投稿内容をデータベースに反映する
		BoardDao bDao = new BoardDao();
		if (request.getParameter("SUBMIT").equals("投稿")) {
			if (bDao.insert(new Board(id, email, reply_status, question_code, question, reply_date ))) {
				request.setAttribute("result",
				new Result("投稿を送信しました！", "/newReacQ/BoardServlet"));
			}
			else {
				request.setAttribute("result",
				new Result("投稿を送信できませんでした", "/newReacQ/BoardServlet"));
			}
		}
	}
}
