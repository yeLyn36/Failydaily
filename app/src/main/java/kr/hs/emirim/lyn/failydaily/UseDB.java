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
            db.execSQL(CreateDB.DataBases._CREATE3);
            System.out.println("Create DB");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+CreateDB.DataBases._TABLENAME1);
            db.execSQL("DROP TABLE IF EXISTS "+CreateDB.DataBases._TABLENAME2);
            db.execSQL("DROP TABLE IF EXISTS "+CreateDB.DataBases._TABLENAME3);
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

    public void makeCategory(){

    }

    //Category Method
    //Insert Category
    public long makeCategory(String name){
        ContentValues values = new ContentValues();
        values.put(CreateDB.DataBases.name, name);
        return mDB.insert(CreateDB.DataBases._TABLENAME3, null, values);
    }
    // Delete Category
    public boolean deleteCategory(String name){
        // deleteFairy(String category);
        return mDB.delete(CreateDB.DataBases._TABLENAME3, "name="+name, null) > 0;
    }
    // Select Category
    public Cursor selectCategory(){
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

    //Update Diary
    public boolean updateColumn(long id, String title, String subtitle, String content , String category, String datetime){
        ContentValues values = new ContentValues();
        values.put(CreateDB.DataBases._ID, id);
        values.put(CreateDB.DataBases.title, title);
        values.put(CreateDB.DataBases.subtitle, subtitle);
        values.put(CreateDB.DataBases.content, content);
        values.put(CreateDB.DataBases.category, category);
        values.put(CreateDB.DataBases.date, datetime);
        return mDB.update(CreateDB.DataBases._TABLENAME1, values, "_ID=" + id, null) > 0;
    }

    //Delete Diary


    //Fairy Method
    //Insert Fairy

    //Delete Fairy


    //FariyList Method
    //Select FairyList


    //Graph Method
    //Show graphOfCategory

    //Show graphOfYear

}
