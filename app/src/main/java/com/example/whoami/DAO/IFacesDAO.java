/*
Depeloper: DUNHAO ZHONG
DATE:2017 Fall Term, Multimedia Communication
 */
package com.example.whoami.DAO;

import android.database.sqlite.SQLiteDatabase;

import com.example.whoami.commonBean.FaceBean;

import java.util.ArrayList;


/**
 * Created by dunhaozhong on 10/11/17.
 */

public interface IFacesDAO {

    long insert(String TABLE_NAME, FaceBean faceBean, SQLiteDatabase db);

    void update(String TABLE_NAME, FaceBean faceBean, SQLiteDatabase db);

    void delete( String TABLE_NAME, String name, SQLiteDatabase db );

    ArrayList<FaceBean> selectAll(String TABLE_NAME, SQLiteDatabase db);
}
