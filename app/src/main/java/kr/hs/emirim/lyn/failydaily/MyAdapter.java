package kr.hs.emirim.lyn.failydaily;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ctName;

        MyViewHolder(View view) {
            super(view);
            ctName = view.findViewById(R.id.ct_name);
        }
    }

    private ArrayList<CategoryInfo> categoryInfoArrayList;
    MyAdapter(ArrayList<CategoryInfo> categoryInfoArrayList) {
        this.categoryInfoArrayList = categoryInfoArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // RecyclerView 의 행을 표시하는데 사용되는 레이아웃 xml 가져옴.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // RecyclerView 에서 보여질 텍스트 설정
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.ctName.setText(categoryInfoArrayList.get(position).name);
    }

    @Override
    public int getItemCount() {
        // RecyclerView 의 행 개수 리턴
        return categoryInfoArrayList.size();
    }
}
