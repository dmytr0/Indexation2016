package Solution; /**
 * admin on 03.02.2016.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;


public class IndexCalculator {

    String src;
    static HashMap<YearMonth, Float> indexes = new HashMap<YearMonth, Float>();
    public static String[] enumBasePer;
    public static String[] enumCalcPer;
    public  int minzp = 1378;                                     // минимальная зарплата по умолчанию
    private YearMonth startIndexesPeriod = YearMonth.of(1999, 10);
    private YearMonth endIndexesPeriod;
    private static final YearMonth startCalc = YearMonth.of(2016,1);

    public IndexCalculator(String src){
        setSrc(src);
        initialization();
    }

    public void initialization(){

        setMinzp(1378);
        fillIndex(src);
        fillBasePeriod();
        fillCalcPeriod();
    }

    //fill indexes
    public void fillIndex(String file) {

        try {
            BufferedReader fr = new BufferedReader(new FileReader(file));
            String currentIndex;
            while ((currentIndex = fr.readLine()) != null) {

                if (!currentIndex.equals("")) {
                    YearMonth yearMonth = YearMonth.parse(currentIndex.split("\t")[0].split("\\.")[2]
                            + "-" + currentIndex.split("\t")[0].split("\\.")[1]);
                    indexes.put(yearMonth, Float.parseFloat(currentIndex.split("\t")[1].replaceAll(",", ".")));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ReadingError");
            e.printStackTrace();
        }
    }

    //fill avalible base period

    public void fillBasePeriod()
    {
        enumBasePer = new String[indexes.keySet().size()];
        YearMonth tmpPeriod = getStartIndexesPeriod();
        for (int i = 0; i < enumBasePer.length; i++) {
            enumBasePer[i] = tmpPeriod.toString();
            tmpPeriod = tmpPeriod.plusMonths(1);
        }
        setEndIndexesPeriod(tmpPeriod.minusMonths(1));
    }

    // fill avalible calculation period

    public void fillCalcPeriod(){
        YearMonth tmpPeriod;
        YearMonth maxAvalible = getEndIndexesPeriod().plusMonths(2);
        if (maxAvalible.compareTo(getStartCalc())<0)
            throw new IllegalArgumentException("Avalible period less the calc period");
        tmpPeriod = getStartCalc();
        ArrayList<String> list = new ArrayList<String>();
        while(tmpPeriod.compareTo(maxAvalible) <= 0) {
            list.add(tmpPeriod.toString());
            tmpPeriod = tmpPeriod.plusMonths(1);
        }
        enumCalcPer = new String[list.size()];
        for(int i=0; i< list.size(); i++){
            enumCalcPer[i] = list.get(i);
        }

    }

    /**
     * @param basePer base period such as"2007-12", not null
     * @param calcPeriod pay period such as"2007-12", not null
     * @return indexation coefficient, not null
     * @throws NullPointerException
     */

    static public float solve (String basePer, String calcPeriod)throws NullPointerException{
        if(basePer == null || calcPeriod == null)
            throw new NullPointerException("Period is null!");

        float coefficient = 1.0f;
        float bound = 0.0f;
        YearMonth base = YearMonth.parse(basePer);
        YearMonth calc = YearMonth.parse(calcPeriod);
        ArrayList<Float> excessLimit = new ArrayList<Float>();

        if (base.compareTo(calc.minusMonths(2))>=0) {
            return 0.0f;
        }

        for(YearMonth i = base.plusMonths(1); i.compareTo(calc.minusMonths(2))< 0; i = i.plusMonths(1) ){

            if(bound != 0) bound *= indexes.get(i);
            else bound = indexes.get(i);

            if(bound >= 1.03) {
                excessLimit.add(new BigDecimal(bound).setScale(3, RoundingMode.HALF_UP).floatValue());
                bound = 0;
            }
        }

        for(float count: excessLimit) coefficient *= count;

        coefficient = coefficient - 1;
        if (coefficient < 0) coefficient =0;
        coefficient = new BigDecimal(coefficient).setScale(3, RoundingMode.HALF_UP).floatValue();
        return coefficient;
    }


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public  String[] getEnumCalcPer() {
        return enumCalcPer;
    }

    public  String[] getEnumBasePer() {
        return enumBasePer;
    }

    public static YearMonth getStartCalc() {
        return startCalc;
    }

    public YearMonth getStartIndexesPeriod() {
        return startIndexesPeriod;
    }

    public YearMonth getEndIndexesPeriod() {
        return endIndexesPeriod;
    }

    public void setEndIndexesPeriod(YearMonth endIndexesPeriod) {
        this.endIndexesPeriod = endIndexesPeriod;
    }

    public  int getMinzp() {
        return minzp;
    }

    public  void setMinzp(int minzp) {
        this.minzp = minzp;
    }



}
