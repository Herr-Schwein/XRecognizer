package uottawa.core.KNN;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.SortedMap;

import uottawa.commonBean.Faces;

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
    public static String calNearestFaces(int k, Faces target, ArrayList<Faces> recordFaces){
        BigDecimal result = new BigDecimal(Double.MAX_VALUE).setScale(4,BigDecimal.ROUND_HALF_UP);
        String resName = target.getName();
        for(Faces faces : recordFaces){
            BigDecimal dis = calEuclideanDisOfFaces(target, faces);
            if(dis.compareTo(result) < 0) {
                result = dis;
                resName = faces.getName();
            }
        }
        return resName;
    }

    private static BigDecimal calEuclideanDisOfFaces(Faces faces1, Faces faces2){
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
