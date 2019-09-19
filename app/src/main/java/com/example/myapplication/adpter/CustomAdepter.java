package com.example.myapplication.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.ShowDataViaRetro;
import com.example.myapplication.models.UserResultResponseModel;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.UserResultResponseModel;

import java.util.ArrayList;

public class CustomAdepter extends BaseAdapter {

    Context context;
    ArrayList<UserResultResponseModel> userlist = new ArrayList<>();

    public CustomAdepter(ShowDataViaRetro mainActivity, ArrayList<UserResultResponseModel> userlist) {

        context = mainActivity;
        this.userlist = userlist;
    }

    @Override
    public int getCount() {
        return userlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.raw_show_data, viewGroup, false);
        TextView txtName = view.findViewById(R.id.txt_name);
        TextView txtNumber = view.findViewById(R.id.txt_number);
        TextView txtAdd = view.findViewById(R.id.txt_address);
        TextView txtEmail = view.findViewById(R.id.txt_email);
        TextView txtPassword = view.findViewById(R.id.txt_password);
        TextView txtCity = view.findViewById(R.id.txt_city);
        TextView txtGender = view.findViewById(R.id.txt_gender);

        txtName.setText(userlist.get(i).name);
        txtAdd.setText(userlist.get(i).address);
        txtCity.setText(userlist.get(i).city);
        txtNumber.setText(userlist.get(i).number);
        txtEmail.setText(userlist.get(i).email);
        txtPassword.setText(userlist.get(i).password);
        txtGender.setText(userlist.get(i).gender);
        return view;
    }
}
