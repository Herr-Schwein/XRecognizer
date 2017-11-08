package com.example.whoami.commonBean;

import java.math.BigDecimal;

/**
 * Created by dunhaozhong on 10/11/17.
 */

public class FaceBean {
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
    private double leftCheekTipX = 0;
    private double leftCheekTipY = 0;
    private double rightCheekTipX = 0;
    private double rightCheekTipY = 0;
    private double leftEarTipX = 0;
    private double leftEarTipY = 0;
    private double rightEarTipX = 0;
    private double rightEarTipY = 0;

    public double[] getALL_X(){
        double [] x = new double[10];
        x[0] = leftEyeX;
        x[1] = rightEyeX;
        x[2] = noseX;
        x[3] = bottomMouthX;
        x[4] = leftMouthX;
        x[5] = rightMouthX;
        x[6] = leftCheekTipX;
        x[7] = rightCheekTipX;
        x[8] = leftEarTipX;
        x[9] = rightEarTipX;
        return x;
    }

    public double[] getALL_Y(){
        double [] y = new double[10];
        y[0] = leftEyeY;
        y[1] = rightEyeY;
        y[2] = noseY;
        y[3] = bottomMouthY;
        y[4] = leftMouthY;
        y[5] = rightMouthY;
        y[6] = leftCheekTipY;
        y[7] = rightCheekTipY;
        y[8] = leftEarTipY;
        y[9] = rightEarTipY;
        return y;
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

    public double getLeftEarTipX() {
        return leftEarTipX;
    }

    public void setLeftEarTipX(double leftEarTipX) {
        this.leftEarTipX = leftEarTipX;
    }

    public double getLeftEarTipY() {
        return leftEarTipY;
    }

    public void setLeftEarTipY(double leftEarTipY) {
        this.leftEarTipY = leftEarTipY;
    }

    public double getRightEarTipX() {
        return rightEarTipX;
    }

    public void setRightEarTipX(double rightEarTipX) {
        this.rightEarTipX = rightEarTipX;
    }

    public double getRightEarTipY() {
        return rightEarTipY;
    }

    public void setRightEarTipY(double rightEarTipY) {
        this.rightEarTipY = rightEarTipY;
    }

    public double getLeftCheekTipX() {
        return leftCheekTipX;
    }

    public void setLeftCheekTipX(double leftCheekTipX) {
        this.leftCheekTipX = leftCheekTipX;
    }

    public double getLeftCheekTipY() {
        return leftCheekTipY;
    }

    public void setLeftCheekTipY(double leftCheekTipY) {
        this.leftCheekTipY = leftCheekTipY;
    }

    public double getRightCheekTipX() {
        return rightCheekTipX;
    }

    public void setRightCheekTipX(double rightCheekTipX) {
        this.rightCheekTipX = rightCheekTipX;
    }

    public double getRightCheekTipY() {
        return rightCheekTipY;
    }

    public void setRightCheekTipY(double rightCheekTipY) {
        this.rightCheekTipY = rightCheekTipY;
    }
}
