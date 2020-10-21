package sakila.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import sakila.dao.StatsDao;
import sakila.util.DBUtil;
import sakila.vo.Stats;

public class StatsService {
	private StatsDao statsDao;
	
	private Stats getToday() {
		//오늘 날짜를 출력하는 메서드
		Calendar today = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String day = formater.format(today.getTime());
		Stats stats = new Stats();
		stats.setDay(day);
		return stats;
	}
	
	//오늘 날짜 방문자가 있는지 확인하고 없으면 null값을 반환함, 총방문자수를 map을 사용하여 리턴
	public Map<String, Object> getStats() {
		//map : Key와 Value라는 것을 한 쌍으로 갖는 자료형 대응관계를 쉽게표현가능
		//오늘카운터값, 토탈카운터값 리턴, int 값을 integer로 바꿔서 박싱한다
		Map<String, Object> map = null;
		statsDao = new StatsDao();
		Stats stats = new Stats();
		Connection conn = null;
		
		try {			
			//DB연결
			conn = DBUtil.getConnection();
			//false로 자동으로 커밋하지 않고 수동으로 커밋을 넣음
			conn.setAutoCommit(false);			
			// stats에 오늘의 날짜를 추가함
			stats = this.getToday();  
			//오늘날짜 정보
			stats = statsDao.selectDay(conn, stats); 
			//전체 접속자수 카운트
			int totalCount = statsDao.totalCount(conn);
			
			map = new HashMap<String, Object>();
			map.put("stats",stats);
			map.put("totalCount", totalCount);
			
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
		return map;
	}
	
	//방문자의 숫자를 카운트하여 없으면 오늘날짜를 있으면 +1한다.
	public void countStats() {
		Stats stats = new Stats();
		statsDao = new StatsDao();
		Connection conn = null;
		
		try {			
			//DB연결
			conn = DBUtil.getConnection();
			//false로 자동으로 커밋하지 않고 수동으로 커밋을 넣음
			conn.setAutoCommit(false);			
			// stats에 오늘의 날짜를 추가함
			stats = this.getToday();
			
			//오늘날짜 정보
			stats = statsDao.selectDay(conn, stats); 
			
			// 오늘 날짜가 db에 없을 경우 오늘날짜를 db에 추가후 count를 1로 초기화.
			if(stats == null) {
				System.out.println("false");
				stats = getToday();
				statsDao.insertStats(conn, stats);
			// 오늘 날짜가 있을경우 count를 +1 한다.
			}else {
				System.out.println("true");
				statsDao.updateStats(conn, stats);
			}
			
			
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
	}
	
}
