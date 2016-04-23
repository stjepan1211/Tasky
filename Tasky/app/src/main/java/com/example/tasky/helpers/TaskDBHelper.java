package com.example.tasky.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tasky.models.Task;

import java.util.ArrayList;

/**
 * Created by Stjepan on 11.4.2016..
 */
public class TaskDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "taskInformation";
    static final String TABLE_TASKS = "tasks2";

    static final String KEY_ID = "_id";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_TIME = "time";
    static final String KEY_IMGRS = "_imgRs";

    public TaskDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TASKS
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_TITLE + " TEXT, " + KEY_DESCRIPTION + " TEXT, "
                + KEY_TIME + " TEXT, " + KEY_IMGRS + " INTEGER" + ");";
        db.execSQL(query);
    }
    //CREATE TABLE (KEY_ID INTEGER PRIMARY KEY, KEY_TITLE TEXT, KEY_DESCRIPTION TEXT, KEY_TIME TEXT, KEY_IMGRS INTEGER)

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_IF = "DROP TABLE IF EXISTS " + TABLE_TASKS;
        onCreate(db);
    }


    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, task.getTitle());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_TIME, task.getTime());
        values.put(KEY_IMGRS, task.getImgRs());

        db.insert(TABLE_TASKS, KEY_TITLE, values);
        db.close();
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_TASKS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(
                        cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getInt(4));
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        db.close();
        return tasks;
    }

   /* public Task getFirst()
    {
        Task first = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.query(TABLE_TASKS, null, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            first = new Task(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),cursor.getInt(4));
        }
        return first;
    }*/

    public void deleteTask(Task task) {
        int id = task.getId();
        String[] arg = new String[]{String.valueOf(id)};
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + "=?", arg);
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, null, null);
        db.execSQL("delete from " + TABLE_TASKS);
        db.close();
    }

    public Task getLast() {
        Task last = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_TASKS, null, null, null, null, null, null);
        if (cursor.moveToLast()) {
            last = new Task(cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getInt(4));
        }
        return last;
    }
}
