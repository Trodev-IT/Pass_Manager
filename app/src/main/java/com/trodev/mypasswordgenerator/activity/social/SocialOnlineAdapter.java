package com.trodev.mypasswordgenerator.activity.social;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOnlineAdapter;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOnlineModel;

import java.util.ArrayList;

public class SocialOnlineAdapter extends RecyclerView.Adapter<SocialOnlineAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<SocialOnlineModel> list;
    private String category;

    public SocialOnlineAdapter(Context context, ArrayList<SocialOnlineModel> list, String category) {
        this.context = context;
        this.list = list;
        this.category = category;
    }

    @NonNull
    @Override
    public SocialOnlineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.browser_online_pass_item, parent, false);

        return new SocialOnlineAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialOnlineAdapter.MyViewHolder holder, int position) {

        SocialOnlineModel model = list.get(position);
        holder.web_name_tv.setText("Website: "+model.getSoc_name());
        holder.web_url_tv.setText("https://www."+model.getSoc_link());
        holder.web_username_tv.setText("Username or Email: "+model.getSoc_uname());
        holder.web_pass_tv.setText("Password: "+model.getSoc_pass());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Platform name is "+model.getSoc_name(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView web_name_tv, web_url_tv, web_username_tv, web_pass_tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            web_name_tv = itemView.findViewById(R.id.web_name_tv);
            web_url_tv = itemView.findViewById(R.id.web_url_tv);
            web_username_tv = itemView.findViewById(R.id.web_username_tv);
            web_pass_tv = itemView.findViewById(R.id.web_pass_tv);
        }
    }
}
