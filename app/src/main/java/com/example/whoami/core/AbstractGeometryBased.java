package com.example.whoami.core;

import android.util.Log;

import com.example.whoami.commonBean.FaceBean;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by ZDH on 10/18/17.
 */

public abstract class AbstractGeometryBased {

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
            Log.d("Distance to "+faceBean.getName()+": ", dis.toString());
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

    /**
     * dis2 cannot be 0
     * @param dis1
     * @param dis2
     * @return
     */
    protected BigDecimal calRatio(BigDecimal dis1, BigDecimal dis2){
        return dis1.divide(dis2,9,BigDecimal.ROUND_HALF_UP);
    }

    protected ArrayList<BigDecimal> calAllDistances(FaceBean faceBean) {
        ArrayList<BigDecimal> res = new ArrayList<>();

        double[] Xs = faceBean.getALL_X();
        double[] Ys = faceBean.getALL_Y();

        //Do not use data of left and right ears
        for(int i = 0; i < Xs.length-1; i++){
            for( int j = i + 1; j < Xs.length; j++ ){
                BigDecimal dis = calItemDistance(Xs[i], Ys[i], Xs[j], Ys[j]);
                res.add(dis);
            }
        }
        return res;
    }

    protected BigDecimal calItemDistance(double x1, double y1, double x2, double y2){
        return BigDecimal.valueOf(
                Math.sqrt(
                        Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)
                )
        ).setScale(9, BigDecimal.ROUND_HALF_UP);
    }
}
