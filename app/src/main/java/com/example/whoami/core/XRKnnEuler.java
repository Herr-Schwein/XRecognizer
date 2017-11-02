package com.example.whoami.core;

import com.example.whoami.commonBean.FaceBean;

import java.math.BigDecimal;


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
        return BigDecimal.valueOf(
                Math.sqrt(
                        Math.pow(faceBean1.getRatioEyesAndNoseMouth().doubleValue() - faceBean2.getRatioEyesAndNoseMouth().doubleValue(), 2)
                                + Math.pow(faceBean1.getRatioLeftEyesAndNose().doubleValue() - faceBean2.getRatioLeftEyesAndNose().doubleValue(), 2)
                                + Math.pow(faceBean1.getRatioLeftEyesBottomMouth().doubleValue() - faceBean2.getRatioLeftEyesBottomMouth().doubleValue(), 2)
                                + Math.pow(faceBean1.getRatioRightEyesAndNose().doubleValue() - faceBean2.getRatioRightEyesAndNose().doubleValue(), 2)
                                + Math.pow(faceBean1.getRatioRightEyesBottomMouth().doubleValue() - faceBean2.getRatioRightEyesBottomMouth().doubleValue(), 2)
                )
        ).setScale(4,BigDecimal.ROUND_HALF_UP);
    }
}
