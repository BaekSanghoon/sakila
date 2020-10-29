package sakila.query;

public class StaffQuery {
	public final static String SELECT_STAFF_BY_KEY = "SELECT email, username FROM staff WHERE email = ? AND password = PASSWORD(?)";
	public final static String SELECT_STAFF_BY_LIST = "SELECT a.username, a.first_name, a.last_name, a.email, b.phone, b.address FROM staff AS a INNER JOIN staff_list AS b ON a.staff_id = b.ID WHERE a.staff_id = ?";
}
