package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDataDao;
import model.UserData;

/**
 * Servlet implementation class TeacherNewServlet
 */
@WebServlet("/TeacherNewServlet")
public class ResistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("EMAIL");
		String password = request.getParameter("PASSWORD");
		String name = request.getParameter("NAME");
		int position = Integer.parseInt(request.getParameter("POSITION"));

		// 登録処理を行う
		UserDataDao uDao = new UserDataDao();
		if (uDao.insert(new UserData(0, email, password, name, position))) {	// 登録成功
			/*
			request.setAttribute("result",
			new Result("登録成功！", "レコードを登録しました。", "/UserLike/TeacherLoginServlet"));
			*/
		}
		else {	// 登録失敗
			/*
			request.setAttribute("result",
			new Result("登録失敗！", "レコードを登録できませんでした。", "/UserLike/TeacherLoginServlet"));
			*/
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
}