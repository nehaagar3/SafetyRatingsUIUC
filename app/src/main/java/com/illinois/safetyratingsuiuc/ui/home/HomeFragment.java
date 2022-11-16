package com.illinois.safetyratingsuiuc.ui.home;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.illinois.safetyratingsuiuc.R;
import com.illinois.safetyratingsuiuc.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        TextView emergency = binding.phoneEmergency;
        emergency.setPaintFlags(emergency.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        // emergency.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
        //         Intent callIntent = new Intent(Intent.ACTION_CALL);
        //         callIntent.setData(Uri.parse("tel:"+ R.string.emergency_number));//change the number
        //         startActivity(callIntent);
        //     }
        // });

        TextView police = binding.phonePolice;
        police.setPaintFlags(emergency.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}