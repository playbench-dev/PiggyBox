package com.Piggy.PiggyBox.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Piggy.PiggyBox.AdapterItmes.ListViewItem;
import com.Piggy.PiggyBox.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    public ArrayList<ListViewItem> listViewItems = new ArrayList<>();


    public ListViewAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        final ViewHolder viewHolder;
        final ListViewItem listViewItem = listViewItems.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.dateTextView = (TextView) convertView.findViewById(R.id.date);
            viewHolder.savedTextView = (TextView) convertView.findViewById(R.id.saving);
            viewHolder.totalTextView = (TextView) convertView.findViewById(R.id.total);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.dateTextView.setText("" + listViewItem.getDate());
        viewHolder.savedTextView.setText("" + listViewItem.getSaved());
        viewHolder.totalTextView.setText("" + listViewItem.getTotal());
        notifyDataSetChanged();
        return convertView;
    }

    public void addItem(String date, String saved, String total) {
        ListViewItem item = new ListViewItem();

        item.setDate(date);
        item.setSaved(saved);
        item.setTotal(total);

        listViewItems.add(item);
    }

    public void remove(int i) {
        listViewItems.remove(i);
        notifyDataSetChanged();
    }
    class ViewHolder {
        public TextView dateTextView,savedTextView,totalTextView;
    }

}
