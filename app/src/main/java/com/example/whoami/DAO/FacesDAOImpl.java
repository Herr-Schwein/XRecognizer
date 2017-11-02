package com.example.whoami.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.whoami.commonBean.Faces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;


/**
 * Created by dunhaozhong on 10/11/17.
 */

public class FacesDAOImpl implements IFacesDAO {

    @Override
    public void insert(String TABLE_NAME, Faces faces, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        String uuid = UUID.randomUUID().toString();
        cv.put("id", uuid);
        cv.put("name", faces.getName());
        cv.put("ratioLeftEyesAndNose", faces.getRatioLeftEyesAndNose().toString());
        cv.put("ratioRightEyesAndNose", faces.getRatioRightEyesAndNose().toString());
        cv.put("ratioLeftEyesBottomMouth", faces.getRatioLeftEyesBottomMouth().toString());
        cv.put("ratioRightEyesBottomMouth", faces.getRatioRightEyesBottomMouth().toString());
        cv.put("ratioEyesAndNoseMouth", faces.getRatioEyesAndNoseMouth().toString());
        cv.put("leftEyeX", faces.getLeftEyeX());
        cv.put("leftEyeY", faces.getLeftEyeX());
        cv.put("rightEyeX", faces.getRightEyeX());
        cv.put("rightEyeY", faces.getRightEyeY());
        cv.put("noseX", faces.getNoseX());
        cv.put("noseY", faces.getNoseY());
        cv.put("leftMouthX", faces.getLeftMouthX());
        cv.put("leftMouthY", faces.getLeftMouthX());
        cv.put("rightMouthX", faces.getRightMouthX());
        cv.put("rightMouthY", faces.getRightMouthY());
        cv.put("bottomMouthX", faces.getBottomMouthX());
        cv.put("bottomMouthY", faces.getBottomMouthY());
        long row = db.insert(TABLE_NAME, null, cv);
        return;
    }

    @Override
    public void update(String TABLE_NAME, Faces faces, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        String where = "name=?";
        String[] whereValue = {faces.getName()};
        cv.put("name", faces.getName());
        db.update(TABLE_NAME, cv, where, whereValue);
    }

    @Override
    public ArrayList<Faces> selectAll(String TABLE_NAME, SQLiteDatabase db) {
        ArrayList<Faces> facesList = new ArrayList<Faces>();

        String state = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(state,null);
        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            Faces faces = new Faces();
            faces.setId(cursor.getInt(cursor.getColumnIndex("id")));
            faces.setName(cursor.getString(cursor.getColumnIndex("name")));
            faces.setRatioEyesAndNoseMouth(new BigDecimal(cursor.getString(cursor.getColumnIndex("ratioEyesAndNoseMouth"))));
            faces.setRatioLeftEyesAndNose(new BigDecimal(cursor.getString(cursor.getColumnIndex("ratioLeftEyesAndNose"))));
            faces.setRatioRightEyesAndNose(new BigDecimal(cursor.getString(cursor.getColumnIndex("ratioRightEyesAndNose"))));
            faces.setRatioLeftEyesBottomMouth(new BigDecimal(cursor.getString(cursor.getColumnIndex("ratioLeftEyesBottomMouth"))));
            faces.setRatioRightEyesBottomMouth(new BigDecimal(cursor.getString(cursor.getColumnIndex("ratioRightEyesBottomMouth"))));
            faces.setLeftEyeY(cursor.getFloat(cursor.getColumnIndex("leftEyeX")));
            faces.setLeftEyeY(cursor.getFloat(cursor.getColumnIndex("leftEyeY")));
            faces.setRightEyeX(cursor.getFloat(cursor.getColumnIndex("rightEyeX")));
            faces.setRightEyeY(cursor.getFloat(cursor.getColumnIndex("rightEyeY")));
            faces.setNoseX(cursor.getFloat(cursor.getColumnIndex("noseX")));
            faces.setNoseY(cursor.getFloat(cursor.getColumnIndex("noseY")));
            faces.setBottomMouthX(cursor.getFloat(cursor.getColumnIndex("bottomMouthX")));
            faces.setBottomMouthY(cursor.getFloat(cursor.getColumnIndex("bottomMouthY")));
            faces.setLeftMouthX(cursor.getFloat(cursor.getColumnIndex("leftMouthX")));
            faces.setLeftMouthY(cursor.getFloat(cursor.getColumnIndex("leftMouthY")));
            faces.setRightMouthX(cursor.getFloat(cursor.getColumnIndex("rightMouthX")));
            faces.setRightMouthY(cursor.getFloat(cursor.getColumnIndex("rightMouthY")));
            facesList.add(faces);
            cursor.moveToNext();
        }
        return facesList;
    }
}
