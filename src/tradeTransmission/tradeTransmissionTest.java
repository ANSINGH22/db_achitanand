package tradeTransmission;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class tradeTransmissionTest {
	
	tradeFlow tflow = new tradeFlow();
	Date currDate =Calendar.getInstance().getTime();
	SimpleDateFormat dateform=new SimpleDateFormat("dd/MM/YYYY");
	
	@Test
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
	
	@Test
    @Order(2)
    void testVersionSame() throws Exception
    {
        Date maturityDate=dateform.parse("20/05/2021");
        //Same Version as before and Changing Counter-Party ID to CP-2
        Trade t4=new Trade("T1",1,"CP-2","B1",maturityDate, currDate, 'N');
        tflow.addTrade(t4);
        assertEquals("CP-2",tflow.allTrade.get("T1").getCounterPartId());
    }

    //Check if Version is low the trade will be rejected
    //T3	5	CP-3	B1	20/05/2014	today date	N
    //T3	1	CP-2	B1	20/05/2014	today date	N
    @Test
    @Order(3)
    void testVersionLow() throws Exception
    {
        Date maturityDate=dateform.parse("20/05/2021");

        Trade t5=new Trade("T3",5,"CP-3","B1",maturityDate, currDate, 'N');
        tflow.addTrade(t5);
        int sizeofList=tflow.allTrade.size();
        //Now Adding Another List
        Trade t6=new Trade("T3",1,"CP-2","B1",maturityDate, currDate, 'N');

        assertThrows(Exception.class,()->tflow.addTrade(t6),"1 is less than 5");

    }
    //Check if maturity Date is greater than todays date the trade is added
    //T4	5	CP-3	B1	20/05/2021	today date	N
    @Test
    @Order(4)
    void testMaturityGreater() throws Exception
    {
        Date maturityDate=dateform.parse("20/05/2021");

        Trade t7=new Trade("T4",5,"CP-4","B3",maturityDate, currDate, 'N');
        tflow.addTrade(t7);

        assertEquals(t7,tflow.allTrade.get("T4"));

    }

    //Check if maturity Date is lower than todays date the Trade will not be added
    //T5  5  CP-3  B1  20/05/2020   today date  N
    @Test
    @Order(5)
    void testMaurityLower() throws Exception
    {
        Date maturityDate=dateform.parse("20/05/2020");
        Trade t8=new Trade("T5",1,"CP-4","B3",maturityDate, currDate, 'N');
        tflow.addTrade(t8);
        assertNull(tflow.allTrade.get("T5"));
    }


}
