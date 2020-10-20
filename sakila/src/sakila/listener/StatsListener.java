package sakila.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import sakila.service.StatsService;

@WebListener
public class StatsListener implements HttpSessionListener {

 
    private StatsService statsService;
	public StatsListener() {//생성자
		
	}
    public void sessionCreated(HttpSessionEvent se)  {  //세션의 생성 여부를 알수있다(감지)
        if(se.getSession().isNew()) {
        	statsService = new StatsService();
        	statsService.countStats();
        }
    }

    public void sessionDestroyed(HttpSessionEvent se)  { //세션이 끝났을때
    }
	
}
