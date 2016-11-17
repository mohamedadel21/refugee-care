package com.example.mohammed.refugeecare.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.mohammed.refugeecare.fragments.CharitiesFragment4;
import com.example.mohammed.refugeecare.fragments.DonationFragment2;
import com.example.mohammed.refugeecare.fragments.Fragment_jobs;
import com.example.mohammed.refugeecare.fragments.HelpFragment1;
import com.example.mohammed.refugeecare.fragments.JopFragment3;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/13/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {


    ArrayList<String> titles;
    FragmentManager fm;
    public ViewPagerAdapter(FragmentManager fm  , ArrayList<String> titles) {
        super(fm);
        this.fm  = fm ;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new HelpFragment1();
            case 1 :
                return new DonationFragment2();
            case 2:
                return new Fragment_jobs();
            case 3:
                return  new CharitiesFragment4();
            default:
                return new HelpFragment1();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);

    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fm.beginTransaction().remove((Fragment)(object)).commit();
    }
}
