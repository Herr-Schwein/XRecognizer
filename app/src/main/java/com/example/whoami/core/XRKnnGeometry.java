package com.example.whoami.core;

import com.example.whoami.commonBean.Faces;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * Created by ZDH on 10/18/17.
 */

public class XRKnnGeometry extends AbstractXRKNN{


    /**
     * Geometry based method
     * @param target
     * @param faces
     * @return
     */
    public BigDecimal calDistanceOfFaces(Faces target, Faces faces){
        BigDecimal dis = BigDecimal.valueOf(0);
        ArrayList<BigDecimal> tDist = new ArrayList<>();
        ArrayList<BigDecimal> fDist = new ArrayList<>();
        Double[] tx = target.get_X_Values();
        Double[] ty = target.get_Y_Values();
        Double[] fx = faces.get_X_Values();
        Double[] fy = faces.get_Y_Values();
        for(int i = 0; i < tx.length - 1; i++){
            for( int j = i+1; j < tx.length; j++ ){
                tDist.add(calDistance(tx[i], ty[i], tx[j], tx[j]));
                fDist.add(calDistance(fx[i], fy[i], fx[j], fx[j]));
            }
        }

        for(int i = 0; i < fDist.size() - 1; i++){
            for(int j = i + 1; j < fDist.size(); j++){
                BigDecimal ratio1 = calRatio(tDist.get(i), tDist.get(j));
                BigDecimal ratio2 = calRatio(fDist.get(i), fDist.get(j));
                dis = dis.add( ratio1.subtract(ratio2).abs() );
            }
        }
        return dis;
    }
}
