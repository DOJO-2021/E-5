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
import model.Reaction;

@WebServlet("/MenuReactionServlet")
public class MenuReactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	//private static final String date = null;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// もしもログインしていなかったらログインサーブレットにリダイレクトする
    	HttpSession session = request.getSession();
    	if (session.getAttribute("email") == null) {
    		response.sendRedirect("/newReacQ/LoginServlet");
    		return;
    	}

    	//講師か受講者かを判別するpositionを取得
    	String pos = (String)session.getAttribute("position");
    	if (pos.equals("1")) {

    		/*
    		 * リアクションの表示
    		 */
    		ReactionDao rDao = new ReactionDao();
    		String date=rDao.reset(new Reaction (0,"",4,"", ""));
    		int reaction0 = rDao.countmenu(0, date);
			int reaction1 = rDao.countmenu(1, date);
			int reaction2 = rDao.countmenu(2, date);
			int reaction3 = rDao.countmenu(3, date);

			//リクエストスコープに格納する
			request.setAttribute("rea0", reaction0);
			request.setAttribute("rea1", reaction1);
			request.setAttribute("rea2", reaction2);
			request.setAttribute("rea3", reaction3);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
			dispatcher.forward(request, response);
    	}
		else if (pos.equals("0")) {

			/*
			 * リアクションの表示
			 */
			ReactionDao rDao = new ReactionDao();
			String date=rDao.reset(new Reaction (0,"",4,"", ""));

			int reaction0 = rDao.countmyT(0, date);
			int reaction1 = rDao.countmyT(1, date);
			int reaction2 = rDao.countmyT(2, date);
			int reaction3 = rDao.countmyT(3, date);

			//リクエストスコープに格納する
			request.setAttribute("rea0", reaction0);
			request.setAttribute("rea1", reaction1);
			request.setAttribute("rea2", reaction2);
			request.setAttribute("rea3", reaction3);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menuT.jsp");
			dispatcher.forward(request, response);
		}
    }

			//リアクションの登録
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pos = (String)session.getAttribute("position");
		if (pos.equals("1")) {

			String email = (String)session.getAttribute("email");
			// リクエストパラメータを取得する
			//request.setCharacterEncoding("UTF-8");
			//int reaction = Integer.parseInt(request.getParameter("Reaction"));

			// 登録処理を行う
			request.setCharacterEncoding("UTF-8");
			String react_title = request.getParameter("react_title");
			ReactionDao rDao = new ReactionDao();
			if (request.getParameter("mrea").equals("説明可")) {
				boolean result = rDao.insert(new Reaction(0, email, 3, "", react_title));
				System.out.println(result);
			}
			else if (request.getParameter("mrea").equals("分かる")) {
				boolean result = rDao.insert(new Reaction(0, email, 2, "", react_title));
				System.out.println(result);
			}
			else if (request.getParameter("mrea").equals("分かるかも")) {
				rDao.insert(new Reaction(0, email, 1, "", react_title));
			}
			else if (request.getParameter("mrea").equals("分からない")) {
				rDao.insert(new Reaction(0, email, 0, "", react_title));
			}
			else if (request.getParameter("mrea").equals("更新")) {
				String react_t = (String)session.getAttribute("react_t");
				request.setAttribute("react_t", react_t);
			}

    		/*
    		 * リアクションの表示
    		 */
    		String date=rDao.reset(new Reaction(0,"",4,"", ""));
    		int reaction0 = rDao.resetcount(0, date);
    		int reaction1 = rDao.resetcount(1, date);
    		int reaction2 = rDao.resetcount(2, date);
    		int reaction3 = rDao.resetcount(3, date);

			//リクエストスコープに格納する
			request.setAttribute("rea0", reaction0);
			request.setAttribute("rea1", reaction1);
			request.setAttribute("rea2", reaction2);
			request.setAttribute("rea3", reaction3);

			// フォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
			dispatcher.forward(request, response);
    	}
  		else if (pos.equals("0")) {
			String email = (String)session.getAttribute("email");

			// 登録処理を行う
			request.setCharacterEncoding("UTF-8");
			String react_title = request.getParameter("react_title");

			session.setAttribute("react_t", react_title);

			ReactionDao rDao = new ReactionDao();
			if (request.getParameter("mrea").equals("更新")) {
				request.setAttribute("react_t", react_title);
			}
			else if (request.getParameter("mrea").equals("リセット")) {
				boolean result = rDao.insert(new Reaction(0, email, 4, "", react_title));
				System.out.println(result);
				request.setAttribute("react_t", react_title);
			}

			/*
			 * リアクションの表示
			 */
			String date=rDao.reset(new Reaction (0,"",4,"", ""));

			int reaction0 = rDao.resetcount(0, date);
			int reaction1 = rDao.resetcount(1, date);
			int reaction2 = rDao.resetcount(2, date);
			int reaction3 = rDao.resetcount(3, date);

			//リクエストスコープに格納する
			request.setAttribute("rea0", reaction0);
			request.setAttribute("rea1", reaction1);
			request.setAttribute("rea2", reaction2);
			request.setAttribute("rea3", reaction3);

    		// フォワードする
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menuT.jsp");
    		dispatcher.forward(request, response);
    	}
	}
}