package com.bloodapp.blood;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class userlist extends ArrayAdapter<Reciever> {

    private Activity context;
    public List<Reciever> reciverList;

    public userlist(Activity context, List<Reciever> reciverList)
    {
        super(context,R.layout.list_layout, reciverList);
        this.context= context;
        this.reciverList=reciverList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null, true);

        TextView named =  listViewItem.findViewById(R.id.name);
        TextView khoon =  listViewItem.findViewById(R.id.blood);
        TextView phone = listViewItem.findViewById(R.id.numbers);
        TextView age = listViewItem.findViewById(R.id.aged);
        TextView emaild = listViewItem.findViewById(R.id.verify);

        Reciever reciever = reciverList.get(position);

        named.setText(reciever.getUserName());
        khoon.setText(reciever.getUserBlood());
        phone.setText(reciever.getUserNumber());
        age.setText(reciever.getUserAge());
        emaild.setText(reciever.getUserVerify());
        return  listViewItem;
    }
}
