import com.lemon.Solution.IndexCalculator;
import com.lemon.Solution.IndexCalculatorImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.YearMonth;

import static org.junit.Assert.assertEquals;

/**
 * admin on 03.02.2016.
 */


public class IndexCalculatorImplTest {

    IndexCalculator impl = new IndexCalculatorImpl("src//main//resources//index.txt");
    IndexCalculator notExist = new IndexCalculatorImpl("null");
    IndexCalculator empty = new IndexCalculatorImpl("src//test//java//empty");

    @Before
    public void init(){

        try {
            impl.initialization();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testForZero(){
        YearMonth tmpYear = YearMonth.parse("2015-10");
       for (int i=0 ; i < 8; i++){
            assertEquals(impl.solve(tmpYear.toString(),"2016-01"), new BigDecimal("0.000"));
            tmpYear = tmpYear.plusMonths(1);
        }
    }

    @Test
    public void test01(){
        assertEquals(impl.solve("2015-01","2016-01"), new BigDecimal("0.371") );
    }

    @Test
    public void test02(){
        assertEquals(impl.solve("2014-01","2016-01"),  new BigDecimal("0.761") );
    }
    @Test
    public void test03(){
        assertEquals(impl.solve("2013-01","2016-01"),  new BigDecimal("0.770") );
    }
    @Test
    public void test04(){
        assertEquals(impl.solve("2010-01","2016-01"),  new BigDecimal("0.983"));
    }

    @Test(expected = FileNotFoundException.class)
    public void testNotExistFile() throws FileNotFoundException {
        notExist.initialization();
        assertEquals(notExist.solve("2010-01","2016-01"),  new BigDecimal("0.983"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyFile()  {
        try {
            empty.initialization();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(empty.solve("2010-01","2016-01"),  new BigDecimal("0.983"));
    }


}
