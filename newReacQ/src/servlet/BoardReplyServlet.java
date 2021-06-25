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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/newReacQ/LoginServlet");
			return;
		}
		String pos = (String)session.getAttribute("position");
		String email = (String)session.getAttribute("email");
		request.setCharacterEncoding("UTF-8");
		int question_code = Integer.parseInt(request.getParameter("QUESTION_CODE"));
		String question_reply =request.getParameter("QUESTION_REPLY");

		BoardDao bDao = new BoardDao();

		if (pos.equals("1")) {
			if (request.getParameter("SUBMIT").equals("送信")) {
				BoardReplyDao brDao = new BoardReplyDao();
				boolean result = brDao.confirm(question_code);
				if(result == true) {
					if (brDao.insert(new BoardReply(0, email, question_code, question_reply, ""))) {
						bDao.update(question_code);

						request.setAttribute("result",
						new Result("回答を送信しました！", "/newReacQ/BoardServlet"));
					}
					else {
						request.setAttribute("result",
						new Result("回答を送信できませんでした。", "/newReacQ/BoardServlet"));
					}
				}
				else {
					request.setAttribute("result",
					new Result("すでに回答されている質問です。", "/newReacQ/BoardServlet"));
				}

				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
				dispatcher.forward(request, response);
			}
			else if(request.getParameter("SUBMIT").equals("気になる")) {
				boolean result = bDao.insertlike(new Like(0, email, question_code, ""));
				System.out.println(result);

				List<BoardAll> Alllist = bDao.boardJoin(new BoardAll(0, "", 0, 0, "", "", "", "", 0, ""));

				// 検索結果をリクエストスコープに格納する
				request.setAttribute("Alllist", Alllist);
				// 掲示板ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/board.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if (pos.equals("0")) {
			if (request.getParameter("SUBMIT").equals("送信")) {
				BoardReplyDao brDao = new BoardReplyDao();
				boolean result = brDao.confirm(question_code);
				if(result == true) {
					if (brDao.insert(new BoardReply(0, email, question_code, question_reply, ""))) {
						bDao.update(question_code);

						request.setAttribute("result",
						new Result("回答を送信しました！", "/newReacQ/BoardServlet"));
					}
					else {
						request.setAttribute("result",
						new Result("回答を送信できませんでした。", "/newReacQ/BoardServlet"));
					}
				}
				else {
					request.setAttribute("result",
					new Result("すでに回答されている質問です。", "/newReacQ/BoardServlet"));
				}

				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
				dispatcher.forward(request, response);
			}
			else if(request.getParameter("SUBMIT").equals("気になる")) {
				boolean result = bDao.insertlike(new Like(0, email, question_code, ""));
				System.out.println(result);

				List<BoardAll> Alllist = bDao.boardJoin(new BoardAll(0, "", 0, 0, "", "", "", "", 0, ""));

				// 検索結果をリクエストスコープに格納する
				request.setAttribute("Alllist", Alllist);
				// 掲示板ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/boardT.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}