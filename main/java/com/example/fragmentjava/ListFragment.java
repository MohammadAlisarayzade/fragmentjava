package com.example.fragmentjava;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListFragment extends androidx.fragment.app.ListFragment {

    OnFileSelectedListener callback;

    public interface OnFileSelectedListener {
        void onFileSelected(int fileResId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnFileSelectedListener) context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] files = {"file1","file2","file3"};
        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, files));
        ListView listView = getListView();
        int padding = (int) (16 * getResources().getDisplayMetrics().density); // 16dp به پیکسل
        listView.setPadding(padding, padding, padding, padding);
        listView.setDividerHeight(8);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int fileResId;
        switch (position) {
            case 0: fileResId = R.raw.file1; break;
            case 1: fileResId = R.raw.file2; break;
            default: fileResId = R.raw.file3; break;
        }
        callback.onFileSelected(fileResId);
    }
}
