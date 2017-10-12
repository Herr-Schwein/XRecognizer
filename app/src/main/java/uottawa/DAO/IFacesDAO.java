package uottawa.DAO;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uottawa.commonBean.Faces;

/**
 * Created by dunhaozhong on 10/11/17.
 */

public interface IFacesDAO {

    public void insert(String TABLE_NAME, Faces faces, SQLiteDatabase db);

    public void update(String TABLE_NAME, Faces faces, SQLiteDatabase db);

    public ArrayList<Faces> selectAll(String TABLE_NAME, SQLiteDatabase db);
}
