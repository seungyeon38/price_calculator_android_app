package com.example.Practice_room_table.Databases;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    Context context;
    static DatabaseClient client;
    StudentsDatabase studentsDatabase;

    public DatabaseClient(Context context){
        this.context = context;

        studentsDatabase = Room.databaseBuilder(context, StudentsDatabase.class, "StudentsDatabase").build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if(client == null){
            client = new DatabaseClient(context);
        }
        return client;
    }

    public StudentsDatabase getStudentsDatabase(){
        return studentsDatabase;
    }
}
