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
import dao.ReactionDao;
import dao.UserDataDao;
import model.Board;
import model.UserData;

/**
 * Servlet implementation class MyReactionServlet
 */
@WebServlet("/MyReactionServlet")
public class MyReactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/nerReacQ/LoginServlet");
			return;
		}
		/*
		 * ユーザデータの表示
		 */
		String email = (String)session.getAttribute("email");

		// 処理を行う
		UserDataDao uDao = new UserDataDao();
		List<UserData> userList = uDao.select(email);

		//リクエストスコープに格納する
		request.setAttribute("userList", userList);

		//講師か受講者かを判別するpositionを取得
		String pos =(String)session.getAttribute("position");
		if (pos.equals("1")) {
			// 処理を行う(select、emailが受講者、日付も選択可)
			//リクエストパラメータ(日付)を取得する
			request.setCharacterEncoding("UTF-8");
			String selectdate = request.getParameter("REPLY_DATE_R");

			ReactionDao rDao = new ReactionDao();
			int reaction0 = rDao.countmypage(email, 0, selectdate);
			int reaction1 = rDao.countmypage(email, 1, selectdate);
			int reaction2 = rDao.countmypage(email, 2, selectdate);
			int reaction3 = rDao.countmypage(email, 3, selectdate);

			//リクエストスコープに格納する
			request.setAttribute("date_r", selectdate);
			request.setAttribute("myrea0", reaction0);
			request.setAttribute("myrea1", reaction1);
			request.setAttribute("myrea2", reaction2);
			request.setAttribute("myrea3", reaction3);

			/*
			 * 今日の日付の掲示板の表示
			 */
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
		else if (pos.equals("0")) {

			//リクエストパラメータ(日付)を取得する
			request.setCharacterEncoding("UTF-8");
			String date = request.getParameter("REPLY_DATE");

			ReactionDao rDao = new ReactionDao();
			int reaction0 = rDao.countmyT(0, date);
			int reaction1 = rDao.countmyT(1, date);
			int reaction2 = rDao.countmyT(2, date);
			int reaction3 = rDao.countmyT(3, date);

			//リクエストスコープに格納する
			request.setAttribute("date_r", date);
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
