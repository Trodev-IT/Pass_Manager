package com.trodev.mypasswordgenerator.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.trodev.mypasswordgenerator.AdapterRecord;
import com.trodev.mypasswordgenerator.Constants;
import com.trodev.mypasswordgenerator.MyHelper;
import com.trodev.mypasswordgenerator.UploadNotesActivity;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOnlineAdapter;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOnlineModel;
import com.trodev.mypasswordgenerator.R;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    FloatingActionButton addRecord;
    RecyclerView recordRv;
    MyHelper dbHelper;

    //sort options
    String orderByNewest = Constants.C_ADDED_TIMESTAMP + " DESC";
    String orderByOldest = Constants.C_ADDED_TIMESTAMP + " ASC";
    String orderByTitleAse = Constants.C_NAME + " ASC";
    String orderByTitleDesc = Constants.C_NAME + " DESC";

    //if refreshing record, refresh with last chosen sort options
    String currentOrderByStatus = orderByNewest;

    public NotesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_locker, container, false);

        addRecord = view.findViewById(R.id.addRecord);

        addRecord = view.findViewById(R.id.addRecord);
        recordRv = view.findViewById(R.id.recordRv);

        //init db
        dbHelper = new MyHelper(getContext());

        //load records
        loadREcords(orderByNewest);

        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), UploadNotesActivity.class);
                intent.putExtra("isEditMode", false);
                startActivity(intent);

            }
        });

        return view;
    }

    public MenuInflater getMenuInflater() {
        return new MenuInflater(getContext());
    }

    private void loadREcords(String orderBy) {
        currentOrderByStatus = orderBy;
        AdapterRecord adapterRecord = new AdapterRecord(getContext(), dbHelper.getAllRecords(orderBy));
        recordRv.setAdapter(adapterRecord);
    }

    private void searchRecords(String query) {

        AdapterRecord adapterRecord = new AdapterRecord(getContext(), dbHelper.searchAllRecords(query));
        recordRv.setAdapter(adapterRecord);
        // getSupportActionBar().setSubtitle("Total: " + dbHelper.getRecordsCount());
    }

    @Override
    public void onResume() {
        super.onResume();
        loadREcords(currentOrderByStatus);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //handle menu item
        int id = item.getItemId();

        if (id == R.id.action_sort) {
            sortOptionDialog();

        } else if (id == R.id.action_delete) {

            // delete all records
            dbHelper.deleteAllData();
            onResume();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortOptionDialog() {

        //options to display in dialog
        String[] options = {"Title Ascending", "Title Descending", "Newest", "Oldest"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Sort By")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //handle options click
                        if (which == 0) {
                            loadREcords(orderByTitleAse);
                        } else if (which == 1) {
                            loadREcords(orderByTitleDesc);
                        } else if (which == 2) {
                            loadREcords(orderByNewest);
                        } else if (which == 3) {
                            loadREcords(orderByOldest);
                        }
                    }
                })
                .create().show();
    }
}