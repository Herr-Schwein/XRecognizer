package com.example.whoami.service;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.SparseArray;

import com.example.whoami.commonBean.FaceBean;
import com.example.whoami.core.AbstractGeometryBased;
import com.example.whoami.core.KnnEuclidean;
import com.example.whoami.core.MinRatioGap;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import java.util.ArrayList;

/**
 * Created by ZDH on 11/2/17.
 */

public class FaceDetectorService {

    private final float RIDUS = 2;

    FaceDetector faceDetector;
    FacesDBService facesDBService;
    Paint paint;
    Canvas canvas;

    private AbstractGeometryBased identifyCore = new MinRatioGap();
//    private AbstractGeometryBased identifyCore = new KnnEuclidean();

    public FaceDetectorService(FragmentActivity fragmentActivity, Paint paint, Canvas canvas){
        this.faceDetector = new FaceDetector.Builder(fragmentActivity.getApplicationContext())
                .setTrackingEnabled(false)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .setMode(FaceDetector.ACCURATE_MODE)
                .build();
        this.paint = paint;
        this.canvas = canvas;
        this.facesDBService = new FacesDBService(fragmentActivity.getApplicationContext());
    }

    public void saveNewFace(String name, Frame frame){

        SparseArray<Face> sparseArray = deteceFace(frame);
        for(int i = 0; i < sparseArray.size(); i++){
            Face face = sparseArray.valueAt(i);
            facesDBService.insert(detectLandmarks(face, name));
            //drawRecOnFaceView(face);
        }
    }

    public String identifyFace(Frame frame){
        SparseArray<Face> sparseArray = deteceFace(frame);
        String res = "Sorry, We haven't met before!";
        ArrayList<FaceBean> faceBeansList = queryRecordFaces();
        for(int i = 0; i < sparseArray.size(); i++) {
            Face face = sparseArray.valueAt(i);
            FaceBean faceBean = detectLandmarks(face, "");
            res = identifyCore.calNearestFaces(1, faceBean, faceBeansList);
        }
        return res;
    }

    private ArrayList<FaceBean> queryRecordFaces(){
        return facesDBService.selectAll();
    }

    private SparseArray<Face>  deteceFace(Frame frame){
        return faceDetector.detect(frame);
    }

    private FaceBean detectLandmarks(Face face, String name) {
        FaceBean faceBean = new FaceBean();

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1f);
        for(Landmark landmark : face.getLandmarks()){
            int cx = (int)(landmark.getPosition().x);
            int cy = (int)(landmark.getPosition().y);
            switch(landmark.getType()){
                case Landmark.BOTTOM_MOUTH:
                    canvas.drawCircle(cx,cy,RIDUS,paint);
                    faceBean.setBottomMouthX(cx);
                    faceBean.setBottomMouthY(cy);
                    break;
                case Landmark.LEFT_EYE:
                    canvas.drawCircle(cx,cy,RIDUS,paint);
                    faceBean.setLeftEyeX(cx);
                    faceBean.setLeftEyeY(cy);
                    break;
                case Landmark.RIGHT_EYE:
                    canvas.drawCircle(cx,cy,RIDUS,paint);
                    faceBean.setRightEyeX(cx);
                    faceBean.setRightEyeY(cy);
                    break;
                case Landmark.NOSE_BASE:
                    canvas.drawCircle(cx,cy,RIDUS,paint);
                    faceBean.setNoseX(cx);
                    faceBean.setNoseY(cy);
                    break;
                case Landmark.LEFT_MOUTH:
                    canvas.drawCircle(cx,cy,RIDUS,paint);
                    faceBean.setLeftMouthX(cx);
                    faceBean.setLeftMouthY(cy);
                    break;
                case Landmark.RIGHT_MOUTH:
                    canvas.drawCircle(cx,cy,RIDUS,paint);
                    faceBean.setRightMouthX(cx);
                    faceBean.setRightMouthY(cy);
                    break;
                case Landmark.LEFT_EAR_TIP:
                    canvas.drawCircle(cx,cy,RIDUS,paint);
                    faceBean.setLeftEarTipX(cx);
                    faceBean.setLeftEarTipY(cy);
                    break;
                case Landmark.RIGHT_EAR_TIP:
                    canvas.drawCircle(cx,cy,RIDUS,paint);
                    faceBean.setRightEarTipX(cx);
                    faceBean.setRightEarTipY(cy);
                    break;
                case Landmark.LEFT_CHEEK:
                    canvas.drawCircle(cx,cy,RIDUS,paint);
                    faceBean.setLeftCheekTipX(cx);
                    faceBean.setLeftCheekTipY(cy);
                    break;
                case Landmark.RIGHT_CHEEK:
                    canvas.drawCircle(cx,cy,RIDUS,paint);
                    faceBean.setRightCheekTipX(cx);
                    faceBean.setRightCheekTipY(cy);
                    break;
            }
        }
        faceBean.setName(name);
        return faceBean;
    }
}
