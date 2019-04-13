package com.example.shakil.androidrvgoogleplay.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shakil.androidrvgoogleplay.Model.ItemData;
import com.example.shakil.androidrvgoogleplay.Model.ItemGroup;
import com.example.shakil.androidrvgoogleplay.R;

import java.util.List;

public class MyItemGroupAdapter extends RecyclerView.Adapter<MyItemGroupAdapter.MyViewHolder> {

    private Context context;
    private List<ItemGroup> dataList;

    public MyItemGroupAdapter(Context context, List<ItemGroup> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_group, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.item_title.setText(dataList.get(i).getHeaderTitle());

        List<ItemData> itemData = dataList.get(i).getListItem();

        MyItemAdapter itemListAdapter = new MyItemAdapter(context, itemData);
        myViewHolder.recycler_view_item_list.setHasFixedSize(true);
        myViewHolder.recycler_view_item_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        myViewHolder.recycler_view_item_list.setAdapter(itemListAdapter);

        myViewHolder.recycler_view_item_list.setNestedScrollingEnabled(false); //Important

        //Button more
        myViewHolder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button more : " + myViewHolder.item_title.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null ? dataList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_title;
        RecyclerView recycler_view_item_list;
        Button btn_more;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_title = itemView.findViewById(R.id.itemTitle);
            btn_more = itemView.findViewById(R.id.btnMore);
            recycler_view_item_list = itemView.findViewById(R.id.recycler_view_list);
        }
    }
}
