package com.illinois.safetyratingsuiuc.ui.Police;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PoliceViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PoliceViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}