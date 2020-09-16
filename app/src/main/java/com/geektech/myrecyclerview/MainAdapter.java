package com.geektech.myrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    List<Title> list;
    Context context;

    public MainAdapter(List<Title> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Title title = list.get(position);
        holder.textTitle.setText(title.getTitle());
        holder.textDesc.setText(title.getDescription());
        holder.textMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(context,holder.textMenu);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                       switch (item.getItemId()){
                           case R.id.menu_save:
                               Toast.makeText(context,"Saved",Toast.LENGTH_LONG).show();
                               break;

                           case R.id.menu_delete:
                               list.remove(position);
                               notifyDataSetChanged();
                               Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                               break;
                           default:
                               break;
                       }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        TextView textDesc;
        TextView textMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.txtTitle);
            textDesc = itemView.findViewById(R.id.txtDesc);
            textMenu = itemView.findViewById(R.id.txtOptionMenu);


        }
    }
}
