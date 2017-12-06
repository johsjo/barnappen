package com.example.johansjolander.barnappen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.johansjolander.barnappen.model.Child;

public class SetupChildActivity extends AppCompatActivity {

    public static final String EXTRA_CHILD_BACK = "child_extra_back";


    private Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        //TODO FIX INTENT ==> NOTHING HAPPENS WHEN PUSH BUTTON
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        final EditText idEditText = (EditText) findViewById(R.id.edit_age);
        final EditText nameEditText = (EditText) findViewById(R.id.edit_name);
        final EditText ageEditText = (EditText) findViewById(R.id.edit_age);

        final Button button = (Button) findViewById(R.id.btn_edit_commit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {


                final long id = Long.parseLong(idEditText.getText().toString());
                final String name = nameEditText.getText().toString();
                final int age = Integer.parseInt(ageEditText.getText().toString());

                final Child newChild = SetupChildActivity.this.child == null ?
                        new Child(id, name, age) :
                        new Child(child.getId(), name, age);

                Intent intent = new Intent();
                      intent.putExtra(EXTRA_CHILD_BACK, newChild);
              setResult(RESULT_OK, intent);
               finish();



           }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Intent createIntent(Context context){
        return new Intent(context, SetupChildActivity.class);
    }
}
