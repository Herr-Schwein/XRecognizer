package com.example.whoami.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.whoami.commonBean.FaceBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;


/**
 * Created by dunhaozhong on 10/11/17.
 */

public class FacesDAOImpl implements IFacesDAO {

    @Override
    public void insert(String TABLE_NAME, FaceBean faceBean, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        String uuid = UUID.randomUUID().toString();
        cv.put("id", uuid);
        cv.put("name", faceBean.getName());
        cv.put("ratioLeftEyesAndNose", faceBean.getRatioLeftEyesAndNose().toString());
        cv.put("ratioRightEyesAndNose", faceBean.getRatioRightEyesAndNose().toString());
        cv.put("ratioLeftEyesBottomMouth", faceBean.getRatioLeftEyesBottomMouth().toString());
        cv.put("ratioRightEyesBottomMouth", faceBean.getRatioRightEyesBottomMouth().toString());
        cv.put("ratioEyesAndNoseMouth", faceBean.getRatioEyesAndNoseMouth().toString());
        cv.put("leftEyeX", faceBean.getLeftEyeX());
        cv.put("leftEyeY", faceBean.getLeftEyeX());
        cv.put("rightEyeX", faceBean.getRightEyeX());
        cv.put("rightEyeY", faceBean.getRightEyeY());
        cv.put("noseX", faceBean.getNoseX());
        cv.put("noseY", faceBean.getNoseY());
        cv.put("leftMouthX", faceBean.getLeftMouthX());
        cv.put("leftMouthY", faceBean.getLeftMouthX());
        cv.put("rightMouthX", faceBean.getRightMouthX());
        cv.put("rightMouthY", faceBean.getRightMouthY());
        cv.put("bottomMouthX", faceBean.getBottomMouthX());
        cv.put("bottomMouthY", faceBean.getBottomMouthY());
        long row = db.insert(TABLE_NAME, null, cv);
        return;
    }

    @Override
    public void update(String TABLE_NAME, FaceBean faceBean, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        String where = "name=?";
        String[] whereValue = {faceBean.getName()};
        cv.put("name", faceBean.getName());
        db.update(TABLE_NAME, cv, where, whereValue);
    }

    @Override
    public ArrayList<FaceBean> selectAll(String TABLE_NAME, SQLiteDatabase db) {
        ArrayList<FaceBean> faceBeanList = new ArrayList<FaceBean>();

        String state = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(state,null);
        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            FaceBean faceBean = new FaceBean();
            faceBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
            faceBean.setName(cursor.getString(cursor.getColumnIndex("name")));
            faceBean.setRatioEyesAndNoseMouth(new BigDecimal(cursor.getString(cursor.getColumnIndex("ratioEyesAndNoseMouth"))));
            faceBean.setRatioLeftEyesAndNose(new BigDecimal(cursor.getString(cursor.getColumnIndex("ratioLeftEyesAndNose"))));
            faceBean.setRatioRightEyesAndNose(new BigDecimal(cursor.getString(cursor.getColumnIndex("ratioRightEyesAndNose"))));
            faceBean.setRatioLeftEyesBottomMouth(new BigDecimal(cursor.getString(cursor.getColumnIndex("ratioLeftEyesBottomMouth"))));
            faceBean.setRatioRightEyesBottomMouth(new BigDecimal(cursor.getString(cursor.getColumnIndex("ratioRightEyesBottomMouth"))));
            faceBean.setLeftEyeY(cursor.getFloat(cursor.getColumnIndex("leftEyeX")));
            faceBean.setLeftEyeY(cursor.getFloat(cursor.getColumnIndex("leftEyeY")));
            faceBean.setRightEyeX(cursor.getFloat(cursor.getColumnIndex("rightEyeX")));
            faceBean.setRightEyeY(cursor.getFloat(cursor.getColumnIndex("rightEyeY")));
            faceBean.setNoseX(cursor.getFloat(cursor.getColumnIndex("noseX")));
            faceBean.setNoseY(cursor.getFloat(cursor.getColumnIndex("noseY")));
            faceBean.setBottomMouthX(cursor.getFloat(cursor.getColumnIndex("bottomMouthX")));
            faceBean.setBottomMouthY(cursor.getFloat(cursor.getColumnIndex("bottomMouthY")));
            faceBean.setLeftMouthX(cursor.getFloat(cursor.getColumnIndex("leftMouthX")));
            faceBean.setLeftMouthY(cursor.getFloat(cursor.getColumnIndex("leftMouthY")));
            faceBean.setRightMouthX(cursor.getFloat(cursor.getColumnIndex("rightMouthX")));
            faceBean.setRightMouthY(cursor.getFloat(cursor.getColumnIndex("rightMouthY")));
            faceBeanList.add(faceBean);
            cursor.moveToNext();
        }
        return faceBeanList;
    }
}
