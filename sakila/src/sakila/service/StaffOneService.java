package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;

import sakila.dao.StaffDao;
import sakila.util.DBUtil;
import sakila.vo.Staff;

public class StaffOneService {
	private StaffDao staffDao;
	//스태프 자세히 보기
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
			
		} catch(Exception e) { //예외발생
			try {				
				conn.rollback();	
				
			} catch (SQLException e1) {				
				e1.printStackTrace(); 
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
