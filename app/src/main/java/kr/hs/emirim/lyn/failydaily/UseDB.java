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
}
