package com.example.myflashcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    ArrayList<Categories> CategoryList;
    Context context;
    int itemLayout;

    public CategoryAdapter(ArrayList<Categories> categoryList, Context context, int itemLayout) {
        CategoryList = categoryList;
        this.context = context;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        return CategoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return CategoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class viewHolder {
        TextView name;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (CategoryList.size() <= 0) return view;

        viewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(itemLayout, null);

            holder = new viewHolder();
            holder.name = (TextView) view.findViewById(R.id.CategoriesName);

            view.setTag(holder);
        }
        else {
            holder = (viewHolder) view.getTag();
        }


        Categories unit = CategoryList.get(i);

        holder.name.setText(unit.getName());

        return view;
    }
}
