package kr.hs.emirim.lyn.failydaily;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    UseDB user;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar tb;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<CategoryInfo> categoryInfoArrayList = new ArrayList<>();
    MyAdapter myAdapter = new MyAdapter(categoryInfoArrayList);       // -> list는 데이터베이스에 있는 리스트 넣으면 될듯

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        initLayout();

        user = new UseDB(this);
        user.open();
    }

    private void initLayout() {
        tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        categoryInfoArrayList.add(new CategoryInfo("일기1"));
        categoryInfoArrayList.add(new CategoryInfo("일기2"));
        mRecyclerView.setAdapter(myAdapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.dl_main_drawer_root);
        navigationView = (NavigationView) findViewById(R.id.nv_main_navigation_root);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                tb,
                R.string.drawer_open,
                R.string.drawer_close
        );
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case R.id.action_graph :
                intent = new Intent(this, CtGraphActivity.class);
                startActivity(intent);
                break;
            case R.id.action_category :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.action_myFairy :
                intent = new Intent(this, FairyListActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnClickHandler(View view) {
        //TODO 요정(+버튼)을 눌렀을 때 일기화면에서 다시 돌아와서 추가되어 있어야 함.
//        View dialogView = getLayoutInflater().inflate(R.layout.activity_sub, null);
//        final EditText nameEditText = (EditText)dialogView.findViewById(R.id.name);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(dialogView);
//
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String name = "카테고리 이름은 : " + nameEditText.getText().toString();
//
//                user.makeCategory(nameEditText.getText().toString());
//                categoryInfoArrayList.add(new CategoryInfo(nameEditText.getText().toString()));
//                mRecyclerView.setAdapter(myAdapter);
//
//                Toast.makeText(getApplicationContext(), nameEditText.getText().toString() + " 카테고리가 생성되었습니다.", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}
