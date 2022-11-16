package com.illinois.safetyratingsuiuc.ui.SafeWalks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SafeWalksViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SafeWalksViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}