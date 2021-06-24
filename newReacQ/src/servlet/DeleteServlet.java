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
import dao.BoardReplyDao;
import dao.ReactionDao;
import dao.UserDataDao;
import model.Board;
import model.UserData;

/**
 * Servlet implementation class MyBoardServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/nerReacQ/LoginServlet");
			return;
		}

		String email = (String)session.getAttribute("email");

		BoardDao bDao = new BoardDao();
		BoardReplyDao brDao = new BoardReplyDao();

		//リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int code  = Integer.parseInt(request.getParameter("CODE"));

		bDao.delete(code);
		bDao.deletelike(code);
		brDao.delete(code);

		/*
		 * 今日の日付の掲示板の表示
		 */
		Date d = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String date = dt.format(d);
		List<Board> myboardList = bDao.selectmypage(new Board(0, email, 0, 0, "", date));

		//リクエストスコープに格納する
		request.setAttribute("myboardList", myboardList);

		/*
		 * ユーザデータの表示
		 */
		UserDataDao uDao = new UserDataDao();
		List<UserData> userList = uDao.select(email);

		//リクエストスコープに格納する
		request.setAttribute("userList", userList);

		/*
		 * 今日の日付のリアクションの表示
		 */
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
}