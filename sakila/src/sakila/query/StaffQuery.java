package sakila.query;

public class StaffQuery {
	public final static String SELECT_STAFF_BY_KEY = "SELECT email, username FROM staff WHERE email = ? AND password = PASSWORD(?)";
	
}
