package me.franciscoigor.quicktasks.controllers.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import me.franciscoigor.quicktasks.R;
import me.franciscoigor.quicktasks.models.Task;

public class HomeActivity extends AppCompatActivity {

    ListView list;
    TaskListAdapter items;
    TextView taskHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list = findViewById(R.id.list_view);

        items= new TaskListAdapter(this);
        items.addTask(new Task("Test1","Description", true));
        items.addTask(new Task("Test2","Description", true));
        items.addTask(new Task("Test3","Description", false));
        items.addTask(new Task("Test4","Description", true));
        items.addTask(new Task("Test5","Description", false));

        taskHeader=findViewById(R.id.task_header);

        list.setAdapter(items);

    }

    public void updateView(){
        taskHeader.setText(getString(R.string.daily_tasks_param, items.getCompletedCount(), items.getCount()));
    }


}
