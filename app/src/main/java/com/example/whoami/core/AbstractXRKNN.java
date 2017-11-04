package com.example.whoami.core;

import android.util.Log;

import com.example.whoami.commonBean.FaceBean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;


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
            Log.d("Distance to "+faceBean.getName()+": ", dis.toString());
            if(dis.compareTo(result) < 0) {
                result = dis;
                resName = faceBean.getName();
                Log.d("result:------->>> ",result.toString());
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
        return dis1.divide(dis2,9,BigDecimal.ROUND_HALF_UP);
    }

    protected ArrayList<BigDecimal> calAllDistances(FaceBean faceBean) {
        ArrayList<BigDecimal> res = new ArrayList<>();

        double[] Xs = faceBean.getALL_X();
        double[] Ys = faceBean.getALL_Y();
        BigDecimal total = new BigDecimal(0);

        for(int i = 0; i < Xs.length-1; i++){
            for( int j = i + 1; j < Xs.length; j++ ){
                BigDecimal dis = calItemDistance(Xs[i], Ys[i], Xs[j], Ys[j]);
                res.add(dis);
                total = total.add(dis);
            }
        }

        res = scaleDistance(res, total);
        return res;
    }

    protected BigDecimal calItemDistance(double x1, double y1, double x2, double y2){
        return BigDecimal.valueOf(
                Math.sqrt(
                        Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)
                )
        ).setScale(9, BigDecimal.ROUND_HALF_UP);
    }

    protected ArrayList<BigDecimal> scaleDistance(ArrayList<BigDecimal> value, BigDecimal total){
        ArrayList<BigDecimal> res = new ArrayList<>();
        int n = value.size();
        BigDecimal ave = total.divide(BigDecimal.valueOf(n), 9, BigDecimal.ROUND_HALF_UP);

        for(BigDecimal item : value ){
            item = item.subtract(ave);
            item = item.divide(ave, 9, BigDecimal.ROUND_HALF_UP);
            res.add(item);
        }
        return res;
    }


}
