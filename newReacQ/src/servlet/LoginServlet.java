package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDataDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class  LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//ログインページにフォワード
      RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
      dispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("EMAIL");
		String password = request.getParameter("PASSWORD");

		UserDataDao uDao = new UserDataDao();

		// ログイン成功
		if (uDao.isLoginOK(email, password)) {
			String position = Integer.toString(uDao.selectP(email));
			//String position = "0";

			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("position", position);

			// メニューサーブレットにリダイレクトする
			response.sendRedirect("/newReacQ/MenuReactionServlet");
		}
		// ログイン失敗
		else {
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}