package com.example.selfdevelopmentapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selfdevelopmentapplication.Adapter.AdapterTask;
import com.example.selfdevelopmentapplication.R;
import com.example.selfdevelopmentapplication.Model.Task;
import com.example.selfdevelopmentapplication.DB.TaskDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fb_addNew;
    RecyclerView rv_task;

    AdapterTask adapterTask;
    ArrayList<Task> taskArrayList;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskDB.init(this);
        context = MainActivity.this;

        fb_addNew = findViewById(R.id.fb_addNew);
        rv_task = findViewById(R.id.rv_task);

        taskArrayList = new ArrayList<>();
        rv_task.setLayoutManager(new LinearLayoutManager(this));
        adapterTask = new AdapterTask(taskArrayList, this);
        rv_task.setAdapter(adapterTask);

        adapterTask.replaceArrayList(TaskDB.getAllData());


        fb_addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddTaskActivity.class));
            }
        });

        //material drawer
        materialDrawer();
    }

    private void materialDrawer() {
        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorAccent)
                .addProfiles(
                        new ProfileDrawerItem().withName("Razaul Munir")
                                .withEmail("razaulmunir@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.boss_baby))
                )
                .build();

        new DrawerBuilder()
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        new PrimaryDrawerItem().withIcon(R.drawable.ic_home_black_24dp).withIdentifier(0).withName("Home"),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withIcon(R.drawable.ic_done_black_24dp).withIdentifier(2).withName("Complete"),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withIcon(R.drawable.ic_do_not_disturb_alt_black_24dp).withIdentifier(3).withName("Not Complete")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        long identifier = drawerItem.getIdentifier();
                        drawerClickedItem(identifier);
                        return false;
                    }
                })
                .withActivity(this)
                .build();
    }

    private void drawerClickedItem(long identifier) {
        switch ((int) identifier) {
            case 0:
                adapterTask.replaceArrayList(TaskDB.getAllData());
                Toast.makeText(MainActivity.this, "Clicked on Home", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                adapterTask.replaceArrayList(TaskDB.searchInColumnForAll(8, "1", -1));
                Toast.makeText(MainActivity.this, "Clicked on Complete", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                adapterTask.replaceArrayList(TaskDB.searchInColumnForAll(8, "0", -1));
                Toast.makeText(MainActivity.this, "Clicked on Not Complete", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void deleteSelectedTask(int id) {
        boolean result = TaskDB.deleteRow(id);
        Toast.makeText(context, "Delete: " + result, Toast.LENGTH_SHORT).show();
        onResume();
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapterTask.replaceArrayList(TaskDB.getAllData());
    }
}
