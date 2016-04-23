package com.example.tasky.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tasky.R;
import com.example.tasky.models.Task;

import java.util.ArrayList;
import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Stjepan on 11.4.2016..
 */
public class TaskAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Task> taskList;
    private LayoutInflater mLayoutInflater;

    public TaskAdapter(Context context, ArrayList<Task> taskList) {
        this.mContext = context;
        this.taskList = taskList;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*if(convertView == null){
            convertView = View.inflate(cntx, R.layout.item_layout, null);
        }


        Task current = taskList.get(position);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        ImageView ivColor = (ImageView) convertView.findViewById(R.id.ivColor);

        tvTitle.setText(current.getTitle());
        tvDescription.setText(current.getDescription());
        tvTime.setText(" - " + current.getTime());
        ivColor.setImageResource(current.getImgRs());*/

        ViewHolder holder;
        Task current = taskList.get(position);

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = this.mLayoutInflater.inflate(R.layout.item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.tvTime.setText(current.getTime());
        holder.tvDescription.setText(current.getDescription());
        holder.tvTitle.setText(current.getTitle());
        holder.ivColor.setImageResource(current.getImgRs());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tvTitle)
        TextView tvTitle;
        @Bind(R.id.tvDescription)
        TextView tvDescription;
        @Bind(R.id.tvTime)
        TextView tvTime;
        @Bind(R.id.ivColor)
        ImageView ivColor;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
