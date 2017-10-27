package uottawa.core.KNN;

import java.math.BigDecimal;

import uottawa.commonBean.Faces;

/**
 * Created by ZDH on 10/18/17.
 */

public class XRKnnEuler extends AbstractXRKNN{

    /**
     * Geometry based method
     * @param faces1
     * @param faces2
     * @return
     */
    public BigDecimal calDistanceOfFaces(Faces faces1, Faces faces2){
        return BigDecimal.valueOf(
                Math.sqrt(
                        Math.pow(faces1.getRatioEyesAndNoseMouth().doubleValue() - faces2.getRatioEyesAndNoseMouth().doubleValue(), 2)
                                + Math.pow(faces1.getRatioLeftEyesAndNose().doubleValue() - faces2.getRatioLeftEyesAndNose().doubleValue(), 2)
                                + Math.pow(faces1.getRatioLeftEyesBottomMouth().doubleValue() - faces2.getRatioLeftEyesBottomMouth().doubleValue(), 2)
                                + Math.pow(faces1.getRatioRightEyesAndNose().doubleValue() - faces2.getRatioRightEyesAndNose().doubleValue(), 2)
                                + Math.pow(faces1.getRatioRightEyesBottomMouth().doubleValue() - faces2.getRatioRightEyesBottomMouth().doubleValue(), 2)
                )
        ).setScale(4,BigDecimal.ROUND_HALF_UP);
    }
}
