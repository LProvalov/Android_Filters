package com.example.levprovalov.firstapponmac;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FilterExpandableListViewAdapter extends BaseExpandableListAdapter {

    private FilterTree<FilterData> filterTree;
    private Context mContext;
    private ArrayList<Integer> customerIndexes;

    public FilterExpandableListViewAdapter(Context mContext, FilterTree<FilterData> filterTree) {
        this.filterTree = filterTree;
        this.mContext = mContext;
        customerIndexes = customerIndexes;
    }

    @Override
    public int getGroupCount() {
        return 4;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        switch (groupPosition) {
            case 0: {
                return 5;
            }
            case 1: {
                int count = 0;
                for(FilterNode<FilterData> t : filterTree.getTimeRoots()) {
                    if (t.getData().isChecked()) {
                        for (int customerIndex : t.getChildren()) {
                            if (!this.customerIndexes.contains(customerIndex))
                            {
                                count++;
                            }
                        }
                    }
                }
                return count;
            }
            case 2: {

            } break;
            case 3: {

            } break;
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.filter_group_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.group_textview);
        switch (groupPosition) {
            case 0: {
                textView.setText("Inspections");
            } break;
            case 1: {
                textView.setText("Customers");
            } break;
            case 2: {
                textView.setText("Sites");
            } break;
            case 3: {
                textView.setText("Buildings");
            } break;
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.filter_child_item, null);
        }

        switch(groupPosition) {
            case 0: { // inspections
                TextView tv = (TextView) convertView.findViewById(R.id.filter_child_item_text);
                switch(childPosition) {
                    case 0: {
                        tv.setText("Overdue");
                    } break;
                    case 1: {
                        tv.setText("Today");
                    } break;
                    case 2: {
                        tv.setText("Next 7 days");
                    } break;
                    case 3: {
                        tv.setText("Next 15 days");
                    } break;
                    case 4: {
                        tv.setText("Next month");
                    } break;
                }
            } break;
            case 1: { // customers
                TextView tv = (TextView) convertView.findViewById(R.id.filter_child_item_text);
                for(FilterNode<FilterData> t : filterTree.getTimeRoots()) {
                    if (t.getData().isChecked()) {
                        for (int customerIndex : t.getChildren()) {
                            if (!this.customerIndexes.contains(customerIndex))
                            {
                                this.customerIndexes.add(customerIndex);
                                tv.setText(filterTree.getCustomers().get(customerIndex).getData().getName());
                            }
                        }
                    }
                }
            } break;
            case 2: { // sites

            } break;
            case 3: { // buildings

            } break;
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

}
