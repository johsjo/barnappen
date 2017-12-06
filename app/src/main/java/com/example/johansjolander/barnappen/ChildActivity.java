package com.example.johansjolander.barnappen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johansjolander.barnappen.model.Child;
import com.example.johansjolander.barnappen.repository.inmemory.InMemoryChildRepository;
import com.example.johansjolander.barnappen.repository.impl.ChildRepository;

import java.util.List;

public class ChildActivity extends AppCompatActivity {
    private static final String BUNDLE_ID_KEY = "child_id";

    private ChildRepository childRepository;
    private List<Child> childList;
    private Callbacks callback;
    private TextView tvChildName;

    public static Intent createIntent(Context context, Child child) {
        Intent intent = new Intent(context, ChildActivity.class);
        intent.putExtra(BUNDLE_ID_KEY, child.getId());
        return intent;
    }


    public interface Callbacks {
        void onListItemClicked(String child);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);


        //TODO FIX INTENT ==> NOTHING HAPPENS WHEN PUSH BUTTON
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



        childRepository = new InMemoryChildRepository();

        Intent startingIntent = getIntent();
        long id = startingIntent.getLongExtra(BUNDLE_ID_KEY, 0);

        Child child = childRepository.getChild(id);

        tvChildName = (TextView) findViewById(R.id.tv_child_name);

        tvChildName.setText(child.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.child_manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_delete:
                //TODO DELETE CHILD
                //TODO USE CALLBACK
                Toast.makeText(this, "Deleted!!", Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
