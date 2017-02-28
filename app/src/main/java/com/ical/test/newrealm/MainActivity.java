package com.ical.test.newrealm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Button add, view, update, delete;
    EditText roll_no, name;
    TextView text;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise realm only once
        Realm.init(this);

        // To get the realm instance
        Realm realm = Realm.getDefaultInstance();

        // To write the realm db
        realm.beginTransaction();
        Student student = realm.createObject(Student.class);
        student.setRoll_no(20);
        student.setName("neeraj mishra");

        realm.commitTransaction();


        RealmResults<Student> results = realm.where(Student.class).findAll();

        for(Student student1 : results){
            Log.d("just", "onCreate: "+student1.getRoll_no() + " " + student1.getName());
        }

        //Update the db
        RealmResults<Student> result = realm.where(Student.class).equalTo("roll_no", 20).findAll();

        realm.beginTransaction();

        for(Student student2 : result){
            student.setName("neeraj mishra thukali");
        }

        realm.commitTransaction();



    }
}
