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
	SimpleDateFormat dateform=new SimpleDateFormat("dd/mm/yyyy");
	
	@Test
    public void testVersionLow() throws Exception
    {
		SimpleDateFormat dateform=new SimpleDateFormat("dd/mm/yyyy");
		Date maturityDate=dateform.parse("20/12/2021");
        Date todaysDate=Calendar.getInstance ().getTime ();
        //Same Version as before and Changing Counter-Party ID to CP-2
        Trade t4=new Trade("T1",1,"CP-2","B1",maturityDate, todaysDate, 'N');
        tflow.addTrade(t4);
        assertEquals("CP-2",tflow.allTrade.get("T1").getCounterPartId());

    }

	
    @Test
    public void testMaturityGreater() throws Exception
    {

    	Date todaysDate=Calendar.getInstance ().getTime ();
        Date maturityDate=dateform.parse("20/05/2022");
		Trade t2=new Trade("T2",2,"CP-2","B1",maturityDate, todaysDate, 'N');
		tflow.addTrade(t2);

        assertEquals(t2,tflow.allTrade.get("T4"));

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
