package kr.hs.emirim.lyn.failydaily;

import android.provider.BaseColumns;

import org.w3c.dom.Text;

public final class CreateDB {

    public static final class DataBases implements BaseColumns{
        public static final String title = "title";
        public static final String content = "content";
        public static final String category = "category";
        public static final String date = "date";
        public static final String name = "name";
        public static final String age = "age";
        public static final String num = "num";

        public static final String _TABLENAME1 = "diaryDB";
        public static final String _TABLENAME2 = "FairyDB";
        public static final String _TABLENAME3 = "CategoryDB";

        public static final String _CREATE = "create table if not exists "+_TABLENAME1+"(" //일기장 DB
                +_ID+" integer primary key autoincrement, " //일기장의 아이디
                +title+" text not null , " //일기장 제목
                +content+" text not null , " //일기장 내용
                +category+" text not null , " //일기장 카테고리
                +date+" text not null)"; //일기장 날짜

        public static final String _CREATE2 = "create table if not exists "+_TABLENAME2+"(" //요정 DB
                +_ID+" integer primary key autoincrement, " //요정 아이디
                +name+" text not null , " //요정이름
                +age+" integer not null , " //요정 나이 (카테고리 당 일기 수)
                +category+" text not null , " //해당 요정의 카테고리
                +num+" integer not null)"; //해당 요정의 번호 (1, 2, 3, 4번 요정)

        public static final String _CREATE3 = "create table if not exists " + _TABLENAME3 + "(" //카테고리 DB
                +name+" text not null primary key)"; //카테고리명

    }
}