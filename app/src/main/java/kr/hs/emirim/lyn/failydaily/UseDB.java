package kr.hs.emirim.lyn.failydaily;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class UseDB {

    private static final String DATABASE_NAME = "failydaily.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;
    Context ctx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(CreateDB.DataBases._CREATE);
            db.execSQL(CreateDB.DataBases._CREATE2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+CreateDB.DataBases._TABLENAME1);
            db.execSQL("DROP TABLE IF EXISTS "+CreateDB.DataBases._TABLENAME2);
            onCreate(db);
        }
    }

    public UseDB(Context context){
        this.mCtx = context;
    }

    public UseDB open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("DB open");
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

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
    public void deleteCategory(String name){
        // deleteFairy(String category);
        mDB.delete(CreateDB.DataBases._TABLENAME3, "name="+name, null);
    }
    // Select Category
    public ArrayList<CategoryInfo> selectCategories(){
        ArrayList<CategoryInfo> categories = new ArrayList<>();
        String sql = "select * from " + CreateDB.DataBases._TABLENAME3 ;
        Cursor cursor = mDB.rawQuery(sql, null);
        while(cursor.moveToNext()){
            categories.add(new CategoryInfo(cursor.getString(0)));
        }
        return categories;
    }


    //DiaryList Method
    //select DiaryList
    public ArrayList<String> selectDiaryList(){
        ArrayList<String> dairyList = new ArrayList<>();
        String sql = "select title from " + CreateDB.DataBases._TABLENAME1;
        Cursor cursor = mDB.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int i = 0;
            dairyList.add(cursor.getString(i++));
        }
        return dairyList;
    }

    //Diary Method
    //select Diary
    public String[] selectDiary(long id){
        String[] Diary = new String[6];
        String sql = "select * from diaryDB where id = \"" + id + "\"";
        String strId = String.valueOf(id);
        Cursor cursor = mDB.rawQuery(sql, null);
        if(cursor.moveToFirst()) {
            String ID = cursor.getString(0);
            if (ID.equals(String.valueOf(id))) {
                for (int i = 1; i < 6; i++) {
                    Diary[i] = cursor.getString(i);
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
        return Diary;
    }
    //Insert Diary
    public void makeDiary(String title, String content, String category, String date){
        String sql = "insert or replace into " + CreateDB.DataBases._TABLENAME1 + "(title, content, category, date) values (\"" + title + "\", \"" + content + "\", \"" + category + "\", \"" + date + "\")";
        mDB.execSQL(sql);
        System.out.println(title);
    }
    //Update Diary
    public void updateDiary(long id, String title, String content , String category, String datetime){
        String sql = "update " + CreateDB.DataBases._TABLENAME1 + "set id = \"" + id + "\", " +
                "title = \"" + title + "\", " + "content = \"" + content + "\", " +
                "category = \"" + category + "\", " + "date = \"" + datetime + "\";";

        mDB.execSQL(sql);
        System.out.println(true);
    }
    //Delete Diary
    public void deleteDiary(long id){
        mDB.delete(CreateDB.DataBases._TABLENAME1, "_ID="+id, null);
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
    public ArrayList<String[]> selectFairyList(){
        ArrayList<String[]> fairyList = new ArrayList<>();
        String[] fairyArray = new String[3];
        String sql = "select name, age, category from " + CreateDB.DataBases._TABLENAME2;
        Cursor cursor = mDB.rawQuery(sql, null);
        while (cursor.moveToNext()){
            fairyArray[0] = cursor.getString(0);
            fairyArray[1] = cursor.getString(1);
            fairyArray[2] = cursor.getString(2);
            fairyList.add(fairyArray);
        }
        return fairyList;
    } //fairyList안에 문자열 배열로 요정 이름, 나이, 카테고리 넣어놓음

    //Graph Method
    //Show graphOfCategory
    public ArrayList<String[]> graphOfCategory() {
        //select category()
        ArrayList<String[]> graph = new ArrayList<>();
        String[] categoryCount = new String[2];
        String sql = "select name, count(*) as count from CategoryDB";
        Cursor cursor = mDB.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            categoryCount[0] = cursor.getString(0);
            categoryCount[1] = String.valueOf(cursor.getInt(1));
            graph.add(categoryCount);
        }
        return graph;
    }
}
