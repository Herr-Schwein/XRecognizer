package com.example.whoami.core;

import android.util.Log;

import com.example.whoami.commonBean.FaceBean;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by ZDH on 10/18/17.
 */

public class XRKnnEuler extends AbstractXRKNN{

    /**
     * Geometry based method
     * @param faceBean1
     * @param faceBean2
     * @return
     */
    public BigDecimal calDistanceOfFaces(FaceBean faceBean1, FaceBean faceBean2){

        // faceBean1 distances
        ArrayList<BigDecimal> dis1 = calAllDistances(faceBean1);
        ArrayList<BigDecimal> ratio1 = new ArrayList<>();

        // faceBean2 distances
        ArrayList<BigDecimal> dis2 = calAllDistances(faceBean2);
        ArrayList<BigDecimal> ratio2 = new ArrayList<>();

        for( int i = 0; i < dis1.size() - 1; i++ ){
            for( int j = i + 1; j < dis1.size(); j++ ) {
                ratio1.add(calRatio(dis1.get(i), dis1.get(i + 1)));
                ratio2.add(calRatio(dis2.get(i), dis2.get(i + 1)));
            }
        }

        BigDecimal val = new BigDecimal(0).setScale(9,BigDecimal.ROUND_HALF_UP);
        for(int i = 0; i < ratio1.size(); i++){
            val = val.add( (ratio1.get(i).subtract(ratio2.get(i))).pow(2) );
        }

        return BigDecimal.valueOf(Math.sqrt(val.doubleValue())).setScale(4, BigDecimal.ROUND_HALF_UP);
    }


}
