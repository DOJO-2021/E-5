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
import model.Board;

/**
 * Servlet implementation class MenuBoardServlet
 */
@WebServlet("/MenuBoardServlet")
public class MenuBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/newReacQ/LoginServlet");
			return;
		}


		//講師か受講者かを判別するpositionを取得
		String pos =(String)session.getAttribute("position");

		if (pos.equals("0")) {
			// 処理を行う
			BoardDao bDao = new BoardDao();
			List<Board> newlist = bDao.selectlatest(new Board(0,"",0,0,"",""));

			//リクエストスコープに格納する
			request.setAttribute("newlist", newlist);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menuT.jsp");
			dispatcher.forward(request, response);
		}

		if (pos.equals("1")) {
			// 処理を行う
			String email =(String)session.getAttribute("email");
			BoardDao bDao = new BoardDao();
			 List<Board> newlist = bDao.selectlatest(new Board(0,"",0,0,"",""));
			 List<Board> mynewlist = bDao.selectmynewlist(new Board(0,email,0,0,"",""));

			//リクエストスコープに格納する
			request.setAttribute("newlist", newlist);
			request.setAttribute("mynewlist", mynewlist);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
			dispatcher.forward(request, response);
		}
	}
}