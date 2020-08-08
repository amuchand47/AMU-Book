package com.herokuapp.amulibraray.amulibrary.Adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.herokuapp.amulibraray.amulibrary.Model.Song;
import com.herokuapp.amulibraray.amulibrary.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  implements Filterable {
    Context ctx;
    List<Song> songs;
    private List <Song> songList;
    private List <Song> fullsonglist;
    DownloadManager downloadManager;

    public Adapter(Context ctx, List<Song> songs){
        this.ctx = ctx;
        this.songList=songs;
        fullsonglist = new ArrayList<>(songList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.custom_notice,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        // bind the data
        Song song = songList.get(position);
        holder.songTitle.setText(song.getTitle());
        final String dtr = song.getImage();
        final String title = song.getTitle();

        // Picasso.get().load(song.getImage()).placeholder(R.drawable.courses).into(holder.imageView);

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String str = dtr;
                String url = "https://www.amu.ac.in/" +str;
                Toast.makeText(ctx, "Downloading the file :"+url, Toast.LENGTH_LONG).show();
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setTitle("Downloads");
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title+".pdf");
                DownloadManager downloadManager= (DownloadManager)ctx.getSystemService(Context.DOWNLOAD_SERVICE);
                request.setMimeType("application/pdf");
                request.allowScanningByMediaScanner();
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI);
                downloadManager.enqueue(request);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }


    public  class ViewHolder extends  RecyclerView.ViewHolder {
        TextView songTitle,songArtists;
        TextView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            songTitle = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

    @Override
    public Filter getFilter() {
        return songFilter;
    }

    private Filter songFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List <Song> songList1 = new ArrayList<>();
            if(constraint==null||constraint.length()==0){
                songList1.addAll(fullsonglist);
            }else{
                String filterpattern = constraint.toString().toLowerCase().trim();
                for(Song item : fullsonglist){
                    if(item.getTitle().toLowerCase().contains(filterpattern)){
                        songList1.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values=songList1;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            songList.clear();
            songList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

}
