package com.example.selfdevelopmentapplication.DB;

import android.content.Context;
import android.database.Cursor;

import com.example.selfdevelopmentapplication.Model.Task;

import java.util.ArrayList;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

public class TaskDB {
    private static EasyDB easyDB;

    public static void init(Context context) {
        if (easyDB == null) {
            easyDB = EasyDB.init(context, "Development")
                    .setTableName("Task")
                    .addColumn(new Column("category", "text"))
                    .addColumn(new Column("date", "text"))
                    .addColumn(new Column("situation", "text"))
                    .addColumn(new Column("character", "text"))
                    .addColumn(new Column("output", "text"))
                    .addColumn(new Column("priority", "text"))
                    .addColumn(new Column("duration", "text"))
                    .addColumn(new Column("complete", "text"))
                    .addColumn(new Column("description", "text"))
                    .addColumn(new Column("solution", "text"))
                    .doneTableColumn();
        }
    }

    public static boolean addData(Task task) {
        boolean done = easyDB.addData(1, task.getCategory())
                .addData(2, task.getDate())
                .addData(3, task.getSituation())
                .addData(4, task.getCharacter())
                .addData(5, task.getOutput())
                .addData(6, task.getPriority())
                .addData(7, task.getDuration())
                .addData(8, task.getComplete())
                .addData(9, task.getDescription())
                .addData(10, task.getSolution())
                .doneDataAdding();

        return done;
    }

    public static ArrayList<Task> getAllData() {
        Cursor res = easyDB.getAllData();
        ArrayList<Task> taskArrayList = getAllDataInModel(res);
        return taskArrayList;
    }

    public static Task searchInColumn(int columnNumber, String data, int limit) {
        Cursor res = easyDB.searchInColumn(columnNumber, data, limit); // Here we passed limit = 1. Thus it will return only one row data with the matched column value
        Task task = getOneRowDataInModel(res);
        return task;
    }

    public static boolean updateData(Task task) {
        boolean updated = easyDB.updateData(1, task.getCategory())
                .updateData(2, task.getDate())
                .updateData(3, task.getSituation())
                .updateData(4, task.getCharacter())
                .updateData(5, task.getOutput())
                .updateData(6, task.getPriority())
                .updateData(7, task.getDuration())
                .updateData(8, task.getComplete())
                .updateData(9, task.getDescription())
                .updateData(10, task.getSolution())
                .rowID(task.getId());
        return updated;
    }

    public static boolean deleteRow(int rowId) {
        boolean deleted = easyDB.deleteRow(rowId);
        return deleted;
    }

    public static Task getOneRowDataInModel(Cursor res) {
        Task task = new Task();
        if (res != null) {
            res.moveToFirst(); // Because here's only one row data
            task.setId(res.getInt(0));
            task.setCategory(res.getInt(1));
            task.setDate(res.getString(2));
            task.setSituation(res.getInt(3));
            task.setCharacter(res.getInt(4));
            task.setOutput(res.getString(5));
            task.setPriority(res.getInt(6));
            task.setDuration(res.getString(7));
            task.setComplete(res.getInt(8));
            task.setDescription(res.getString(9));
            task.setSolution(res.getString(10));
        }
        return task;
    }

    public static ArrayList<Task> getAllDataInModel(Cursor res) {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        while (res.moveToNext()) {
            Task task = new Task();
            task.setId(res.getInt(0));
            task.setCategory(res.getInt(1));
            task.setDate(res.getString(2));
            task.setSituation(res.getInt(3));
            task.setCharacter(res.getInt(4));
            task.setOutput(res.getString(5));
            task.setPriority(res.getInt(6));
            task.setDuration(res.getString(7));
            task.setComplete(res.getInt(8));
            task.setDescription(res.getString(9));
            task.setSolution(res.getString(10));

            taskArrayList.add(task);
        }
        return taskArrayList;
    }
}

