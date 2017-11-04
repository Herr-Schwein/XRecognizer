package com.example.whoami.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.whoami.commonBean.FaceBean;

import java.util.ArrayList;
import java.util.UUID;


/**
 * Created by dunhaozhong on 10/11/17.
 */

public class FacesDAOImpl implements IFacesDAO {

    @Override
    public long insert(String TABLE_NAME, FaceBean faceBean, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        String uuid = UUID.randomUUID().toString();
        cv.put("id", uuid);
        cv.put("name", faceBean.getName());
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
        cv.put("leftEarTipX", faceBean.getLeftEarTipX());
        cv.put("leftEarTipY", faceBean.getLeftEarTipY());
        cv.put("rightEarTipX", faceBean.getRightEarTipX());
        cv.put("rightEarTipY", faceBean.getRightEarTipY());
        cv.put("leftCheekTipX", faceBean.getLeftCheekTipX());
        cv.put("leftCheekTipY", faceBean.getLeftCheekTipY());
        cv.put("rightCheekTipX", faceBean.getRightCheekTipX());
        cv.put("rightCheekTipY", faceBean.getRightCheekTipY());
        long row = db.insert(TABLE_NAME, null, cv);
        return row;
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
            faceBean.setLeftEyeY(cursor.getFloat(cursor.getColumnIndex("leftEyeX")));
            faceBean.setLeftEyeY(cursor.getFloat(cursor.getColumnIndex("leftEyeY")));
            faceBean.setRightEyeX(cursor.getFloat(cursor.getColumnIndex("rightEyeX")));
            faceBean.setRightEyeY(cursor.getFloat(cursor.getColumnIndex("rightEyeY")));
            faceBean.setNoseX(cursor.getFloat(cursor.getColumnIndex("noseX")));
            faceBean.setNoseY(cursor.getFloat(cursor.getColumnIndex("noseY")));
            faceBean.setLeftMouthX(cursor.getFloat(cursor.getColumnIndex("leftMouthX")));
            faceBean.setLeftMouthY(cursor.getFloat(cursor.getColumnIndex("leftMouthY")));
            faceBean.setRightMouthX(cursor.getFloat(cursor.getColumnIndex("rightMouthX")));
            faceBean.setRightMouthY(cursor.getFloat(cursor.getColumnIndex("rightMouthY")));
            faceBean.setBottomMouthX(cursor.getFloat(cursor.getColumnIndex("bottomMouthX")));
            faceBean.setBottomMouthY(cursor.getFloat(cursor.getColumnIndex("bottomMouthY")));
            faceBean.setLeftEarTipX(cursor.getFloat(cursor.getColumnIndex("leftEarTipX")));
            faceBean.setLeftEarTipY(cursor.getFloat(cursor.getColumnIndex("leftEarTipY")));
            faceBean.setRightEarTipX(cursor.getFloat(cursor.getColumnIndex("rightEarTipX")));
            faceBean.setRightEarTipY(cursor.getFloat(cursor.getColumnIndex("rightEarTipY")));
            faceBean.setLeftCheekTipX(cursor.getFloat(cursor.getColumnIndex("leftCheekTipX")));
            faceBean.setLeftCheekTipY(cursor.getFloat(cursor.getColumnIndex("leftCheekTipY")));
            faceBean.setRightCheekTipX(cursor.getFloat(cursor.getColumnIndex("rightCheekTipX")));
            faceBean.setRightCheekTipY(cursor.getFloat(cursor.getColumnIndex("rightCheekTipY")));
            faceBeanList.add(faceBean);
            cursor.moveToNext();
        }
        return faceBeanList;
    }
}
