package sakila.service;

import java.sql.*;

import sakila.dao.StaffDao;
import sakila.util.DBUtil;
import sakila.vo.Staff;

public class StaffService {
	private StaffDao staffDao;
	
	//로그인
	public Staff getStaffByKey(Staff staff) {
		Staff returnStaff = null;		
		staffDao = new StaffDao();
		Connection conn =  null;

		try {
			DBUtil dbUtil = new DBUtil();
			conn = DBUtil.getConnection(); 
			conn.setAutoCommit(false);

			// 인증 확인 -> 결과 저장
			returnStaff = staffDao.selectStaffByKey(conn, staff);
			conn.commit();
			
		} catch(Exception e) { //예외발생시 실행
			try {				
				conn.rollback();	
				
			} catch (SQLException e1) {				
				e1.printStackTrace(); //메소드가 내부적으로 예외결과를 호출
			}
			e.printStackTrace();
			
		} finally {
			try {			
				conn.close();
				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		
		return returnStaff;
	}
	
	public Staff staffOne(Staff staff) {
		Staff returnStaff = null;		
		staffDao = new StaffDao();
		Connection conn =  null;

		try {
			DBUtil dbUtil = new DBUtil();
			conn = DBUtil.getConnection(); 
			conn.setAutoCommit(false);
			
			returnStaff = staffDao.selectStaffOne(conn, staff);
			conn.commit();
			
		} catch(Exception e) { //예외발생시 실행
			try {				
				conn.rollback();	
				
			} catch (SQLException e1) {				
				e1.printStackTrace(); //메소드가 내부적으로 예외결과를 호출
			}
			e.printStackTrace();
			
		} finally {
			try {			
				conn.close();
				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		
		return returnStaff;
	}
}
