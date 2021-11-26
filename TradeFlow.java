package tradeTransmission;

import java.util.Date;
import java.util.HashMap;

public class TradeFlow {

HashMap<String,Trade> allTrade=new HashMap<String,Trade>();
	
	
	//check if no trade Exists
	public boolean checkIfTradeEmpty()
	{
		return allTrade.isEmpty();
	}
	
	//Check if the version is lower 
	public void checkVersion(Trade t,int version) throws Exception
	{
		if(t.getVersion()< version)
		{
			throw new Exception(t.getVersion()+" is less than "+ version);
			
		}
		
	}
	
	//Check if maturityDate is less than todays date
	public boolean checkMaturityDate(Date maturityDate,Date CurrentDate)
	{
		
		if(CurrentDate.compareTo(maturityDate)>0)
			return false;
		
		return true;
		
	}
	
	public void checkExpiredDates()
	{

		Date currentDate=new Date();
		
		for(String strKey : allTrade.keySet() ){
		    if(currentDate.compareTo(allTrade.get(strKey).getMaturityDate())>0)
		    {
		    		Trade t=allTrade.get(strKey);
		    		t.setExpired('Y');
		    		allTrade.replace(strKey, t);
		    }
		}
		
	}
	
	
	//add Trade
	public void addTrade(Trade T) throws Exception
	{
		if(allTrade.containsKey(T.getTradeId()))
		{
			checkVersion(T, allTrade.get(T.getTradeId()).getVersion());
			
			if(checkMaturityDate(T.getMaturityDate(), allTrade.get(T.getTradeId()).getMaturityDate()))
			{
				allTrade.replace(T.getTradeId(), T);
				System.out.println(T.getTradeId()+" is added to the Store");
			}
			else
			{
				System.out.println("Unable to add "+T.getTradeId()+" in the store as maturity date is lower than todays date");
			}
		}
		else
		{
			
			if(checkMaturityDate(T.getMaturityDate(), T.getCreatedDate()))
			{
				
					allTrade.put(T.getTradeId(), T);
					System.out.println(T.getTradeId()+" is added to the Store");
			
			}
			else
			{
				System.out.println("Unable to add "+T.getTradeId()+" in the store as maturity date is lower than todays date");
			}
		}
		
	}
	
	
	//get trade
	public Trade getTrade(String tId) throws Exception
	{
		if(allTrade.containsKey(tId))
			return allTrade.get(tId);
		throw new Exception ("Trade with "+tId+" not Found");
		
	}
	
	//printAllTrade
	public void printTrade()
	{
		for(String tId : allTrade.keySet())
		{
			System.out.println(allTrade.get(tId).toString());
		}
	}
}

	

