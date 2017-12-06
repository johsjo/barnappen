package com.example.johansjolander.barnappen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.johansjolander.barnappen.fragment.ChildFragment;
import com.example.johansjolander.barnappen.model.Child;
import com.example.johansjolander.barnappen.repository.inmemory.InMemoryChildRepository;
import com.example.johansjolander.barnappen.repository.impl.ChildRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ChildFragment.Callbacks {

    private static final int REQUEST_CODE_EDIT_CHILD = 1;
    private static final int REQUEST_CODE_EDIT_PREGNANCY = 2 ;
    private static final int REQUEST_CODE_EDIT_PROFILE = 3;
    private RecyclerView recyclerView;
    private List<String> stringsChild;
    //private static FragmentManager fm;
    private ChildFragment childFragment;

    private ChildRepository childRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        childRepository = new InMemoryChildRepository();

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.main_child_container);

        if (fragment == null) {
            fragment = ChildFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.main_child_container, fragment)
                    .commit();
        }

        childFragment = (ChildFragment) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_child:
                Intent intentChild = SetupChildActivity.createIntent(this);
                startActivityForResult(intentChild, REQUEST_CODE_EDIT_CHILD);
                return true;

            case R.id.action_add_pregnancy:
                Intent intentPregnancy = SetupPregnancyActivity.createIntent(this);
                startActivityForResult(intentPregnancy, REQUEST_CODE_EDIT_PREGNANCY);
                return true;

            case R.id.action_profile:
                Intent intentProfile = SetupProfileActivity.createIntent(this);
                startActivityForResult(intentProfile, REQUEST_CODE_EDIT_PROFILE);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "funka onActivityResult?", Toast.LENGTH_LONG).show();
        System.out.println("KOM IGEN NU!!\n" + childRepository.getAllChildren());

        //TODO UpdateDataSet here


        if (requestCode == REQUEST_CODE_EDIT_CHILD) {
            if (resultCode == RESULT_OK) {
                Child child = data.getParcelableExtra(SetupChildActivity.EXTRA_CHILD_BACK);
                childRepository.addChild(child);

                childFragment.updateDataSet(childRepository);

                Toast.makeText(this, "funka?", Toast.LENGTH_LONG).show();
                //updateAdapter();  //TODO UPDATE CHILD_FRAGMENT
            }
        }
    }

    @Override
    public void onListItemClicked(Child child) {
        Intent intent = ChildActivity.createIntent(MainActivity.this, child);
        startActivity(intent);

    }
}

