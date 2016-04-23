package com.example.tasky;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tasky.adapters.TaskAdapter;
import com.example.tasky.helpers.TaskDBHelper;
import com.example.tasky.models.Task;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE = 1;
    ArrayList<Task> myTasks = new ArrayList<Task>();

    TaskAdapter adapter;

    @Bind(R.id.lvTasks)
    ListView lvTasks;
    @Bind(R.id.bAddTaskActivity)
    Button bAddTaskActivity;
    @Bind(R.id.bPrintDatabase)
    Button bPrintDatabase;
    @Bind(R.id.bDeleteDatabase)
    Button bDeleteDatabase;
    @Nullable
    @Bind(R.id.ivColor)
    ImageView ivColor;

    TaskDBHelper dbHelper = new TaskDBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Methods for view  and control initialization
        populateList();
        initializeAdapter();
    }


    @OnClick(R.id.bDeleteDatabase)
    public void OnClickDelete() {
        dbHelper.deleteAll();
        myTasks.clear();
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.bPrintDatabase)
    public void OnClickPrint() {
        if (dbHelper.getLast() == null)
            Toast.makeText(MainActivity.this, "No data in database", Toast.LENGTH_SHORT).show();
        myTasks.addAll(dbHelper.getTasks());
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.bAddTaskActivity)
    public void onCLick() {
        Intent intentAdd = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intentAdd, REQUEST_CODE);
    }

    @OnItemLongClick(R.id.lvTasks)
    public boolean onLongClickTasks(int position) {
        Task clicked = (Task) adapter.getItem(position);
        String msg = "Task " + clicked.getTitle() + " has been deleted ";
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

        dbHelper.deleteTask((Task) adapter.getItem(position));
        myTasks.remove(position);
        adapter.notifyDataSetChanged();

        return false;
    }


    /**
     * Adapter initialization
     */
    public void initializeAdapter() {
        adapter = new TaskAdapter(this, myTasks);
        lvTasks.setAdapter(adapter);
    }


    /**
     * on activity result method creates new task object when it gets params from second activity
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String title = data.getStringExtra("TASK_TITLE");
                String description = data.getStringExtra("TASK_DESCRIPTION");
                String time = data.getStringExtra("TASK_TIME");
                String priority = data.getStringExtra("TASK_PRIORITY");
                Task task;
                switch (priority) {
                    case "1":
                        task = new Task(title, description, time, R.drawable.red);
                        dbHelper.addTask(task);
                        break;
                    case "2":
                        task = new Task(title, description, time, R.drawable.yellow);
                        dbHelper.addTask(task);
                        break;
                    case "3":
                        task = new Task(title, description, time, R.drawable.green);
                        dbHelper.addTask(task);
                        break;
                    default:
                        dbHelper.addTask(new Task(title, description, time, R.drawable.green));
                        break;
                }
                /**
                 * inputing new task in list and database
                 */
                initializeAdapter();
                myTasks.add(dbHelper.getLast());
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * function  populates list and base with three tasks when application is started
     */
    public void populateList() {
        Task first = new Task("Kupi kruh", "idi u pekaru i kupi kruh", "07:00", R.drawable.green);
        Task second = new Task("Napisi app", "napisi cool aplikaciju", "18:00", R.drawable.yellow);
        Task third = new Task("Zaradi milijune", "loooooool", "07:00", R.drawable.red);
        Log.d("LOG", "spremi u bazu");
        dbHelper.addTask(first);
        dbHelper.addTask(second);
        dbHelper.addTask(third);
        Log.d("LOG", "spremljeno u bazu");
        myTasks.addAll(dbHelper.getTasks());
    }
}

