import com.lemon.Exceptions.ProblemFileException;
import com.lemon.Solution.IndexCalculator;
import com.lemon.Solution.IndexCalculatorImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.YearMonth;

import static org.junit.Assert.assertEquals;


public class IndexCalculatorImplTest {

    IndexCalculator impl = new IndexCalculatorImpl("src//main//resources//index.txt");
    IndexCalculator notExist = new IndexCalculatorImpl("null");
    IndexCalculator empty = new IndexCalculatorImpl("src//test//java//empty");

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void init(){

        try {
            impl.initialization();
        } catch (ProblemFileException e) {
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
    public void testResult01(){
        assertEquals(impl.solve("2015-01","2016-01"), new BigDecimal("0.371") );
    }
    @Test
    public void testResult02(){
        assertEquals(impl.solve("2014-01","2016-01"),  new BigDecimal("0.761") );
    }
    @Test
    public void testResult03(){
        assertEquals(impl.solve("2013-01","2016-01"),  new BigDecimal("0.770") );
    }
    @Test
    public void testResult04(){
        assertEquals(impl.solve("2010-01","2016-01"),  new BigDecimal("0.983"));
    }

    @Test
    public void testNotExistFile() throws FileNotFoundException, ProblemFileException {
        expectedEx.expect(ProblemFileException.class);
        expectedEx.expectMessage("not found!");

        notExist.initialization();
        assertEquals(notExist.solve("2010-01","2016-01"),  new BigDecimal("0.983"));
    }

    @Test
    public void testEmptyFile() throws ProblemFileException {

        expectedEx.expect(ProblemFileException.class);
        expectedEx.expectMessage("Not all indexes are loaded!");
        empty.initialization();
        assertEquals(empty.solve("2010-01","2016-01"),  new BigDecimal("0.983"));
    }


}
