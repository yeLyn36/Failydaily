package kr.hs.emirim.lyn.failydaily;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DairyActivity extends AppCompatActivity implements View.OnClickListener{

    EditText title = (EditText) findViewById(R.id.title);
    EditText content = (EditText) findViewById(R.id.content);
    Button btSave = (Button)findViewById(R.id.btSave);
    Button btDelete = (Button)findViewById(R.id.btDelete);

    UseDB usedb;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy);

        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                // 23 버전 이상일 때 상태바 #efb951 색상에 회색 아이콘 색상을 설정
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#efb951"));
            }
        } else if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getWindow().setStatusBarColor(Color.parseColor("#efb951"));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btSave: {
                usedb.makeDiary();
            }
            case R.id.btDelete: {
                usedb.deleteDiary();
            }
        }
    }
}
