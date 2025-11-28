package com.example.fragmentjava;



import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DetailFragment extends Fragment {

    private static final String ARG_FILE_ID = "file_id";
    private TextView textView;

    public static DetailFragment newInstance(int fileResId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_FILE_ID, fileResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(getActivity());
        textView = new TextView(getActivity());
        textView.setPadding(32, 32, 32, 32);
        textView.setTextSize(16);
        scrollView.addView(textView);

        if (getArguments() != null) {
            int fileId = getArguments().getInt(ARG_FILE_ID);
            showText(fileId);
        }
        return scrollView;
    }

    private void showText(int fileResId) {
        try {
            InputStream is = getResources().openRawResource(fileResId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) text.append(line).append("\n");
            textView.setText(text.toString());
            reader.close();
        } catch (Exception e) {
            textView.setText("خطا در خواندن فایل");
        }
    }
}
