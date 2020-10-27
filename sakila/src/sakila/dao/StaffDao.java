package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.query.StaffQuery;
import sakila.vo.Address;
import sakila.vo.Staff;

public class StaffDao {
	//로그인
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
	//관리자 정보 자세히 보기
	public Staff selectStaffOne(Connection conn, Staff staff) throws Exception {
		Staff returnStaff = null;
		Address address = null;
		
		PreparedStatement stmt = conn.prepareStatement(StaffQuery.SELECT_STAFF_BY_LIST);
		stmt.setInt(1, staff.getStaffId());
		System.out.println("debug: method-begin: StaffDao.selectStaffOne() ->" + stmt);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			returnStaff = new Staff();
			address = new Address();
			returnStaff.setUserName(rs.getString("username"));
			returnStaff.setFirstName(rs.getString("firstName"));
			returnStaff.setLastName(rs.getString("lastName"));
			returnStaff.setEmail(rs.getString("email"));
			address.setPhone(rs.getString("phone"));
			address.setAddress(rs.getString("address"));
					
		}
		return returnStaff,address;
	}
}
