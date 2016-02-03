import Solution.IndexCalculator;
import org.junit.Test;

import java.time.YearMonth;

import static org.junit.Assert.*;

/**
 * admin on 03.02.2016.
 */


public class IndexCalculatorTest {

    private IndexCalculator impl = new IndexCalculator("src//test//java//index.txt");

    @Test
    public void testForZero(){

        // c 5 мес и дальше коэфициент 0
        YearMonth tmpYear = YearMonth.parse("2015-05");
        for (int i=0 ; i < 8; i++){
            assertEquals(impl.solve(tmpYear.toString(),"2016-01"), 0.0f , 0.0001f );
        }
    }

    @Test
    public void test01(){
        assertEquals(impl.solve("2015-01","2016-01"), 0.371f , 0.0001f );
    }

    @Test
    public void test02(){
        assertEquals(impl.solve("2014-01","2016-01"), 0.761f , 0.0001f );
    }
    @Test
    public void test03(){
        assertEquals(impl.solve("2013-01","2016-01"), 0.770f , 0.0001f );
    }
    @Test
    public void test04(){
        assertEquals(impl.solve("2010-01","2016-01"), 0.983f , 0.0001f );
    }


}
