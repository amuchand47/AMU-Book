package com.herokuapp.amulibraray.amulibrary.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.herokuapp.amulibraray.amulibrary.Model.HomeScreen;
import com.herokuapp.amulibraray.amulibrary.Notice.NoticeActivity;
import com.herokuapp.amulibraray.amulibrary.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context ctx;
    private List<HomeScreen> homeScreens;


    public HomeAdapter(Context ctx, List<HomeScreen> homeScreens) {
        this.ctx = ctx;
        this.homeScreens = homeScreens;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.home_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      HomeScreen homeScreen = homeScreens.get(position);
      holder.title.setText(homeScreen.getName());
      Picasso.get().load(homeScreen.getImage()).placeholder(R.drawable.notice).resize(280,300).into(holder.img);
      holder.img.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             ctx.startActivity(new Intent(ctx, NoticeActivity.class));
          }
      });
    }

    @Override
    public int getItemCount() {
        return homeScreens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.imageView);
        }
    }

}
