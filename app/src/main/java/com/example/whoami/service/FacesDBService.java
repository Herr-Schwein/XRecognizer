package com.example.whoami.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.whoami.DAO.FacesDAOImpl;
import com.example.whoami.DAO.IFacesDAO;
import com.example.whoami.commonBean.Faces;

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
        String state = "CREATE TABLE " + TABLE_NAME + " (id, name, ratioLeftEyesAndNose, ratioRightEyesAndNose, " +
                "ratioLeftEyesBottomMouth, ratioRightEyesBottomMouth, ratioEyesAndNoseMouth, leftEyeX, leftEyeY, " +
                "rightEyeX, rightEyeY, noseX, noseY, leftMouthX, leftMouthY, rightMouthX, " +
                "rightMouthY, bottomMouthX, bottomMouthY, PRIMARY KEY(id, name))";
        System.out.println(state);
        sqLiteDatabase.execSQL(state);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO nothing
    }

    public ArrayList<Faces> selectAll(){
        db = this.getReadableDatabase();
        ArrayList<Faces> facesRecords = iFacesDAO.selectAll(TABLE_NAME, db);
        return facesRecords;
    }

    public void insert(Faces faces){
        db = this.getReadableDatabase();
        db = this.getWritableDatabase();
        iFacesDAO.insert(TABLE_NAME, faces, db);
    }

    public void update(Faces faces){
        db = this.getReadableDatabase();
        db = this.getWritableDatabase();
        iFacesDAO.update(TABLE_NAME, faces, db);
    }
}
