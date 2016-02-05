package com.lemon.Solution;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

/**
 * Created by admin on 04.02.2016.
 */
public interface IndexCalculator {

    public BigDecimal solve (String basePer, String calcPeriod);
    public  String[] getEnumBasePer();
    public  String[] getEnumCalcPer();
    public  BigDecimal getMinzp();
    public void initialization() throws FileNotFoundException;
    public void setSrc(String src);

}
