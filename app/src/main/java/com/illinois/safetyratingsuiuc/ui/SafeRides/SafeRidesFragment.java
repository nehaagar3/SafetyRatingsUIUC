package com.illinois.safetyratingsuiuc.ui.SafeRides;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.illinois.safetyratingsuiuc.ExpandableListData;
import com.illinois.safetyratingsuiuc.MyExpandableListAdapter;
import com.illinois.safetyratingsuiuc.R;
import com.illinois.safetyratingsuiuc.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SafeRidesFragment extends Fragment {

    private FragmentGalleryBinding binding;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List expandableListTitle;
    HashMap expandableListDetail;
    private Button button;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SafeRidesViewModel galleryViewModel =
                new ViewModelProvider(this).get(SafeRidesViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Expadnable list view declarations
        expandableListView = (ExpandableListView) root.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListData.getSafeRidesData();
        expandableListTitle = new ArrayList(expandableListDetail.keySet());
        expandableListAdapter = new MyExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if(groupPosition == 0) {
                    try {
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://play.google.com/store/apps/details?id=com.routematch.cumtd"));
                        startActivity(viewIntent);
                    } catch(Exception e) {

                    }
                }
                return false;
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}