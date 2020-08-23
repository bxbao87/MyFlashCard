package com.example.myflashcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAnswerAdapter extends BaseAdapter {

    Context context;
    int itemLayout;
    ArrayList<Bitmap> ListImageOfAnswer;
    ArrayList<String> ListAnswer;

    public ImageAnswerAdapter(Context context, int itemLayout, ArrayList<Bitmap> listImageOfAnswer, ArrayList<String> listAnswer) {
        this.context = context;
        this.itemLayout = itemLayout;
        ListImageOfAnswer = listImageOfAnswer;
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


    class viewHolderImageAnswer {
        ImageView ImageAnswer;
        TextView answer;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (ListAnswer.size() <= 0) return view;

        viewHolderImageAnswer holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(itemLayout, null);

            holder = new viewHolderImageAnswer();
            holder.ImageAnswer = (ImageView) view.findViewById(R.id.ImageOfAnswer);
            holder.answer = (TextView) view.findViewById(R.id.Answer);

            view.setTag(holder);
        }
        else {
            holder = (viewHolderImageAnswer) view.getTag();
        }


        Bitmap ImageUnit = ListImageOfAnswer.get(i);
        String AnswerUnit = ListAnswer.get(i);

        holder.answer.setText(AnswerUnit);
        holder.ImageAnswer.setImageBitmap(ImageUnit);

        return view;
    }
}
