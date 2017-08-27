package com.example.administrator.heroreview.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.heroreview.R;
import com.example.administrator.heroreview.Util;
import com.example.administrator.heroreview.model.Hero;
import com.example.administrator.heroreview.view.activity.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroListAdapter extends RecyclerView.Adapter<HeroListAdapter.HeroCardViewHolder> {
    private Context context;
    private List<Hero> heroes;

    public HeroListAdapter(Context context, List<Hero> heroes) {
        this.context = context;
        this.heroes = heroes;
    }

    @Override
    public HeroListAdapter.HeroCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_card_view, parent, false);

        return new HeroCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HeroCardViewHolder holder, final int position) {
        Util.setDownloadImageView(
                context,
                heroes.get(position).getImage(),
                holder.logoImageView);
        holder.heroName.setText(heroes.get(position).getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("imagesDetail", heroes.get(position).getImage());
                intent.putExtra("nameDetail", heroes.get(position).getTitle());
                intent.putExtra("introDetail", heroes.get(position).getIntro());
                intent.putExtra("colorDetail", heroes.get(position).getColor());
                intent.putExtra("textDetail", heroes.get(position).getText());
                intent.putExtra("yearDetail", heroes.get(position).getYear());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class HeroCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        CardView cardView;

        @BindView(R.id.logo_image)
        ImageView logoImageView;

        @BindView(R.id.hero_name)
        TextView heroName;

        public HeroCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
