package com.trodev.mypasswordgenerator.activity.browser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trodev.mypasswordgenerator.R;

import java.util.ArrayList;

public class BrowserOnlineAdapter extends RecyclerView.Adapter<BrowserOnlineAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<BrowserOnlineModel> list;
    private String category;

    public BrowserOnlineAdapter(Context context, ArrayList<BrowserOnlineModel> list, String category) {
        this.context = context;
        this.list = list;
        this.category = category;
    }

    @NonNull
    @Override
    public BrowserOnlineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.browser_online_pass_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrowserOnlineAdapter.MyViewHolder holder, int position) {
        BrowserOnlineModel model = list.get(position);
        holder.web_name_tv.setText("Website: "+model.getWeb_name());
        holder.web_url_tv.setText("https://www."+model.getWeb_url());
        holder.web_username_tv.setText("Username or Email: "+model.getWeb_uname());
        holder.web_pass_tv.setText("Password: "+model.getWeb_password());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "This website name is "+model.getWeb_name(), Toast.LENGTH_SHORT).show();
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
