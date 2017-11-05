package com.example.whoami.core;

import android.util.Log;

import com.example.whoami.commonBean.FaceBean;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by ZDH on 10/18/17.
 */

public class XRKnnGeometry extends AbstractXRKNN{


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
                ratio1.add(calRatio(dis1.get(i), dis1.get(j)));
                ratio2.add(calRatio(dis2.get(i), dis2.get(j)));
            }
        }

        BigDecimal val = BigDecimal.valueOf(0).setScale(9,BigDecimal.ROUND_HALF_UP);

        for(int i = 0; i < ratio1.size(); i++){
            val = val.add( calRatio(ratio1.get(i), ratio2.get(i)) );
            Log.d("Ratio1 ", ratio1.get(i).toString());
            Log.d("Ratio2 ", ratio2.get(i).toString());
            Log.d("val: ",  val.toString());
        }

        return val;
    }
}
