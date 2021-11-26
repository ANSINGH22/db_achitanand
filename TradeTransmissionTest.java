package tradeTransmission;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.*;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;


public class TradeTransmissionTest {

	TradeFlow tflow = new TradeFlow();
	Date currDate =Calendar.getInstance().getTime();
	SimpleDateFormat dateform=new SimpleDateFormat("dd/MM/YYYY");
	
/*	@Test
	public void testcheckVersion() throws Exception {
		
		Date maturityDate=dateform.parse("20/05/2021");
        Trade t2=new Trade("T2",2,"CP-2","B1",maturityDate, currDate, 'N');
        tflow.addTrade(t2);

        //Changing Version as 3 and Changing Counter-Party ID to CP-3
        Trade t3=new Trade("T2",3,"CP-3","B1",maturityDate, currDate, 'N');
        tflow.addTrade(t3);
        assertEquals("CP-4",tflow.allTrade.get("T2").getCounterPartId());
        
        maturityDate=dateform.parse("20/05/2021");
        //Same Version as before and Changing Counter-Party ID to CP-2
        Trade t4=new Trade("T1",1,"CP-2","B1",maturityDate, currDate, 'N');
        tflow.addTrade(t4);
        assertEquals("CP-2",tflow.allTrade.get("T1").getCounterPartId());
    
		
	}
	*/
	/*
	 * @Test public void testVersion() throws Exception { Date
	 * maturityDate=dateform.parse("20/05/2021"); //Same Version as before and
	 * Changing Counter-Party ID to CP-2 Trade t4=new
	 * Trade("T1",1,"CP-2","B1",maturityDate, currDate, 'N'); tflow.addTrade(t4);
	 * assertEquals("CP-2",tflow.allTrade.get("T1").getCounterPartId()); }
	 */

	@Test
    public void testVersionLow() throws Exception
    {
        Date maturityDate=dateform.parse("20/12/2021");

        Trade t5=new Trade("T3",1,"CP-3","B1",maturityDate, currDate, 'N');
        tflow.addTrade(t5);
        //tflow.allTrade.put("T3", t5);
        int sizeofList=tflow.allTrade.size();
        //Now Adding Another List
        Trade t6=new Trade("T3",0,"CP-2","B1",maturityDate, currDate, 'N');

        assertThrows(Exception.class,()->tflow.addTrade(t6),"0 is less than 1");

    }

	
    @Test
    public void testMaturityGreater() throws Exception
    {
        Date maturityDate=dateform.parse("30/12/2021");

        Trade t7=new Trade("T4",5,"CP-4","B3",maturityDate, currDate, 'N');
        tflow.addTrade(t7);
        System.out.println(" "+t7);
        
        System.out.println("output: = "+tflow.allTrade.get("T4"));
        
        System.out.println("outputmap: = "+tflow.allTrade);

        assertEquals(t7,tflow.allTrade.get("T4"));

    }

 
    @Test
    public void testMaurityLower() throws Exception
    {
        Date maturityDate=dateform.parse("20/05/2020");
        Trade t8=new Trade("T5",1,"CP-4","B3",maturityDate, currDate, 'N');
        tflow.addTrade(t8);
        assertNull(tflow.allTrade.get("T5"));
    }


}
