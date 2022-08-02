package com.example.demoapp.tabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.RoomDatabase.Artical;
import com.example.demoapp.RoomDatabase.ArticalDataBase;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<Artical> postModels;
    ArticalDataBase articalDataBase;
    boolean flag;

    public RecyclerViewAdapter(Context context, List<Artical> postModels, boolean flag) {
        this.context = context;
        this.postModels = postModels;
        this.flag = flag;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvUserId.setText("User ID : "+postModels.get(position).getUserId());
        holder.tvId.setText("ID : "+postModels.get(position).getId());
        holder.tvTitle.setText("Title : "+postModels.get(position).getTitle());
        holder.tvBody.setText("Body : "+postModels.get(position).getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag) {
                    articalDataBase = ArticalDataBase.getInstance(context);
                    articalDataBase.articalDao().updateArticals("2", postModels.get(position).getId());
                    Toast.makeText(context, "Added in Favourite", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Already added in Favourite", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    public void setArticals(){
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUserId, tvId, tvTitle, tvBody;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvUserId = (TextView) itemView.findViewById(R.id.tvUserId);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
        }
    }
}
