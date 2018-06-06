package com.example.mehikamanocha.app;

/**
 * Created by mehika.manocha on 12/03/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAddAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public ListAddAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler {
        TextView TITLE, DATE;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public void remove(Object object) {
        super.remove(object);
        list.remove(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutHandler layoutHandler;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.tasks, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.TITLE = (TextView) view.findViewById(R.id.textView);
            layoutHandler.DATE = (TextView) view.findViewById(R.id.textView2);
            view.setTag(layoutHandler);

        } else {
            layoutHandler = (LayoutHandler) view.getTag();
        }
        DataProvider dataList = (DataProvider) this.getItem(position);
        layoutHandler.TITLE.setText(dataList.getTitle());
        layoutHandler.DATE.setText(dataList.getDate());

        return view;
    }

}