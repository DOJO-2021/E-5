package servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class MenuReactionServlet
 */

import dao.ReactionDao;
@WebServlet("/MenuReactionServlet")
public class MenuReactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //
	private static final String date = null;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// もしもログインしていなかったらログインサーブレットにリダイレクトする
    	HttpSession session = request.getSession();
    	if (session.getAttribute("email") == null) {
    		response.sendRedirect("/newReacQ/LoginServlet");
    		return;
    	}
    	//講師か受講者かを判別するpositionを取得
    	String pos = (String)session.getAttribute("position");
    	if (pos.equals("0")) {
    		// 処理を行う
    		//セッションアトリビュートでemailを取得
    		String email = (String)session.getAttribute("email");

    		//リアクションを取得
    		request.setCharacterEncoding("UTF-8");
			int reaction = getInt("");
			String date = request.getParameter("date");

			ReactionDao rDao = new ReactionDao();
			int reaction0 = rDao.countmenu(0, date);
			int reaction1 = rDao.countmenu(1, date);
			int reaction2 = rDao.countmenu(2, date);
			int reaction3 = rDao.countmenu(3, date);


			//リクエストスコープに格納する
			request.setAttribute("myrea0", reaction0);
			request.setAttribute("myrea1", reaction1);
			request.setAttribute("myrea2", reaction2);
			request.setAttribute("myrea3", reaction3);

    		// フォワードする
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
    		dispatcher.forward(request, response);
    	}
   		else if (pos.equals("0")) {
    		// 処理を行う
    		//セッションアトリビュートでemailを取得
    		String email = (String)session.getAttribute("email");

    		//リアクションを取得
    		request.setCharacterEncoding("UTF-8");
			int reaction = getInt("");
			String date = request.getParameter("date");

			ReactionDao rDao = new ReactionDao();
			int reaction0 = rDao.countmenu(0, date);
			int reaction1 = rDao.countmenu(1, date);
			int reaction2 = rDao.countmenu(2, date);
			int reaction3 = rDao.countmenu(3, date);


			//リクエストスコープに格納する
			request.setAttribute("myrea0", reaction0);
			request.setAttribute("myrea1", reaction1);
			request.setAttribute("myrea2", reaction2);
			request.setAttribute("myrea3", reaction3);

    		// フォワードする
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menuT.jsp");
    		dispatcher.forward(request, response);
    	}

	}
	private int getInt(String string) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
}