package com.example.Practice_room_table.Helper;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.Practice_room_table.DataViewerActivity;
import com.example.Practice_room_table.Databases.DatabaseClient;
import com.example.Practice_room_table.Databases.StudentsTable;

import java.util.List;

public class DatabaseHelper {

    Context context;

    public DatabaseHelper(Context context){
        this.context = context;
    }

    public static DatabaseHelper getInstance(Context context){
        return new DatabaseHelper(context);
    }

    // Insert Data
    public void addNewStudent(String stu_name, String stu_standard){
        class NewStudent extends AsyncTask<Void, Void, StudentsTable>{
            @Override
            protected StudentsTable doInBackground(Void... voids) {
                StudentsTable studentsTable = new StudentsTable();
                studentsTable.setStu_name(stu_name);
                studentsTable.setStu_standard(stu_standard);

                DatabaseClient.getInstance(context)
                        .getStudentsDatabase()
                        .studentDAO()
                        .insertData(studentsTable);

                return studentsTable;
            }

            @Override
            protected void onPostExecute(StudentsTable studentsTable) {
                super.onPostExecute(studentsTable);
                if(studentsTable != null){
                    Toast.makeText(context, studentsTable.getStu_name() + "\n" + studentsTable.getStu_standard(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        NewStudent newStudent = new NewStudent();
        newStudent.execute();
    }

    // Show all data from StudentTable
    public void getAllStudentsData(){
        class AllStudents extends AsyncTask<Void, Void, List<StudentsTable>>{

            @Override
            protected List<StudentsTable> doInBackground(Void... voids) {
                List<StudentsTable> list = DatabaseClient.getInstance(context)
                        .getStudentsDatabase()
                        .studentDAO()
                        .selectAll();
                return list;
            }

            @Override
            protected void onPostExecute(List<StudentsTable> studentsTables) {
                super.onPostExecute(studentsTables);
                if(studentsTables != null && studentsTables.size() > 0){
                    ((DataViewerActivity)context).setRecyclerView(studentsTables);
                }
            }
        }

        AllStudents allStudents = new AllStudents();
        allStudents.execute();
    }

    // Update Data
    public void updateData(StudentsTable table, String stu_name, String stu_standard){
        class UpdateStudentData extends AsyncTask<Void, Void, StudentsTable>{

            @Override
            protected StudentsTable doInBackground(Void... voids) {
                table.setStu_name(stu_name);
                table.setStu_standard(stu_standard);

                DatabaseClient.getInstance(context)
                        .getStudentsDatabase()
                        .studentDAO()
                        .updateData(table);

                return table;
            }

            @Override
            protected void onPostExecute(StudentsTable studentsTable) {
                super.onPostExecute(studentsTable);
                if(table != null){
                    Toast.makeText(context, "Updated" + "\n" + table.getStu_name() + "\n" +
                            table.getStu_standard(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        UpdateStudentData updateStudentData = new UpdateStudentData();
        updateStudentData.execute();
    }

    //Delete Data
    public void deleteData(StudentsTable studentsTable){
        class DeleteData extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context)
                        .getStudentsDatabase()
                        .studentDAO()
                        .deleteData(studentsTable);

                return null;
            }
        }

        DeleteData deleteData = new DeleteData();
        deleteData.execute();
    }
}
