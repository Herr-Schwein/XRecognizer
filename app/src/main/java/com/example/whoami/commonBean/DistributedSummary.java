package com.example.whoami.commonBean;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by ZDH on 11/5/17.
 */

public class DistributedSummary {

    private BigDecimal mean;
    private BigDecimal maxValue;
    private BigDecimal minValue;

    public void calSummaries(ArrayList<BigDecimal> value){
        BigDecimal maxValue = BigDecimal.valueOf(Integer.MIN_VALUE);
        BigDecimal minValue = BigDecimal.valueOf(Integer.MAX_VALUE);
        BigDecimal total = BigDecimal.valueOf(0);

        for( BigDecimal item : value ){
            maxValue = maxValue.compareTo(item) <= 0 ? item : maxValue;
            minValue = minValue.compareTo(item) >= 0 ? item : minValue;
            total = total.add(item);
        }

        this.mean = total.divide(BigDecimal.valueOf(value.size()), 9, BigDecimal.ROUND_HALF_UP);
        this.maxValue = maxValue;
        this.minValue = minValue;

    }

    public BigDecimal getMean() {
        return mean;
    }

    public void setMean(BigDecimal mean) {
        this.mean = mean;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }
}
