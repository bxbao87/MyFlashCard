package com.example.myflashcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TextAnswerAdapter extends BaseAdapter {
    Context context;
    int itemLayout;
    ArrayList<String> ListAnswer;

    public TextAnswerAdapter(Context context, int itemLayout, ArrayList<String> listAnswer) {
        this.context = context;
        this.itemLayout = itemLayout;
        ListAnswer = listAnswer;
    }

    @Override
    public int getCount() {
        return ListAnswer.size();
    }

    @Override
    public Object getItem(int i) {
        return ListAnswer.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class viewHolderTextAnswer {
        TextView answer;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (ListAnswer.size() <= 0) return view;

        viewHolderTextAnswer holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(itemLayout, null);

            holder = new viewHolderTextAnswer();
            holder.answer = (TextView) view.findViewById(R.id.Answer);

            view.setTag(holder);
        }
        else {
            holder = (viewHolderTextAnswer) view.getTag();
        }


        String AnswerUnit = ListAnswer.get(i);

        holder.answer.setText(AnswerUnit);

        return view;
    }
}
