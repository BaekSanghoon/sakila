package sakila.query;

public class StaffQuery {
	public final static String SELECT_STAFF_BY_KEY = "SELECT email, username FROM staff WHERE email = ? AND password = PASSWORD(?)";
	public final static String SELECT_STAFF_BY_LIST = "SELECT a.username, a.first_name, a.last_name, a.email, b.phone, b.address FROM staff AS a LEFT JOIN address AS b ON a.address_id = b.address_id WHERE a.staff_id = ?";
}
