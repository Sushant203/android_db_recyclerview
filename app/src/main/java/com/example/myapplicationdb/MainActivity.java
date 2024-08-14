package com.example.myapplicationdb;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName, editRollno, editAddress, editId;
    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = findViewById(R.id.editTextName);
        editRollno = findViewById(R.id.editTextRollNo);
        editAddress = findViewById(R.id.editTextAddress);
        editId = findViewById(R.id.editTextId);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(studentAdapter);

        viewAll();
    }

    public void addData(View view) {
        boolean isInserted = myDb.insertData(editName.getText().toString(),
                editRollno.getText().toString(),
                editAddress.getText().toString());

        if (isInserted) {
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
            viewAll();
        } else {
            Toast.makeText(this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void updateData(View view) {
        boolean isUpdate = myDb.updateData(editId.getText().toString(),
                editName.getText().toString(),
                editRollno.getText().toString(),
                editAddress.getText().toString());

        if (isUpdate) {
            Toast.makeText(this, "Data Updated", Toast.LENGTH_LONG).show();
            viewAll();
        } else {
            Toast.makeText(this, "Data not Updated", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteData(View view) {
        Integer deletedRows = myDb.deleteData(editId.getText().toString());

        if (deletedRows > 0) {
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_LONG).show();
            viewAll();
        } else {
            Toast.makeText(this, "Data not Deleted", Toast.LENGTH_LONG).show();
        }
    }

    public void viewAll() {
        Cursor res = myDb.getAllData();
        studentList.clear();

        if (res.getCount() == 0) {
            return;
        }

        while (res.moveToNext()) {
            studentList.add(new Student(res.getString(0), res.getString(1), res.getString(2), res.getString(3)));
        }
        studentAdapter.notifyDataSetChanged();
    }
}
