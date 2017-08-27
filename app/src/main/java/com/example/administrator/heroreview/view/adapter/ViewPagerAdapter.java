package com.example.administrator.heroreview.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.heroreview.R;
import com.example.administrator.heroreview.Util;
import com.example.administrator.heroreview.model.Hero;
import com.example.administrator.heroreview.view.activity.DetailActivity;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Hero> heroes;
    private LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context, List<Hero> heroes) {
        this.context = context;
        this.heroes = heroes;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.img_hero_viewpager, container, false);

        ImageView imageView = (ImageView) item_view.findViewById(R.id.item_viewpager);
        Util.setDownloadImageView(context, heroes.get(position).getImage(), imageView);
        container.addView(item_view);
        item_view.setOnClickListener(new View.OnClickListener() {
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
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
