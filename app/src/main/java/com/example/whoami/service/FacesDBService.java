package com.example.whoami.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.whoami.DAO.FacesDAOImpl;
import com.example.whoami.DAO.IFacesDAO;
import com.example.whoami.commonBean.FaceBean;

import java.util.ArrayList;


/**
 * Created by dunhaozhong on 10/11/17.
 */

public class FacesDBService extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "FacialRecognize.db";
    private final static String TABLE_NAME = "FACES";
    private final static int DATABASE_VERSION = 1;
    private SQLiteDatabase db = null;

    IFacesDAO iFacesDAO = new FacesDAOImpl();

    public FacesDBService(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String state = "CREATE TABLE " + TABLE_NAME + " (id, name, leftEyeX, leftEyeY, " +
                "rightEyeX, rightEyeY, noseX, noseY, leftMouthX, leftMouthY, rightMouthX, " +
                "rightMouthY, bottomMouthX, bottomMouthY, leftEarTipX, leftEarTipY, " +
                "rightEarTipX, rightEarTipY, leftCheekTipX, leftCheekTipY, rightCheekTipX, rightCheekTipY, " +
                "PRIMARY KEY(id, name))";
        System.out.println(state);
        sqLiteDatabase.execSQL(state);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO nothing
    }

    public ArrayList<FaceBean> selectAll(){
        db = this.getReadableDatabase();
        ArrayList<FaceBean> faceBeanRecords = iFacesDAO.selectAll(TABLE_NAME, db);
        db.close();
        return faceBeanRecords;
    }

    public void insert(FaceBean faceBean){
        db = this.getReadableDatabase();
        db = this.getWritableDatabase();
        iFacesDAO.insert(TABLE_NAME, faceBean, db);
        db.close();
    }

    public void update(FaceBean faceBean){
        db = this.getReadableDatabase();
        db = this.getWritableDatabase();
        iFacesDAO.update(TABLE_NAME, faceBean, db);
        db.close();
    }

    public void delete(String name){
        db = this.getReadableDatabase();
        db = this.getWritableDatabase();
        iFacesDAO.delete(TABLE_NAME, name, db);
        db.close();
    }
}
