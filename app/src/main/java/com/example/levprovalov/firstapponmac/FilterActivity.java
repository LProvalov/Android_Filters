package com.example.levprovalov.firstapponmac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

public class FilterActivity extends AppCompatActivity {

    public static String FILTER_TREE = "filter_tree";

    private FilterTree<FilterData> filterTree;
    private ExpandableListView listView;
    private int lastExpandedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Intent intent = getIntent();
        this.filterTree = (FilterTree<FilterData>) intent.getSerializableExtra(FILTER_TREE);

        listView = (ExpandableListView) findViewById(R.id.customer_filter_listView);
        listView.setAdapter(new FilterExpandableListViewAdapter(this, filterTree));

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ((FilterExpandableListViewAdapter) parent.getExpandableListAdapter()).onChildClick(groupPosition, childPosition);
                ((FilterExpandableListViewAdapter) parent.getExpandableListAdapter()).notifyDataSetChanged();
                return false;
            }
        });

        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    listView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
        listView.expandGroup(0);
    }

    public void onApplyButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(FILTER_TREE, this.filterTree);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onResetButtonClick(View view) {
        for (FilterNode<FilterData> b : this.filterTree.getBuildings()) {
            b.getData().setChecked(false);
        }
        for (FilterNode<FilterData> s : this.filterTree.getSites()) {
            s.getData().setChecked(false);
        }
        for (FilterNode<FilterData> c : this.filterTree.getCustomers()) {
            c.getData().setChecked(false);
        }
        for (FilterNode<FilterData> t : this.filterTree.getTimeRoots()) {
            t.getData().setChecked(false);
        }
        listView.expandGroup(0);
        ((FilterExpandableListViewAdapter) this.listView.getExpandableListAdapter()).notifyDataSetChanged();
    }
}
