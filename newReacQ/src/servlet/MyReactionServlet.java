package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyReactionServlet
 */
@WebServlet("/MyReactionServlet")
public class MyReactionServlet extends HttpServlet {
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


		//講師or受講者を選別（session、position）
		if (session.getAttribute("position") == 0) {
			// 処理を行う(select、emailが受講者、日付も選択可)
			//セッションスコープからemailを取得
			String email = (String)session.getAttribute("email");

			//リクエストパラメータ(日付)を取得する
			request.setCharacterEncoding("UTF-8");
			String date = request.getParameter("date");

			ReactionDAO bDao = new ReactionDAO();
			List<**> cardList = bDao.select(new **(0, email, 0, 0, "", date));

			//リクエストスコープに格納する
			request.setAttribute("myreactionList", myreactionList);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			dispatcher.forward(request, response);
		}

		else if (session.getAttribute("position") == 1) {
			// 処理を行う(select、日付選択可)
			//リクエストパラメータ(日付)を取得する
			request.setCharacterEncoding("UTF-8");
			String date = request.getParameter("date");

			ReactionDAO bDao = new ReactionDAO();
			List<**> cardList = bDao.select(new **(0, "", 0, 0, "", date));

			//リクエストスコープに格納する
			request.setAttribute("myreactionList", myreactionList);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypageT.jsp");
			dispatcher.forward(request, response);
		}
	}

}
