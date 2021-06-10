package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserData;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class  LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//ログインページにフォワード
      RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
      dispatcher.forward(request, response);

        // セッションからログイン情報を取得
        HttpSession session = request.getSession();
        UserData Ud = (UserData) session.getAttribute("account");

        // ロールでフォワード先を振り分ける
        if(Ud.getRole() == 1) {
            RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
            rd.forward(request, response);
        } else if(Ud.getRole() == 2) {
            RequestDispatcher rd = request.getRequestDispatcher("menuT.jsp");
            rd.forward(request, response);
        }
    }
}