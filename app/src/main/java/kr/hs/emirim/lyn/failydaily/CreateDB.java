package kr.hs.emirim.lyn.failydaily;

import android.provider.BaseColumns;

import org.w3c.dom.Text;

public final class CreateDB {

    public static final class DataBase implements BaseColumns{
        public static final String title = "title";
        public static final String subtitle = "subtitle";
        public static final String content = "content";
        public static final String category = "category";
        public static final String date = "date";
        public static final String _TABLENAME = "failydaily";
        public static final String _CREATE = "create table if not exists "+_TABLENAME+"("
                +_ID+" integer primary key autoincrement, "
                +title+" text not null , "
                +subtitle+" text not null , "
                +content+" text not null , "
                +category+" text not null , "
                +date+" text not null DEFAULT (datetime('now', 'localtime'))";
    }
}