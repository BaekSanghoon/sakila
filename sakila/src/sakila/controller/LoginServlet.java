package sakila.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.service.StaffService;
import sakila.service.StatsService;
import sakila.vo.Staff;
import sakila.vo.Stats;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private StatsService statsService;
	private StaffService staffService;
	//로그인 폼으로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginStaff") != null) { //로그인이 되어있으면..
				response.sendRedirect(request.getContextPath()+"/auth/IndexServlet"); //모든 요청 앞에는 auth를 붙일것(로그인 되어있을때만 들어오게..)
				return;
			}
			statsService = new StatsService();
			Map<String, Object> map = statsService.getStats();
			//map의 객체 변수선언
			Stats stats = (Stats) map.get("stats");
			int totalCount = (Integer) map.get("totalCount");
			//객체위에 값을담음
			request.setAttribute("stats", stats);
			request.setAttribute("totalCount", totalCount);
			//포워드할 페이지를 요청
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);//페이지를 부르는 공식
		}
	//로그인 엑션으로 이동
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		staffService = new StaffService();		
		Staff staff = new Staff();
		HttpSession session = request.getSession();
		//로그인 페이지값 받아오기
		int StaffId = Integer.parseInt(request.getParameter("id"));
		String Password = request.getParameter("pw");
		//staff 객체에 값을 저장
		staff.setStaffId(StaffId);
		staff.setPassword(Password);
		// 서비스에서 인증 결과 받아오기
		Staff returnStaff = staffService.getStaffByKey(staff);

		// session담고 IndexServlet 포워딩
		if(returnStaff != null) { //로그인성공
			String userName = returnStaff.getUserName();
			
			//session에 담기
			session.setAttribute("loginStaff", StaffId);
			response.sendRedirect(request.getContextPath()+"/auth/IndexServlet");

			return;
		}
		// 로그인 실패 -> 로그인 페이지로 이동
		System.out.println("로그인실패");
		response.sendRedirect(request.getContextPath() + "/LoginServlet");
	}
}
