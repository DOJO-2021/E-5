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
import dao.BoardReplyDao;
import model.BoardAll;
import model.BoardReply;
import model.Like;
import model.Result;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardReplyServlet")
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

		String email = (String)session.getAttribute("email");
		request.setCharacterEncoding("UTF-8");
		String question = request.getParameter("QUESTION");
		String question_reply =request.getParameter("QUESTION_REPLY");

		BoardDao bDao = new BoardDao();
		int q_code = bDao.q_code(question);

		if (request.getParameter("QUESTION_REPLY").equals("回答")) {
			BoardReplyDao brDao = new BoardReplyDao();
			if (brDao.insert(new BoardReply(0, email, q_code, question_reply, ""))) {
				request.setAttribute("result",
				new Result("回答を送信しました！", "/newReacQ/BoardServlet"));
			}
			else {
				request.setAttribute("result",
				new Result("回答を送信できませんでした", "/newReacQ/BoardServlet"));
			}

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("LIKE").equals("気になる")) {
			boolean result = bDao.insertlike(new Like(0, email, q_code, ""));
			System.out.println(result);

			List<BoardAll> Alllist = bDao.boardJoin(new BoardAll(0, "", 0, 0, "", "", "", "", 0));

			// 検索結果をリクエストスコープに格納する
			request.setAttribute("Alllist", Alllist);
			// 掲示板ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/board.jsp");
			dispatcher.forward(request, response);
		}

		/*
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
		*/

	}
}