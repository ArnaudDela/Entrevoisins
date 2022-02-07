package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            Bundle outStateNeighbour = new Bundle();
            outStateNeighbour.putBoolean("isFavorite",false);
            NeighbourFragment neighbourFragment = new NeighbourFragment();
            neighbourFragment.setArguments(outStateNeighbour);
            return neighbourFragment;
        }
        else{
            Bundle outStateFavorite = new Bundle();
            outStateFavorite.putBoolean("isFavorite",true);
            NeighbourFragment favoriteFragment = new NeighbourFragment();
            favoriteFragment.setArguments(outStateFavorite);
            return favoriteFragment;
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}