package com.omarstudiolimited.prayertimes.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.omarstudiolimited.prayertimes.MainActivity;
import com.omarstudiolimited.prayertimes.R;

public class AboutFragment extends Fragment {

    private AboutViewModel aboutViewModel;
    View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_about, container, false);
        TextView toolbarTextView  = (TextView) ((MainActivity) this.getActivity()).findViewById(R.id.tbtv);
        toolbarTextView.setText("عن التطبيق");
        TextView about_text_view=(TextView) view.findViewById(R.id.text_about);
        about_text_view.setText(
                "عن التطبيق"+"\n"+
                        "تطبيق اوقات الصلاة هو تطبيق بسيط يحتوي على مواعيد واوقات الصلاة في الدول العربية اضافة الى بعض الادعية والاذكار"+"\n"+
                        "جميع الاوقات محدثة بشكل يومي"
        );
        return view;
    }
}