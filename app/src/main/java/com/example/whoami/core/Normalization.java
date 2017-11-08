package com.example.whoami.core;

import android.util.Log;

import com.example.whoami.commonBean.DistributedSummary;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by ZDH on 11/5/17.
 */

public class Normalization {

    /**
     * normalize the data depends on type
     * @param type: 0: mean normalization;
     *              1: MaxMin normalization;
     * @return type == 0: [-1, 1]
     *         type == 1: [0,  1]
     */
    public static ArrayList<BigDecimal> normalize(ArrayList<BigDecimal> value, int type){
        ArrayList<BigDecimal> res = new ArrayList<>();
        DistributedSummary distributedSummary = new DistributedSummary();
        distributedSummary.calSummaries(value);
        switch(type){
            case 0:
                res = normalizeDistance(value, distributedSummary.getMean(), distributedSummary.getMaxValue(), distributedSummary.getMinValue());
                break;
            case 1:
                res = normalizeDistance(value, distributedSummary.getMaxValue(), distributedSummary.getMinValue());
                break;
        }
        return res;
    }


    /**
     * return = (x - u) / (maxDis - minDis);  u is mean
     * return = [-1, 1]
     * @param value
     * @param mean
     * @param maxValue
     * @param minValue
     * @return
     */
    private static ArrayList<BigDecimal> normalizeDistance(ArrayList<BigDecimal> value, BigDecimal mean, BigDecimal maxValue, BigDecimal minValue){
        ArrayList<BigDecimal> res = new ArrayList<>();
        BigDecimal gap = maxValue.subtract(minValue);

        for(BigDecimal item : value ){
            item = item.subtract(mean);
            item = item.divide(gap, 9, BigDecimal.ROUND_HALF_UP);
            res.add(item);
        }
        return res;
    }

    /**
     * return = (x - minDis) / (maxDis - minDis)
     * return = [0, 1]
     * @param value
     * @param maxValue
     * @param minValue
     * @return
     */
    private static ArrayList<BigDecimal> normalizeDistance(ArrayList<BigDecimal> value, BigDecimal maxValue, BigDecimal minValue){
        ArrayList<BigDecimal> res = new ArrayList<>();

        BigDecimal gap = maxValue.subtract(minValue);

//        Log.d("maxDis: ",  maxValue.toString());
//        Log.d("minDis: ",  minValue.toString());
//        Log.d("gap: ",  gap.toString());
        for(BigDecimal item : value ){
            item = item.subtract(minValue);
            item = item.divide(gap, 9, BigDecimal.ROUND_HALF_UP);
            res.add(item);
        }
        return res;
    }

}
