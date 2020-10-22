package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.query.StaffQuery;
import sakila.vo.Staff;

public class StaffDao {
	public Staff selectStaffByKey(Connection conn , Staff staff)  throws Exception { //모든예외넘김(오브젝트 제외)
		Staff returnStaff = null;
		
		PreparedStatement stmt = conn.prepareStatement(StaffQuery.SELECT_STAFF_BY_KEY);
		stmt.setString(1, staff.getEmail());
		stmt.setString(2, staff.getPassword());
		System.out.println("debug: method-begin: StaffDao.selectStaffByKey() ->" + stmt);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			returnStaff = new Staff();
			returnStaff.setEmail(rs.getString("email"));
			returnStaff.setPassword(rs.getString("username"));
		}
		System.out.println("debug: instance 변수 : returnStaff="+returnStaff);
		return returnStaff;
	}
}
