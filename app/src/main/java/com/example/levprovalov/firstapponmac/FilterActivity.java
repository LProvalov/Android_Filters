package com.example.levprovalov.firstapponmac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

public class FilterActivity extends AppCompatActivity {

    public static String FILTER_TREE = "filter_tree";

    private FilterTree<FilterData> filterTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Intent intent = getIntent();
        this.filterTree = (FilterTree<FilterData>) intent.getSerializableExtra(FILTER_TREE);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.filter_listView);
        listView.setAdapter(new FilterExpandableListViewAdapter(this, filterTree));

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                switch(groupPosition) {
                    case 0: { // Inspections
                        filterTree.getTimeRoots().get(childPosition).getData().setChecked(!filterTree.getTimeRoots().get(childPosition).getData().isChecked());
                    } break;
                    case 1: { // Customers

                    } break;
                    case 2: { // Sites

                    } break;
                    case 3: { // Buildings

                    } break;

                }
                return false;
            }
        });

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                FilterExpandableListViewAdapter la = (FilterExpandableListViewAdapter) parent.getExpandableListAdapter();
                la.notifyDataSetChanged();

                return false;
            }
        });
    }
}
