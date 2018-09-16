package com.example.levprovalov.firstapponmac;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final int FILTER_RESPONCE = 1;
    private ArrayList<CustomersData> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customers = new ArrayList<>();
        customers.add(new CustomersData("Harman"));
        customers.add(new CustomersData("Samsung"));
        customers.add(new CustomersData("MERA"));

        Calendar overdueCalendar = Calendar.getInstance();
        overdueCalendar.setTime(new Date());
        overdueCalendar.add(Calendar.DATE, -2);
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(new Date());
        Calendar next5Day = Calendar.getInstance();
        next5Day.setTime(new Date());
        next5Day.add(Calendar.DATE, 4);
        Calendar next15Day = Calendar.getInstance();
        next15Day.setTime(new Date());
        next15Day.add(Calendar.DATE, 14);
        Calendar nextMonth = Calendar.getInstance();
        nextMonth.setTime(new Date());
        nextMonth.add(Calendar.MONTH, 1);
        nextMonth.add(Calendar.DATE, 2);

        Site harmanNiNoSite = new Site("Nizhniy Novgorod");
        customers.get(0).getSites().add(harmanNiNoSite);
        customers.get(0).getSites().get(0).getBuildings().add(new Buildnig("Salganskay 10"));
        customers.get(0).getSites().get(0).getBuildings().get(0).getInspections().add(new Inspection(overdueCalendar.getTime()));
        customers.get(0).getSites().get(0).getBuildings().add(new Buildnig("Salganskay 24"));
        customers.get(0).getSites().get(0).getBuildings().get(1).getInspections().add(new Inspection(next5Day.getTime()));
        customers.get(0).getSites().add(new Site("USA"));
        customers.get(0).getSites().get(1).getBuildings().add(new Buildnig("Building 1"));
        customers.get(0).getSites().get(1).getBuildings().get(0).getInspections().add(new Inspection(overdueCalendar.getTime()));
        customers.get(0).getSites().get(1).getBuildings().add(new Buildnig("Building 2"));
        customers.get(0).getSites().get(1).getBuildings().get(1).getInspections().add(new Inspection(next15Day.getTime()));
        customers.get(0).getSites().get(1).getBuildings().add(new Buildnig("Building 3"));
        customers.get(0).getSites().get(1).getBuildings().get(2).getInspections().add(new Inspection(nextMonth.getTime()));

        customers.get(1).getSites().add(new Site("Russia"));
        customers.get(1).getSites().get(0).getBuildings().add(new Buildnig("Buildin E1"));
        customers.get(1).getSites().get(0).getBuildings().get(0).getInspections().add(new Inspection(todayCalendar.getTime()));
        customers.get(1).getSites().get(0).getBuildings().add(new Buildnig("Building E2"));
        customers.get(1).getSites().get(0).getBuildings().get(1).getInspections().add(new Inspection(next5Day.getTime()));
        customers.get(1).getSites().add(new Site("England"));
        customers.get(1).getSites().get(1).getBuildings().add(new Buildnig("Building 1"));
        customers.get(1).getSites().get(1).getBuildings().get(0).getInspections().add(new Inspection(todayCalendar.getTime()));
        customers.get(1).getSites().get(1).getBuildings().add(new Buildnig("Building 2"));
        customers.get(1).getSites().get(1).getBuildings().get(1).getInspections().add(new Inspection(next5Day.getTime()));

        customers.get(2).getSites().add(new Site("Russia"));
        customers.get(2).getSites().get(0).getBuildings().add(new Buildnig("B1 10"));
        customers.get(2).getSites().get(0).getBuildings().get(0).getInspections().add(new Inspection(todayCalendar.getTime()));
        customers.get(2).getSites().get(0).getBuildings().add(new Buildnig("B2 24"));
        customers.get(2).getSites().get(0).getBuildings().get(1).getInspections().add(new Inspection(next15Day.getTime()));
        customers.get(2).getSites().get(0).getBuildings().add(new Buildnig("B3 24"));
        customers.get(2).getSites().get(0).getBuildings().get(1).getInspections().add(new Inspection(next15Day.getTime()));
        customers.get(2).getSites().add(new Site("Germany"));
        customers.get(2).getSites().get(1).getBuildings().add(new Buildnig("Building 1"));
        customers.get(2).getSites().get(1).getBuildings().get(0).getInspections().add(new Inspection(next5Day.getTime()));
        customers.get(2).getSites().get(1).getBuildings().add(new Buildnig("Building 2"));
        customers.get(2).getSites().get(1).getBuildings().get(1).getInspections().add(new Inspection(nextMonth.getTime()));
        customers.get(2).getSites().get(1).getBuildings().add(new Buildnig("Building 3"));
        customers.get(2).getSites().get(1).getBuildings().get(1).getInspections().add(new Inspection(next15Day.getTime()));



    }

    public void onFilterClick(View view) {
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(new Date());
        Calendar next5Day = Calendar.getInstance();
        next5Day.setTime(new Date());
        next5Day.add(Calendar.DATE, 5);
        Calendar next15Day = Calendar.getInstance();
        next15Day.setTime(new Date());
        next15Day.add(Calendar.DATE, 15);
        Calendar nextMonth = Calendar.getInstance();
        nextMonth.setTime(new Date());
        nextMonth.add(Calendar.MONTH, 1);

        FilterTree<FilterData> filterTree = new FilterTree<FilterData>();
        filterTree.getTimeRoots().add(new FilterNode<FilterData>(new FilterData("Overdue")));
        filterTree.getTimeRoots().add(new FilterNode<FilterData>(new FilterData("Today")));
        filterTree.getTimeRoots().add(new FilterNode<FilterData>(new FilterData("Next 7 days")));
        filterTree.getTimeRoots().add(new FilterNode<FilterData>(new FilterData("Next 15 days")));
        filterTree.getTimeRoots().add(new FilterNode<FilterData>(new FilterData("Next month")));
        for (CustomersData c : customers) {
            FilterData customer = new FilterData(c.getName());
            FilterNode<FilterData> cfn = new FilterNode<FilterData>(customer);

            filterTree.getCustomers().add(cfn);
            for(Site s : c.getSites()) {
                FilterData site = new FilterData(s.getName());
                FilterNode<FilterData> sfn = new FilterNode<FilterData>(site);
                sfn.setParent(filterTree.getCustomers().size() - 1);
                filterTree.getSites().add(sfn);
                cfn.getChildren().add(filterTree.getSites().size() - 1);

                for(Buildnig b : s.getBuildings()) {
                    FilterData building = new FilterData(b.getName());
                    FilterNode<FilterData> bfn = new FilterNode<FilterData>(building);
                    bfn.setParent(filterTree.getSites().size() - 1);
                    filterTree.getBuildings().add(bfn);
                    sfn.getChildren().add(filterTree.getBuildings().size() - 1);

                    for(Inspection i : b.getInspections()) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(i.getDate());
                        if (calendar.before(todayCalendar)) {
                            filterTree.getTimeRoots().get(0).getChildren().add(filterTree.getCustomers().size() - 1);
                        } else if (calendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR) &&
                                calendar.get(Calendar.DAY_OF_YEAR) == todayCalendar.get(Calendar.DAY_OF_YEAR)) {
                            filterTree.getTimeRoots().get(1).getChildren().add(filterTree.getCustomers().size() - 1);
                        } else if (calendar.after(todayCalendar) && calendar.before(next5Day)) {
                            filterTree.getTimeRoots().get(2).getChildren().add(filterTree.getCustomers().size() - 1);
                        } else if (calendar.after(next5Day) && calendar.before(next15Day)) {
                            filterTree.getTimeRoots().get(3).getChildren().add(filterTree.getCustomers().size() - 1);
                        } else if (calendar.after(next15Day) && calendar.before(nextMonth)) {
                            filterTree.getTimeRoots().get(4).getChildren().add(filterTree.getCustomers().size() - 1);
                        }
                    }
                }
            }

        }
        Intent filterIntent = new Intent(this, FilterActivity.class);
        filterIntent.putExtra(FilterActivity.FILTER_TREE, filterTree);
        startActivityForResult(filterIntent, FILTER_RESPONCE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case FILTER_RESPONCE: {

            } break;
        }
    }
}
