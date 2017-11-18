package com.example.whoami.core;

import android.util.Log;

import com.example.whoami.commonBean.FaceBean;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by ZDH on 10/18/17.
 */

public class MinRatioGap extends AbstractGeometryBased {


    /**
     * Geometry based method
     * @param faceBean1: target
     * @param faceBean2: record
     * @return
     */
    public BigDecimal calDistanceOfFaces(FaceBean faceBean1, FaceBean faceBean2){

        // faceBean1 distances
        ArrayList<BigDecimal> dis1 = calAllDistances(faceBean1);
        ArrayList<BigDecimal> ratio1 = new ArrayList<>();

        // faceBean2 distances
        ArrayList<BigDecimal> dis2 = calAllDistances(faceBean2);
        ArrayList<BigDecimal> ratio2 = new ArrayList<>();

        //Don't calculate ratio if dis equals to 0
        for( int i = 0; i < dis1.size() - 1; i++ ){
            for( int j = i + 1; j < dis1.size(); j++ ) {
                if ( dis1.get(i).doubleValue() == 0 || dis2.get(i).doubleValue() == 0
                        || dis1.get(j).doubleValue() == 0 || dis2.get(j).doubleValue() == 0 ){
                    continue;
                }
                if(dis1.get(i).compareTo(dis1.get(j)) >= 0) {
                    ratio1.add(calRatio(dis1.get(i), dis1.get(j)));
                    ratio2.add(calRatio(dis2.get(i), dis2.get(j)));
                }
            }
        }

        //Normalize the ratios to [-1, 1]
        ratio1 = Normalization.normalize(ratio1, 1);
        ratio2 = Normalization.normalize(ratio2, 1);

        BigDecimal val = BigDecimal.valueOf(0).setScale(9,BigDecimal.ROUND_HALF_UP);

        for(int i = 0; i < ratio1.size(); i++){
//          Log.d("Ratio1 ", ratio1.get(i).toString());
//          Log.d("Ratio2 ", ratio2.get(i).toString());

            if(ratio1.get(i).doubleValue() != 0 && ratio2.get(i).doubleValue() != 0) {
                BigDecimal gap = ratio1.get(i).subtract(ratio2.get(i));
                BigDecimal mean = ratio1.get(i).add(ratio2.get(i));
                mean = mean.divide(BigDecimal.valueOf(2), 9, BigDecimal.ROUND_HALF_UP);
                val = val.add((gap.divide(mean, 9, BigDecimal.ROUND_HALF_UP)).pow(2));
//              Log.d("val: ",  val.toString());
            }
        }

//        return BigDecimal.valueOf(Math.sqrt(val.doubleValue())).setScale(4, BigDecimal.ROUND_HALF_UP);
        return BigDecimal.valueOf(val.doubleValue()).setScale(4, BigDecimal.ROUND_HALF_UP);
    }
}
