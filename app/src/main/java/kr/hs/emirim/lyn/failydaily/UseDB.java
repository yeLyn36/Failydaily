package kr.hs.emirim.lyn.failydaily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.nio.file.Path;

import static android.content.Context.MODE_PRIVATE;

public class UseDB {

    private static final String DATABASE_NAME = "failydaily.db"; //DB명
    private static final int DATABASE_VERSION = 1; //버전
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(CreateDB.DataBases._CREATE);
            db.execSQL(CreateDB.DataBases._CREATE2);
            db.execSQL(CreateDB.DataBases._CREATE3);
            System.out.println("Create DB");
        } //TABLE 생성

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+CreateDB.DataBases._TABLENAME1);
            db.execSQL("DROP TABLE IF EXISTS "+CreateDB.DataBases._TABLENAME2);
            db.execSQL("DROP TABLE IF EXISTS "+CreateDB.DataBases._TABLENAME3);
            onCreate(db);
        } //DB 업그레이드
    }

    public UseDB(Context context){
        this.mCtx = context;
    }

    public UseDB open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    } //DB 생성

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }


    //Category Method
    //Insert Category
    public void makeCategory(String name){
        String sql = "insert or replace into " + CreateDB.DataBases._TABLENAME3 + "(name) values (\"" + name + "\")";
        mDB.execSQL(sql);
        System.out.println(name);
    }
    // Delete Category
    public boolean deleteCategory(String name){
        // deleteFairy(String category);
        return mDB.delete(CreateDB.DataBases._TABLENAME3, "name="+name, null) > 0;
    }
    // Select Category
    public Cursor selectCategories(){
        return mDB.query(CreateDB.DataBases._TABLENAME3, null, null, null, null, null, null);
    }


    //DiaryList Method
    //select DiaryList
    public Cursor selectDiaryList(){
        return mDB.query(CreateDB.DataBases._TABLENAME1, null, null, null, null, null, null);
    }

    //Diary Method
    //select Diary


    //Insert Diary
    public void makeDiary(long id, String title, String subtitle, String content, String category, String date){
        String sql = "insert or replace into " + CreateDB.DataBases._TABLENAME1 + "(id, title, subtitle, content, category, date) values (\"" + id + "\", \"" + title + "\", \"" + subtitle + "\", \"" + content + "\", \"" + category + "\", \"" + date + "\")";
        mDB.execSQL(sql);
        System.out.println(id);
    }

    //Update Diary
    public void updateDiary(long id, String title, String subtitle, String content , String category, String datetime){
        String sql = "update " + CreateDB.DataBases._TABLENAME1 + "set id = \"" + id + "\", " +
                "title = \"" + title + "\", " + "subtitle = \"" + subtitle + "\", " + "content = \"" + content + "\", " +
                "category = \"" + category + "\", " + "date = \"" + datetime + "\";";

        mDB.execSQL(sql);
        System.out.println(true);
    }

    //Delete Diary
    public boolean deleteDiary(long id){
        return mDB.delete(CreateDB.DataBases._TABLENAME1, "_ID="+id, null) > 0;
    }

    //Fairy Method
    //Insert Fairy
    public void makeFairy(long id, String name, int age, String category, int num) {
        String sql = "insert or replace into " + CreateDB.DataBases._TABLENAME2 + "(id, name, age, category, num) values (\"" + id + "\", \"" + name + "\", \"" + age + "\", \"" + category + "\", \"" + num + "\")";
        mDB.execSQL(sql);
        System.out.println(id);
    }

    //Delete Fairy
    public boolean deleteFairy(String category){
        return mDB.delete(CreateDB.DataBases._TABLENAME2, "category="+category, null) > 0;
    }

    //FariyList Method
    //Select FairyList
    public Cursor selectFairyList(){
        return mDB.query(CreateDB.DataBases._TABLENAME2, null, null, null, null, null, null);
    }

    //Graph Method
    //Show graphOfCategory
    //select category()
    //리스트에 카데고리 별로 나누기
    //리턴 리스트

    //Show graphOfYear
    //select category
    //리스트에 날짜별로 나누기
    //리턴 리스트

}
