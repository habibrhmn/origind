package com.bloodapp.blood;


import android.os.Bundle;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDetailFragment extends Fragment {


    public MenuDetailFragment() {
        // Required empty public constructor
    }

    private long menuID;

    public void setMenuID(long menuID) {
        this.menuID = menuID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (savedInstanceState != null)
        {
            menuID = savedInstanceState.getLong("menuID");
        }
        return inflater.inflate(R.layout.fragment_menu_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null)
        {
            TextView title = view.findViewById(R.id.namaes);
            TextView description = view.findViewById(R.id.descriptiond);
            Menu menu = Menu.menus[(int)menuID];
            title.setText(menu.getName());
            description.setText(menu.getDescription());

        }

    }
}
