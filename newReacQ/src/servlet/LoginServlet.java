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
import model.User;

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


      /*
        // セッションからログイン情報を取得
        HttpSession session = request.getSession();
        UserData Ud = (UserData) session.getAttribute("account");

        // ロールでフォワード先を振り分ける
        if(Ud.getPosition() == 0) {
            RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
            rd.forward(request, response);
        } else if(Ud.getPosition() == 1) {
            RequestDispatcher rd = request.getRequestDispatcher("menuT.jsp");
            rd.forward(request, response);
        }
        */


    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("EMAIL");
		String password = request.getParameter("PASSWORD");

		UserDataDao uDao = new UserDataDao();
		//String position = Integer.toString(uDao.selectP(email));
		String position = "0";

		HttpSession session = request.getSession();
		session.setAttribute("account", new User(email, password));
		session.setAttribute("position", position);


		// ログイン成功
		if (uDao.isLoginOK(email, password)) {


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