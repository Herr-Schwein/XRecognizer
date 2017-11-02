package com.example.whoami.commonBean;

import java.math.BigDecimal;

/**
 * Created by dunhaozhong on 10/11/17.
 */

public class Faces {
    private int id;
    private String name;
    private double leftEyeX = 0;
    private double leftEyeY = 0;
    private double rightEyeX = 0;
    private double rightEyeY = 0;
    private double noseX = 0;
    private double noseY = 0;
    private double leftMouthX = 0;
    private double leftMouthY = 0;
    private double rightMouthX = 0;
    private double rightMouthY = 0;
    private double bottomMouthX = 0;
    private double bottomMouthY = 0;
    private BigDecimal ratioLeftEyesAndNose = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
    private BigDecimal ratioRightEyesAndNose = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
    private BigDecimal ratioLeftEyesBottomMouth = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
    private BigDecimal ratioRightEyesBottomMouth = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
    private BigDecimal ratioEyesAndNoseMouth = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);

    /**
     * result = distance of eyes / distance of left eye to nose
     * @return
     */
    public void calRatioLeftEyesAndNose() {
        BigDecimal disEyes = new BigDecimal(Math.sqrt(Math.pow(rightEyeX - leftEyeX, 2) + Math.pow(rightEyeY - rightEyeY, 2)))
                .setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal disLeftEyeNose = new BigDecimal(Math.sqrt(Math.pow(leftEyeX - noseX, 2) + Math.pow(leftEyeY - noseY, 2)))
                .setScale(4,BigDecimal.ROUND_HALF_UP);
        if (disLeftEyeNose.equals(0)) {
            ratioLeftEyesAndNose = BigDecimal.valueOf(-1);
        } else {
            ratioLeftEyesAndNose = disEyes.divide(disLeftEyeNose,4, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * result = distance of eyes / distance of right eye to nose
     * @return
     */
    public void calRatioRightEyesAndNose(){
        BigDecimal disEyes = new BigDecimal(Math.sqrt( Math.pow(rightEyeX - leftEyeX, 2) + Math.pow(rightEyeY - rightEyeY, 2) ))
                .setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal disRightEyeNose = new BigDecimal(Math.sqrt( Math.pow(rightEyeX - noseX, 2) + Math.pow(rightEyeY - noseY, 2)))
                .setScale(4,BigDecimal.ROUND_HALF_UP);
        if(disRightEyeNose.equals(0)){
            ratioRightEyesAndNose = BigDecimal.valueOf(-1);
        } else {
            ratioRightEyesAndNose = disEyes.divide(disRightEyeNose,4, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * result = distance of eyes / distance of left eye to BottomMouth
     * @return
     */
    public void calRatioLeftEyesBottomMouth(){
        BigDecimal disEyes = new BigDecimal(Math.sqrt( Math.pow(rightEyeX - leftEyeX, 2) + Math.pow(rightEyeY - rightEyeY, 2) ))
                .setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal disLeftEyeBottomMouth = new BigDecimal(Math.sqrt( Math.pow(leftEyeX - bottomMouthX, 2) + Math.pow(leftEyeY - bottomMouthY, 2)))
                .setScale(4,BigDecimal.ROUND_HALF_UP);
        if(disLeftEyeBottomMouth.equals(0)){
            ratioLeftEyesBottomMouth = BigDecimal.valueOf(-1);
        } else {
            ratioLeftEyesBottomMouth = disEyes.divide(disLeftEyeBottomMouth,4, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * result = distance of eyes / distance of left eye to nose
     * @return
     */
    public void calRatioRightEyesBottomMouth(){
        BigDecimal disEyes = new BigDecimal(Math.sqrt( Math.pow(rightEyeX - leftEyeX, 2) + Math.pow(rightEyeY - rightEyeY, 2) ))
                .setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal disLeftEyeNose = new BigDecimal(Math.sqrt( Math.pow(leftEyeX - bottomMouthX, 2) + Math.pow(leftEyeY - bottomMouthY, 2)))
                .setScale(4,BigDecimal.ROUND_HALF_UP);
        if(disLeftEyeNose.equals(0)){
            ratioRightEyesBottomMouth = BigDecimal.valueOf(-1);
        } else {
            ratioRightEyesBottomMouth = disEyes.divide(disLeftEyeNose,4, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * result = distance of eyes / distance of bottom mouth to nose
     * @return
     */
    public void calRatioEyesAndNoseBottomMouth(){
        BigDecimal disEyes = new BigDecimal(Math.sqrt( Math.pow(rightEyeX - leftEyeX, 2) + Math.pow(rightEyeY - rightEyeY, 2) ))
                .setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal disNoseMouth = new BigDecimal(Math.sqrt( Math.pow(noseX - bottomMouthX, 2) + Math.pow(noseY - bottomMouthY, 2)))
                .setScale(4,BigDecimal.ROUND_HALF_UP);
        if(disNoseMouth.equals(0)){
            ratioEyesAndNoseMouth = BigDecimal.valueOf(-1);
        } else {
            ratioEyesAndNoseMouth = disEyes.divide(disNoseMouth,4, BigDecimal.ROUND_HALF_UP);
        }
    }

    public Double[] get_X_Values(){
        Double[] res = new Double[6];
        res[0] = leftEyeX;
        res[1] = rightEyeX;
        res[2] = noseX;
        res[3] = leftMouthX;
        res[4] = rightMouthX;
        res[5] = bottomMouthX;
        return res;
    }

    public Double[] get_Y_Values(){
        Double[] res = new Double[6];
        res[0] = leftEyeY;
        res[1] = rightEyeY;
        res[2] = noseY;
        res[3] = leftMouthY;
        res[4] = rightMouthY;
        res[5] = bottomMouthY;
        return res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLeftEyeX() {
        return leftEyeX;
    }

    public void setLeftEyeX(double leftEyeX) {
        this.leftEyeX = leftEyeX;
    }

    public double getLeftEyeY() {
        return leftEyeY;
    }

    public void setLeftEyeY(double leftEyeY) {
        this.leftEyeY = leftEyeY;
    }

    public double getRightEyeX() {
        return rightEyeX;
    }

    public void setRightEyeX(double rightEyeX) {
        this.rightEyeX = rightEyeX;
    }

    public double getRightEyeY() {
        return rightEyeY;
    }

    public void setRightEyeY(double rightEyeY) {
        this.rightEyeY = rightEyeY;
    }

    public double getNoseX() {
        return noseX;
    }

    public void setNoseX(double noseX) {
        this.noseX = noseX;
    }

    public double getNoseY() {
        return noseY;
    }

    public void setNoseY(double noseY) {
        this.noseY = noseY;
    }

    public double getLeftMouthX() {
        return leftMouthX;
    }

    public void setLeftMouthX(double leftMouthX) {
        this.leftMouthX = leftMouthX;
    }

    public double getLeftMouthY() {
        return leftMouthY;
    }

    public void setLeftMouthY(double leftMouthY) {
        this.leftMouthY = leftMouthY;
    }

    public double getRightMouthX() {
        return rightMouthX;
    }

    public void setRightMouthX(double rightMouthX) {
        this.rightMouthX = rightMouthX;
    }

    public double getRightMouthY() {
        return rightMouthY;
    }

    public void setRightMouthY(double rightMouthY) {
        this.rightMouthY = rightMouthY;
    }

    public double getBottomMouthX() {
        return bottomMouthX;
    }

    public void setBottomMouthX(double bottomMouthX) {
        this.bottomMouthX = bottomMouthX;
    }

    public double getBottomMouthY() {
        return bottomMouthY;
    }

    public void setBottomMouthY(double bottomMouthY) {
        this.bottomMouthY = bottomMouthY;
    }

    public BigDecimal getRatioLeftEyesAndNose() {
        return ratioLeftEyesAndNose;
    }

    public void setRatioLeftEyesAndNose(BigDecimal ratioLeftEyesAndNose) {
        this.ratioLeftEyesAndNose = ratioLeftEyesAndNose;
    }

    public BigDecimal getRatioRightEyesAndNose() {
        return ratioRightEyesAndNose;
    }

    public void setRatioRightEyesAndNose(BigDecimal ratioRightEyesAndNose) {
        this.ratioRightEyesAndNose = ratioRightEyesAndNose;
    }

    public BigDecimal getRatioLeftEyesBottomMouth() {
        return ratioLeftEyesBottomMouth;
    }

    public void setRatioLeftEyesBottomMouth(BigDecimal ratioLeftEyesBottomMouth) {
        this.ratioLeftEyesBottomMouth = ratioLeftEyesBottomMouth;
    }

    public BigDecimal getRatioRightEyesBottomMouth() {
        return ratioRightEyesBottomMouth;
    }

    public void setRatioRightEyesBottomMouth(BigDecimal ratioRightEyesBottomMouth) {
        this.ratioRightEyesBottomMouth = ratioRightEyesBottomMouth;
    }

    public BigDecimal getRatioEyesAndNoseMouth() {
        return ratioEyesAndNoseMouth;
    }

    public void setRatioEyesAndNoseMouth(BigDecimal ratioEyesAndNoseMouth) {
        this.ratioEyesAndNoseMouth = ratioEyesAndNoseMouth;
    }
}
