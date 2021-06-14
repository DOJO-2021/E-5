package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReactionDao;

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


		//講師か受講者かを判別するpositionを取得
		int pos =(int)session.getAttribute("position");

		if (pos == 0) {
			// 処理を行う(select、emailが受講者、日付も選択可)
			//セッションアトリビュートでemailを取得
			String email = (String)session.getAttribute("email");

			//リクエストパラメータ(日付)を取得する
			request.setCharacterEncoding("UTF-8");
			String date = request.getParameter("date");

			ReactionDao rDao = new ReactionDao();
			int reaction0 = rDao.countmypage(email, 0, date);
			int reaction1 = rDao.countmypage(email, 1, date);
			int reaction2 = rDao.countmypage(email, 2, date);
			int reaction3 = rDao.countmypage(email, 3, date);

			//リクエストスコープに格納する
			request.setAttribute("myrea0", reaction0);
			request.setAttribute("myrea1", reaction1);
			request.setAttribute("myrea2", reaction2);
			request.setAttribute("myrea3", reaction3);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
			dispatcher.forward(request, response);
		}

		else if (pos == 1) {
			// 処理を行う(select、日付選択可)
			//リクエストパラメータ(日付)を取得する
			request.setCharacterEncoding("UTF-8");
			String date = request.getParameter("date");

			ReactionDao rDao = new ReactionDao();
			int reaction0 = rDao.countmyT(0, date);
			int reaction1 = rDao.countmyT(1, date);
			int reaction2 = rDao.countmyT(2, date);
			int reaction3 = rDao.countmyT(3, date);

			//リクエストスコープに格納する
			request.setAttribute("myrea0", reaction0);
			request.setAttribute("myrea1", reaction1);
			request.setAttribute("myrea2", reaction2);
			request.setAttribute("myrea3", reaction3);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypageT.jsp");
			dispatcher.forward(request, response);
		}
	}

}
