package com.trodev.mypasswordgenerator.offlinedatabase;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.trodev.mypasswordgenerator.R;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class UploadNotesActivity extends AppCompatActivity {

    CircleImageView profileIv;
    TextInputEditText nameEt, ticketEt, phoneEt, dateEt, bioEt;
    FloatingActionButton saveBtn;

    private static final int STORAGE_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int STORAGE_PICK_CODE = 102;
    private static final int CAMERA_PICK_CODE = 103;

    // arrays of permissions required to pick image from camera/gallery
    private String[] cameraPermission;
    private String[] storagePermission;

    private Uri imageUri = null;

    private String id, name, phone, ticket, date, bio, addedTime, updatedTime;
    private boolean isEditMode = false;
    //db helper
    private MyHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notes);

        getSupportActionBar().setTitle("Save Tickets");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //   profileIv = findViewById(R.id.profileIv);
        nameEt = findViewById(R.id.nameEt);
        ticketEt = findViewById(R.id.ticketEt);
        phoneEt = findViewById(R.id.phoneEt);
        dateEt = findViewById(R.id.dateEt);
        bioEt = findViewById(R.id.bioEt);
        saveBtn = findViewById(R.id.saveBtn);

        phoneEt.setText("");

        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());

        dateEt.setText(s);

        // get data from adapter
        isEditMode = getIntent().getBooleanExtra("isEditMode", false);

        //set data to views
        if(isEditMode)
        {
            getSupportActionBar().setTitle("Update notes");
            //update data
            id = getIntent().getStringExtra("ID");
            name = getIntent().getStringExtra("NAME");
            // phone = getIntent().getStringExtra("PHONE");
            ticket = getIntent().getStringExtra("TICKET");
            date = getIntent().getStringExtra("DATE");
            bio = getIntent().getStringExtra("BIO");
            addedTime = getIntent().getStringExtra("ADDED_TIME");
            updatedTime = getIntent().getStringExtra("UPDATED_TIME");

            //set data to views
            nameEt.setText(name);
            phoneEt.setText(phone);
            ticketEt.setText(ticket);
            dateEt.setText(date);
            bioEt.setText(bio);
        }
        else
        {
            //add data
            getSupportActionBar().setTitle("Add Ticket Information");
        }

        //init db
        dbHelper = new MyHelper(this);

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

/*        profileIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   imagePickDialog();

            }
        });*/

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputData();

            }
        });
    }

    private void inputData() {

        name = "" + nameEt.getText().toString().trim();
        //phone = "" + phoneEt.getText().toString().trim();
        date = "" + dateEt.getText().toString().trim();
        ticket = "" + ticketEt.getText().toString().trim();
        bio = "" + bioEt.getText().toString().trim();

        if(isEditMode)
        {
            //update data
            String timestamp = "" + System.currentTimeMillis();
            dbHelper.updateRecord(
                    ""+id,
                    ""+name,
                    ""+bio,
                    ""+phone,
                    ""+ticket,
                    ""+date,
                    ""+ timestamp,
                    ""+ timestamp
            );

            Toast.makeText(this, "Update successful", Toast.LENGTH_SHORT).show();

        }else
        {
            String timestamp = "" + System.currentTimeMillis(); //                 "" + imageUri,
            long id = dbHelper.insertRecord(
                    "" + name,
                    "" + bio,
                    "" + phone,
                    "" + ticket,
                    "" + date,
                    "" + timestamp,
                    "" + timestamp

            );

            Toast.makeText(this, "Notes added successful\nNotes serial: " + id, Toast.LENGTH_SHORT).show();
        }



    }
/*
    private void imagePickDialog() {

        String[] options = {"Camera", "Gallery"};

        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image From");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //handle clicks
                if (which == 0) {
                    if (!checkCameraPermission()) {
                        requestCameraPermission();
                    } else {
                        Toast.makeText(UploadTicketActivity.this, "Access Granted", Toast.LENGTH_SHORT).show();
                        pickFromCamera();
                    }
                } else if (which == 1) {

                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        Toast.makeText(UploadTicketActivity.this, "Access Denied", Toast.LENGTH_SHORT).show();
                        pickFromGallery();
                    }

                }

            }
        });

        //show builder
        builder.create().show();

    }

    private void pickFromCamera() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "TEMP IMAGE TITLE");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "TEMP IMAGE DESCRIPTION");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, CAMERA_PICK_CODE);

    }

    private void pickFromGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, STORAGE_PICK_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

*//*        if (requestCode == RESULT_OK) {
            //image picked
            if (requestCode == STORAGE_PICK_CODE) {
                //picked from gallery
                //picked from gallery

                imageUri = data.getData();
                profileIv.setImageURI(imageUri);

            }
            //image picked
            if (requestCode == CAMERA_PICK_CODE) {
                //picked from gallery

                imageUri = data.getData();
                profileIv.setImageURI(imageUri);

            }
        }*//*

        if (resultCode == RESULT_OK) {
            if (requestCode == STORAGE_PICK_CODE) {
                // Camera image
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Uri imageUri = getImageUriFromCamera(extras);
                    displayImage(imageUri);
                }
            } else if (requestCode == CAMERA_PICK_CODE) {
                // Gallery image
                Uri imageUri = data.getData();
                displayImage(imageUri);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private Uri getImageUriFromCamera(Bundle extras) {
        // Get the captured image from the extras
        return (Uri) extras.get(MediaStore.EXTRA_OUTPUT);
    }

    private void displayImage(Uri imageUri) {
        // Display the selected image in the ImageView
        profileIv.setImageURI(imageUri);
    }

    private boolean checkStoragePermission() {
        //Log.d(TAG, "checkStoragePermission: ");
        boolean result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return result;
    }

    private void requestStoragePermission() {
        //Log.d(TAG, "requestStoragePermission: ");
        requestPermissions(storagePermission, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        // Log.d(TAG, "checkCameraPermission: ");

        boolean cameraResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean storageResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return cameraResult && storageResult;

    }

    private void requestCameraPermission() {
        // Log.d(TAG, "requestCameraPermission: ");
        requestPermissions(cameraPermission, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted) {

                        pickFromCamera();

                        Toast.makeText(getApplicationContext(), "Camera Permission is enabled", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Camera & Storage Permission required", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "Cancelled...", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case STORAGE_REQUEST_CODE: {

                if (grantResults.length > 0) {

                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (storageAccepted) {

                        pickFromGallery();

                        Toast.makeText(getApplicationContext(), "Storage Permission is enabled", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Storage Permission is required...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Cancelled....", Toast.LENGTH_SHORT).show();
                }

            }
            break;

        }
    }*/


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}