package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.query.StatsQuery;
import sakila.vo.Stats;

public class StatsDao {
	//오늘 날짜가 있는지 확인. 없으면 null값을 반환
	public Stats selectDay(Connection conn, Stats stats) throws Exception{	
		Stats returnStats = null;
		
		PreparedStatement stmt = conn.prepareStatement(StatsQuery.SELECT_STATS);
		stmt.setString(1, stats.getDay());
		System.out.println("debug: method-begin: StatsDao.selectDay() ->" + stmt);
	//오늘 날짜에 접속자가 있을시
		ResultSet rs = stmt.executeQuery(); 
		if(rs.next()) {
			returnStats = new Stats();
			returnStats.setDay(rs.getString("day"));
			returnStats.setCount(rs.getInt("count"));
		}
		System.out.println("debug: instance 변수 : returnStats="+returnStats);
		return returnStats;
	}
	// 받아온 날짜의 방문자 수를 1로 초기화시키는 메서드
	public void insertStats(Connection conn, Stats stats) throws Exception {
		PreparedStatement stmt = conn.prepareStatement(StatsQuery.INSERT_STATS);
		stmt.setString(1, stats.getDay());
		System.out.println("debug: method-begin: StatsDao.insertStats() ->"+ stmt);
		
		stmt.executeUpdate();
	}
	// 셀렉트에 오늘 날자가 있으면 +1 시키는 메서드
	public void updateStats(Connection conn, Stats stats) throws Exception{
		PreparedStatement stmt = conn.prepareStatement(StatsQuery.UPDATE_STATS);
		stmt.setString(1, stats.getDay());
		System.out.println("debug: method-begin: StatsDao.updateStats() ->"+ stmt);
		
		stmt.executeUpdate();
	}
	// 전체 접속자 수
	public int totalCount(Connection conn) throws Exception{
		int total = 0; //0으로 초기화
		PreparedStatement stmt = conn.prepareStatement(StatsQuery.TOTAL_STATS);
		System.out.println("debug: method-begin: StatsDao.totalCount() ->"+ stmt);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			total = rs.getInt("SUM(count)");
		}

		return total;
	}
}
