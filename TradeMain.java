package tradeTransmission;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TradeMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		TradeFlow tflow=new TradeFlow();
		Date todaysDate=Calendar.getInstance ().getTime ();
		SimpleDateFormat dateform=new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		//Adding Trade T1
		Date maturityDate=dateform.parse("20/05/2021");
		Trade t1=new Trade("T1",1,"CP-1","B1",maturityDate, todaysDate, 'N');
		tflow.addTrade(t1);
		
		//Adding Trade T2
		maturityDate=dateform.parse("20/05/2022");
		Trade t2=new Trade("T2",2,"CP-2","B1",maturityDate, todaysDate, 'N');
		tflow.addTrade(t2);
	
		
		
		//Adding Trade T3
		maturityDate=dateform.parse("20/05/2022");
		Date createdDate=dateform.parse("14/03/2015");
		Trade t4=new Trade("T3",1,"CP-1","B1",maturityDate, createdDate, 'N');
		tflow.addTrade(t4);
		
		
		//Adding Trade T4
		maturityDate=dateform.parse("20/05/2014");
		Trade t3=new Trade("T4",5,"CP-3","B2",maturityDate, todaysDate, 'N');
		tflow.addTrade(t3);
		
		
		
		//Display all Trade
		System.out.println("\n\n");
		System.out.println("Displaying total number of Trade in the list");
		tflow.printTrade();
		System.out.println("\n\n");	
				
		//Checking for all Expired Flag
		System.out.println("Checking for Expired Flag");
		maturityDate=dateform.parse("20/05/2020");
		Trade t6=new Trade("T2",2,"CP-2","B1",maturityDate, todaysDate, 'N');
		tflow.allTrade.replace("T2", t6);
		
		maturityDate=dateform.parse("20/05/2020");
		Trade t7=new Trade("T4",5,"CP-3","B2",maturityDate, todaysDate, 'N');
		tflow.allTrade.replace("T4", t7);
		tflow.checkExpiredDates();
		tflow.printTrade();
		
		

	}
}
