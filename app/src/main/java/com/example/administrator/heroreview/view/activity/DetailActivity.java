package com.example.administrator.heroreview.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.heroreview.R;
import com.example.administrator.heroreview.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.background)
    LinearLayout background;
    @BindView(R.id.txt_name_hero)
    TextView nameDetail;
    @BindView(R.id.txt_intro)
    TextView introDetail;
    @BindView(R.id.txt_year)
    TextView yearDetail;
    @BindView(R.id.txt_text)
    TextView textDetail;
    @BindView(R.id.image_hero)
    ImageView imageHero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        String images = getIntent().getStringExtra("imagesDetail");
        String name = getIntent().getStringExtra("nameDetail");
        String text = getIntent().getStringExtra("textDetail");
        String intro = getIntent().getStringExtra("introDetail");
        String year = getIntent().getStringExtra("yearDetail");
        String backgroundColor = getIntent().getStringExtra("colorDetail");
        nameDetail.setText(name);
        yearDetail.setText(year);
        introDetail.setText(intro);
        textDetail.setText(text);
        Util.setDownloadImageView(this, images, imageHero);
        background.setBackgroundColor(Color.parseColor(backgroundColor));

    }
}
