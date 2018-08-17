package com.lokesh.resumeapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lokesh.resumeapp.Activity.HomeActivity;
import com.lokesh.resumeapp.Model.CreateBioModel;
import com.lokesh.resumeapp.Model.StateModel;
import com.lokesh.resumeapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kishumewara on 15/08/18.
 */

public class StateAdapter extends BaseAdapter {

   List<StateModel> stateLists;
    Context context;
    int resource;
    LayoutInflater inflate;

    public StateAdapter(Context context,List<StateModel> stateLists) {
        this.context = context;
        this.stateLists = stateLists;
        inflate = LayoutInflater.from(context);
    }

    static class ViewHolder {
        protected TextView textView ;
    }

    @Override
    public int getCount() {
        return stateLists.size();
    }

    @Override
    public Object getItem(int position) {
        return  stateLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;

        if ( convertView == null ) {

            holder = new ViewHolder();
            convertView = inflate.inflate(R.layout.item_spiner_state, null);
            holder.textView = convertView.findViewById( R.id.tvState );
            convertView.setTag( holder );
            convertView.setTag( R.id.tvState,holder.textView );
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText( stateLists.get(position).getName());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateLists.get(position).getName();
            }
        });

        return convertView;

    }

}
