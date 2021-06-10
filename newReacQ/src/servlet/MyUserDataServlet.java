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
 * Servlet implementation class MyUserDataServlet
 */
@WebServlet("/MyUserDataServlet")
public class MyUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/nerReacQ/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("ID"));
		String email = request.getParameter("EMAIL");
		String password = request.getParameter("PASSWORD");
		String name = request.getParameter("NAME");
		int position = Integer.parseInt(request.getParameter("POSITION"));


		// 更新または削除を行う
		UserDataDAO uDao = new UserDataDAO();
		if (request.getParameter("SUBMIT").equals("更新")) {
			// 更新成功/メニューに戻る
			if (uDao.update(new UserData(id, email, password, name, position))) {
				// 処理を行う
				List<Bc> cardList = bDao.select(new Bc(id, name, name_kana, company, department, position, phone, fax, mail, postal, address, remarks));
				//リクエストスコープに格納する
				request.setAttribute("cardList", cardList);
				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
				dispatcher.forward(request, response);
			}
			// 更新失敗/検索に戻る
			else {
			}
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}

}
