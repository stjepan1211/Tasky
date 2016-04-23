package com.example.tasky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Stjepan on 11.4.2016..
 */
public class AddTaskActivity extends Activity {

    EditText etAddTitle,etAddDescription, etAddTime, etAddPriority;
    Button btnAddTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask_layout);

        etAddTitle = (EditText) findViewById(R.id.etNewTitle);
        etAddDescription = (EditText) findViewById(R.id.etNewDescription);
        etAddTime = (EditText) findViewById(R.id.etNewTime);
        etAddPriority = (EditText) findViewById(R.id.etNewPriority);
        btnAddTask = (Button) findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String title = etAddTitle.getText().toString();
                    String description = etAddDescription.getText().toString();
                    String time = etAddTime.getText().toString();
                    String priority = etAddPriority.getText().toString();
                    Intent addTaskIntent = new Intent();
                    addTaskIntent.putExtra("TASK_TITLE", title);
                    addTaskIntent.putExtra("TASK_DESCRIPTION",description);
                    addTaskIntent.putExtra("TASK_TIME",time);
                    addTaskIntent.putExtra("TASK_PRIORITY",priority);
                    setResult(RESULT_OK, addTaskIntent);
                    finish();
            }
        });
    }

}
