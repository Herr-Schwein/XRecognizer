package com.example.whoami.core;

import com.example.whoami.commonBean.FaceBean;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by ZDH on 10/18/17.
 */

public abstract class AbstractXRKNN {

    /**
     * Using KNN to get nearest faces
     * @param k
     * @param target
     * @param recordFaces
     * @return
     */
    public String calNearestFaces(int k, FaceBean target, ArrayList<FaceBean> recordFaces){
        BigDecimal result = new BigDecimal(Double.MAX_VALUE).setScale(4,BigDecimal.ROUND_HALF_UP);
        String resName = target.getName();
        for(FaceBean faceBean : recordFaces){
            BigDecimal dis = calDistanceOfFaces(target, faceBean);
            if(dis.compareTo(result) < 0) {
                result = dis;
                resName = faceBean.getName();
            }
        }
        return resName;
    }

    /**
     * Calculate Eulerian distance
     * @param faceBean1
     * @param faceBean2
     * @return
     */
    abstract protected BigDecimal calDistanceOfFaces(FaceBean faceBean1, FaceBean faceBean2);

    protected BigDecimal calRatio(BigDecimal dis1, BigDecimal dis2){
        return dis1.divide(dis2,4,BigDecimal.ROUND_HALF_UP);
    }

    protected BigDecimal calDistance(double x1, double y1, double x2, double y2){
        return BigDecimal.valueOf(
                Math.sqrt(
                        Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)
                )
        ).setScale(4, BigDecimal.ROUND_HALF_UP);
    }
}
