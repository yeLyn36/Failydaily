package kr.hs.emirim.lyn.failydaily;

import android.provider.BaseColumns;

import org.w3c.dom.Text;

public final class CreateDB {

    public static final class DataBases implements BaseColumns{
        public static final String title = "title";
        public static final String subtitle = "subtitle";
        public static final String content = "content";
        public static final String category = "category";
        public static final String date = "date";
        public static final String name = "name";
        public static final String age = "age";
        public static final String num = "num";

        public static final String _TABLENAME1 = "diaryDB";
        public static final String _TABLENAME2 = "FairyDB";
        public static final String _TABLENAME3 = "CategoryDB";

        public static final String _CREATE = "create table if not exists "+_TABLENAME1+"("
                +_ID+" integer primary key autoincrement, "
                +title+" text not null , "
                +subtitle+" text not null , "
                +content+" text not null , "
                +category+" text not null , "
                +date+" text not null DEFAULT (datetime('now', 'localtime')))";

        public static final String _CREATE2 = "create table if not exists "+_TABLENAME2+"("
                +_ID+" integer primary key autoincrement, "
                +name+" text not null , "
                +age+" integer not null , "
                +category+" text not null , "
                +num+" integer not null)";

        public static final String _CREATE3 = "create table if not exists " + _TABLENAME3 + "("
                +name+" text not null primary key)";
    }
}