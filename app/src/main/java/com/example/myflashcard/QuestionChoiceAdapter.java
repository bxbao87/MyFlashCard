package com.example.myflashcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionChoiceAdapter extends BaseAdapter {

    ArrayList<String> QuestionList;
    Context context;
    int itemLayout;

    public QuestionChoiceAdapter(ArrayList<String> questionList, Context context, int itemLayout) {
        QuestionList = questionList;
        this.context = context;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        return QuestionList.size();
    }

    @Override
    public Object getItem(int i) {
        return QuestionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class viewHolderQuestion {
        TextView QuestionNumber;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (QuestionList.size() <= 0) return view;


        viewHolderQuestion holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(itemLayout, null);

            holder = new viewHolderQuestion();
            holder.QuestionNumber = (TextView) view.findViewById(R.id.QuestionNumber);

            view.setTag(holder);
        }
        else {
            holder = (viewHolderQuestion) view.getTag();
        }

        holder.QuestionNumber.setText(Integer.toString(i));

        return view;
    }
}
