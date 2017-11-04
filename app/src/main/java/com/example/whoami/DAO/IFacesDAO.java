package com.example.whoami.DAO;

import android.database.sqlite.SQLiteDatabase;

import com.example.whoami.commonBean.FaceBean;

import java.util.ArrayList;


/**
 * Created by dunhaozhong on 10/11/17.
 */

public interface IFacesDAO {

    public long insert(String TABLE_NAME, FaceBean faceBean, SQLiteDatabase db);

    public void update(String TABLE_NAME, FaceBean faceBean, SQLiteDatabase db);

    public ArrayList<FaceBean> selectAll(String TABLE_NAME, SQLiteDatabase db);
}
