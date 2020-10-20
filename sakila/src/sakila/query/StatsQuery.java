package sakila.query;

public class StatsQuery {
	//Dao에 전달될 쿼리문
	//오늘날짜정보
	public static final String SELECT_STATS = "SELECT day, count FROM stats WHERE day = ?";
	//오늘 날짜가 없으면 오늘날짜를 입력하고 카운트를 1로 초기화 시킨다
	public static final String INSERT_STATS = "INSERT INTO stats(day, count) VALUES(?, 1)";
	//오늘 날짜가 있으면 1을 더한다
	public static final String UPDATE_STATS = "UPDATE stats SET count=count+1 WHERE day = ?";
	//총 접속자수
	public static final String TOTAL_STATS = "SELECT SUM(count) FROM stats";
}
