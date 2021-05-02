package com.omarstudiolimited.prayertimes.ui.duaa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DuaaboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DuaaboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}