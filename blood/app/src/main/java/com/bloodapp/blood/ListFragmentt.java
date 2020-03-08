package com.bloodapp.blood;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragmentt extends ListFragment {

    static interface MedListListener{
        void itemClicked(long id);
    }

    private MedListListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (MedListListener) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        if(listener != null)
        {
            listener.itemClicked(id);
        }

        super.onListItemClick(l, v, position, id);
    }

    public ListFragmentt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String names [] = new String[Menu.menus.length];

        for(int i = 0; i<names.length; i++){
            names[i] = Menu.menus[i].getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,names);
        setListAdapter(adapter);
        return super.onCreateView(inflater,container,savedInstanceState);

    }

}
