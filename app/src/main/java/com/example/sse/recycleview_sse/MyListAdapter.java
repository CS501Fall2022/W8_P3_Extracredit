package com.example.sse.recycleview_sse;
import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    public static final String ratings = "RATINGS";
    public static float episodeRatings[];

    private String episodes[];
    String episodeDescriptions[];
    String episodelinks[];
    ArrayList<Integer> episodeImages;
    Context context;
    public MyListAdapter(Context aContext) {
        context = aContext;
        episodes = aContext.getResources().getStringArray(R.array.episodes);
        episodeDescriptions = aContext.getResources().getStringArray(R.array.episode_descriptions);
        episodelinks =  aContext.getResources().getStringArray(R.array.episode_links);
        episodeRatings = new float[episodes.length];

        for (int i = 0; i < episodeRatings.length; i++) {
            if (MainActivity.sharedPrefs.contains(ratings + i)) {
//                Log.w("TAG", "MyCustomAdapter: " + i + " " + MainActivity.sharedPrefs.getFloat(ratings + i, 0));
                episodeRatings[i] = MainActivity.sharedPrefs.getFloat(ratings + i, 0);
            }
        }
        episodeImages = new ArrayList<Integer>();
        episodeImages.add(R.drawable.st_spocks_brain);
        episodeImages.add(R.drawable.st_arena__kirk_gorn);
        episodeImages.add(R.drawable.st_this_side_of_paradise__spock_in_love);
        episodeImages.add(R.drawable.st_mirror_mirror__evil_spock_and_good_kirk);
        episodeImages.add(R.drawable.st_platos_stepchildren__kirk_spock);
        episodeImages.add(R.drawable.st_the_naked_time__sulu_sword);
        episodeImages.add(R.drawable.st_the_trouble_with_tribbles__kirk_tribbles);
        episodeImages.add(R.drawable.st_mirror_mirror__evil_spock_and_good_kirk);
    }
    public  class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgEpisode;
        public TextView tvEpisodeTitle;
        public TextView tvEpisodeDescription;
        public RatingBar tvRatingBar;
        public Button btnRandom;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgEpisode = (ImageView) itemView.findViewById(R.id.imgEpisode);
            this.tvEpisodeTitle = (TextView) itemView.findViewById(R.id.tvEpisodeTitle);
            this.tvEpisodeDescription = (TextView) itemView.findViewById(R.id.tvEpisodeDescription);
            this.tvRatingBar = (RatingBar) itemView.findViewById(R.id.rbEpisode);
            this.btnRandom = (Button) itemView.findViewById(R.id.btnRandom);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItem= layoutInflater.inflate(R.layout.listview_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvEpisodeTitle.setText(episodes[position]);
        holder.tvEpisodeDescription.setText(episodeDescriptions[position]);
        holder.imgEpisode.setImageResource(episodeImages.get(position).intValue());
        holder.tvRatingBar.setRating(episodeRatings[position]);
        holder.tvEpisodeTitle.setText(episodes[position]);

        holder.btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(episodelinks[position]));
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return episodes.length;
    }
}

