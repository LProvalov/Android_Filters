package com.example.levprovalov.firstapponmac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class FilterExpandableListViewAdapter extends BaseExpandableListAdapter {

    private FilterTree<FilterData> filterTree;
    private Context mContext;
    private ArrayList<Integer> customerIndexes;
    private final String[] GROUP_NAMES = {"Inspection", "Customers", "Sites", "Buildings"};

    public FilterExpandableListViewAdapter(Context mContext, FilterTree<FilterData> filterTree) {
        this.filterTree = filterTree;
        this.mContext = mContext;
        customerIndexes = customerIndexes;
    }

    @Override
    public int getGroupCount() {
        return this.GROUP_NAMES.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int count = 0;
        switch (groupPosition) {
            case 0: // Inspection
                count = filterTree.getTimeRoots().size();
                break;
            case 1: { // Customers
                boolean atLeastOneRootSelected = false;
                ArrayList<Integer> customerIndexes = new ArrayList<Integer>();
                for (FilterNode<FilterData> t : filterTree.getTimeRoots()) {
                    if (t.getData().isChecked()) {
                        atLeastOneRootSelected |= true;
                        for (int customerIndex : t.getChildren()) {
                            if (!customerIndexes.contains(customerIndex))
                            {
                                count++;
                                customerIndexes.add(customerIndex);
                            }
                        }
                    }
                }
                if (!atLeastOneRootSelected) {
                    count = filterTree.getCustomers().size();
                }
            } break;
            case 2: { // Sites
                for (FilterNode<FilterData> s : filterTree.getSites()) {
                    if (filterTree.getCustomers().get(s.getParent()).getData().isChecked()) {
                        count++;
                    }
                }
            } break;
            case 3: { // Buildings
                for (FilterNode<FilterData> b : filterTree.getBuildings()) {
                    if (filterTree.getSites().get(b.getParent()).getData().isChecked()) {
                        count++;
                    }
                }
            } break;
        }
        return count;
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

    private int countOfCheckedItems(ArrayList<FilterNode<FilterData>> nodes) {
        int count = 0;
        for(FilterNode<FilterData> node : nodes) {
            if (node.getData().isChecked()) count++;
        }
        return count;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.filter_group_item, null);
        }
        ((TextView) convertView.findViewById(R.id.customer_filter_group)).setText(GROUP_NAMES[groupPosition]);
        TextView counterTextView = (TextView) convertView.findViewById(R.id.customer_filter_group_counter);
        int count = 0;
        switch (groupPosition) {
            case 0: { // Inspection
                count = countOfCheckedItems(filterTree.getTimeRoots());
            } break;
            case 1: { // Customers
                count = countOfCheckedItems(filterTree.getCustomers());
            } break;
            case 2: { // Sites
                count = countOfCheckedItems(filterTree.getSites());
            } break;
            case 3: { // Buildings
                count = countOfCheckedItems(filterTree.getBuildings());
            } break;
        }
        if (0 == count) counterTextView.setVisibility(View.INVISIBLE);
        else {
            counterTextView.setVisibility(View.VISIBLE);
            counterTextView.setText(Integer.toString(count));
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
            case 0: { // Inspection
                ((TextView)convertView.findViewById(R.id.customer_filter_text)).setText(filterTree.getTimeRoots().get(childPosition).getData().getName());
                ((CheckBox)convertView.findViewById(R.id.customer_filter_checkbox)).setChecked(filterTree.getTimeRoots().get(childPosition).getData().isChecked());
            } break;

            case 1: { // Customers
                ArrayList<Integer> customerIndexes = new ArrayList<>();
                boolean atLeastOneRootSelected = false;
                for (FilterNode<FilterData> t : filterTree.getTimeRoots()) {
                    if (t.getData().isChecked()) {
                        atLeastOneRootSelected |= true;
                        for (int cIndex : t.getChildren()) {
                            if (!customerIndexes.contains(cIndex)) {
                                customerIndexes.add(cIndex);
                            }
                        }
                    }
                }
                if (atLeastOneRootSelected) {
                    ((TextView) convertView.findViewById(R.id.customer_filter_text)).setText(filterTree.getCustomers().get(customerIndexes.get(childPosition)).getData().getName());
                    ((CheckBox) convertView.findViewById(R.id.customer_filter_checkbox)).setChecked(filterTree.getCustomers().get(customerIndexes.get(childPosition)).getData().isChecked());
                } else {
                    ((TextView) convertView.findViewById(R.id.customer_filter_text)).setText(filterTree.getCustomers().get(childPosition).getData().getName());
                    ((CheckBox) convertView.findViewById(R.id.customer_filter_checkbox)).setChecked(filterTree.getCustomers().get(childPosition).getData().isChecked());
                }
            } break;

            case 2: { // Sites
                int siteIndex = 0;
                for (FilterNode<FilterData> s : filterTree.getSites()) {
                    if (filterTree.getCustomers().get(s.getParent()).getData().isChecked()) {
                        if (siteIndex == childPosition) {
                            ((TextView) convertView.findViewById(R.id.customer_filter_text)).setText(s.getData().getName());
                            ((CheckBox) convertView.findViewById(R.id.customer_filter_checkbox)).setChecked(s.getData().isChecked());
                            break;
                        } else siteIndex++;
                    }
                }
            } break;

            case 3: { // Buildings
                int buildingIndex = 0;
                for (FilterNode<FilterData> b : filterTree.getBuildings()) {
                    if (filterTree.getSites().get(b.getParent()).getData().isChecked()) {
                        if (buildingIndex == childPosition) {
                            ((TextView) convertView.findViewById(R.id.customer_filter_text)).setText(b.getData().getName());
                            ((CheckBox) convertView.findViewById(R.id.customer_filter_checkbox)).setChecked(b.getData().isChecked());
                            break;
                        } else buildingIndex++;
                    }
                }
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

    public void onChildClick(int groupPosition, int childPosition) {
        switch (groupPosition) {
            case 0: { // Inspections
                boolean atLeastOneRootSelected = false;
                for (FilterNode<FilterData> r : filterTree.getTimeRoots()) {
                    if (r.getData().isChecked()) {
                        atLeastOneRootSelected = true;
                        break;
                    }
                }
                if (!atLeastOneRootSelected) {
                    for (FilterNode<FilterData> c : filterTree.getCustomers()) c.getData().setChecked(false);
                    for (FilterNode<FilterData> s : filterTree.getSites()) s.getData().setChecked(false);
                    for (FilterNode<FilterData> b : filterTree.getBuildings()) b.getData().setChecked(false);
                }
                FilterNode<FilterData> t = filterTree.getTimeRoots().get(childPosition);
                t.getData().setChecked(!t.getData().isChecked());
            } break;
            case 1: { // Customers
                boolean atLeastOneRootSelected = false;
                ArrayList<Integer> customerIndexes = new ArrayList<>();
                for (FilterNode<FilterData> t : filterTree.getTimeRoots()) {
                    if (t.getData().isChecked()) {
                        atLeastOneRootSelected |= true;
                        for (int  cIndex : t.getChildren()) {
                            if (!customerIndexes.contains(cIndex)) {
                                customerIndexes.add(cIndex);
                            }
                        }
                    }
                }
                FilterNode<FilterData> c;
                if (atLeastOneRootSelected) {
                    c = filterTree.getCustomers().get(customerIndexes.get(childPosition));
                } else {
                    c = filterTree.getCustomers().get(childPosition);
                }
                c.getData().setChecked(!c.getData().isChecked());
            } break;
            case 2: { // Sites
                int siteIndex = 0;
                for (FilterNode<FilterData> s : filterTree.getSites()) {
                    if (filterTree.getCustomers().get(s.getParent()).getData().isChecked()) {
                        if (siteIndex == childPosition) {
                            s.getData().setChecked(!s.getData().isChecked());
                            return;
                        } else siteIndex++;
                    }
                }
            } break;
            case 3: { // Buildings
                int buildingIndex = 0;
                for (FilterNode<FilterData> b : filterTree.getBuildings()) {
                    if (filterTree.getSites().get(b.getParent()).getData().isChecked()) {
                        if (buildingIndex == childPosition) {
                            b.getData().setChecked(!b.getData().isChecked());
                            return;
                        } else buildingIndex++;
                    }
                }
            } break;
        }
    }
}
