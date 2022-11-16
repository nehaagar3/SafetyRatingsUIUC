package com.illinois.safetyratingsuiuc.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.illinois.safetyratingsuiuc.R;
import com.illinois.safetyratingsuiuc.ui.Police.PoliceFragment;
import com.illinois.safetyratingsuiuc.ui.SafeWalks.SafeWalksFragment;
import com.illinois.safetyratingsuiuc.ui.SafeRides.SafeRidesFragment;
import com.illinois.safetyratingsuiuc.ui.home.HomeFragment;
import com.illinois.safetyratingsuiuc.ui.Police.PoliceFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;

    private int numOfTabs;

    public SectionsPagerAdapter(Context context, FragmentManager fm, int numOfTabs) {
        super(fm);
        mContext = context;
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SafeRidesFragment();
            case 2:
                return new SafeWalksFragment();
            case 3:
                return new PoliceFragment();
            default:
                return null;

        }


//        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}