package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNeighbourActivity extends Activity {

    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @BindView(R.id.activity_detail_neighbour_avatar_picture)
    public ImageView mAvatarPicture;

    @BindView(R.id.back_btn)
    public ImageButton mBackBtn;

    @BindView(R.id.activity_detail_neighbour_first_name)
    public TextView mFirstName;

    @BindView(R.id.detail_Card_Name)
    public TextView mCardName;

    @BindView(R.id.detail_home_card_img)
    public ImageView mHomeImg;

    @BindView(R.id.detail_home_card_text)
    public TextView mHomeText;

    @BindView(R.id.detail_tel_card_img)
    public ImageView mTelImg;

    @BindView(R.id.detail_tel_card_text)
    public TextView mTelText;

    @BindView(R.id.detail_site_card_img)
    public ImageView mSiteImg;

    @BindView(R.id.detail_site_card_text)
    public TextView mSiteText;

    @BindView(R.id.detail_about_me)
    public TextView mDetailAbout;

    @BindView(R.id.fav_btn)
    public android.support.design.widget.FloatingActionButton mFav_btn;

    private List<Neighbour> mFavoriteNeighboursList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);


        mApiService = DI.getNeighbourApiService();
        mFavoriteNeighboursList = mApiService.getNeighbours(true);
        getNeighbour();
        seeDetail();

    }





    private void getNeighbour() {
        mNeighbour = (Neighbour) getIntent().getSerializableExtra("neighbour");
    }

    private void seeDetail() {
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(mAvatarPicture);
        mFirstName.setText(mNeighbour.getName());
        mCardName.setText(mNeighbour.getName());
        mHomeText.setText(mNeighbour.getAddress());
        mTelText.setText(mNeighbour.getPhoneNumber());
        String siteText = "www.facebook.fr/" + mNeighbour.getName();
        mSiteText.setText(siteText);
        mDetailAbout.setText(mNeighbour.getAboutMe());
        mBackBtn.setOnClickListener(v -> finish());

        if (mFavoriteNeighboursList.contains(mNeighbour)) {
            mFav_btn.setImageDrawable(getDrawable(R.drawable.ic_baseline_star__yellow_24));
        }
        else
        {
            mFav_btn.setImageDrawable(getDrawable(R.drawable.ic_baseline_star_24));
        }
        }

    @OnClick(R.id.fav_btn)
    public void favBtn() {

        if (mFavoriteNeighboursList.contains(mNeighbour)) {
            mApiService.deleteNeighbour(mNeighbour, true);
            mFav_btn.setImageDrawable(getDrawable(R.drawable.ic_baseline_star_24));
            Toast toast = Toast.makeText(this, getString(R.string.remove_from_favorites), Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            mApiService.addNeighbour(mNeighbour, true);
            mFav_btn.setImageDrawable(getDrawable(R.drawable.ic_baseline_star__yellow_24));
            Toast toast = Toast.makeText(this, getString(R.string.add_to_favorites), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}