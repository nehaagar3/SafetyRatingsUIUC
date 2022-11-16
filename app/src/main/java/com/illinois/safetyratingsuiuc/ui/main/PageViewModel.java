package com.illinois.safetyratingsuiuc.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Safety is multi-dimensional. If you or someone you know are a victim of a " +
                "crime or otherwise, feel unsafe, please contact authorities and click on the " +
                "navigation bar on the top-left corner to explore other safety resources on " +
                "campus. \n" +
                "\n" +
                "Emergency: 911 \n" +
                "Campus Police: (217) 333-1216");
    }

    public LiveData<String> getText() {
        return mText;
    }
}