package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardReplyDao;
import model.BoardReply;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardServlet")
public class BoardReplyServlet extends HttpServlet {
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


		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("ID"));
		String email =request.getParameter("EMAIL");
		int q_reply_code =Integer.parseInt(request.getParameter("Q_REPLY_CODE"));
		String question_reply =request.getParameter("QUESTION_REPLY");
		String reply_date =request.getParameter("REPLY_DATE");


		//回答内容をデータベースに反映する
		BoardReplyDao brDao = new BoardReplyDao();{
		if (request.getParameter("SUBMIT").equals("回答"))
			if (brDao.insert(new BoardReply(id, email, q_reply_code, question_reply, reply_date ))) {
				request.setAttribute("result",
				new Result("回答を送信しました！", "/newReacQ/BoardServlet"));
			}
			else {
				request.setAttribute("result",
				new Result("回答を送信できませんでした", "/newReacQ/BoardServlet"));
			}
		}

	}
}