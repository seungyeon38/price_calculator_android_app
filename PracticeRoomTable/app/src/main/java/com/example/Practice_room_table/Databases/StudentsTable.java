package com.example.Practice_room_table.Databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Entity means tableDatabaseHelper
@Entity
public class StudentsTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "stu_name")
    private String stu_name;

    @ColumnInfo(name = "stu_standard")
    private String stu_standard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_standard() {
        return stu_standard;
    }

    public void setStu_standard(String stu_standard) {
        this.stu_standard = stu_standard;
    }

    @Override
    public String toString() {
        return "StudentsTable{" +
                "id=" + id +
                ", stu_name='" + stu_name + '\'' +
                ", stu_standard='" + stu_standard + '\'' +
                '}';
    }
}
