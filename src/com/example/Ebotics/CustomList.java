package com.example.Ebotics;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lilian on 11/5/14.
 * THis class carries the custom listView.
 */

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] subject;
    private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] subject, Integer[] imageId) {
        super(context, R.layout.list_single, subject);
        this.context = context;
        this.subject = subject;
        this.imageId = imageId;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.tvhometxt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(subject[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}