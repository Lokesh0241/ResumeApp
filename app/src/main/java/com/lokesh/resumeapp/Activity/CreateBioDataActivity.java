package com.lokesh.resumeapp.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lokesh.resumeapp.Model.CreateBioModel;
import com.lokesh.resumeapp.MySingleton;
import com.lokesh.resumeapp.R;
import com.lokesh.resumeapp.State;
import com.lokesh.resumeapp.StateAdapter;
import com.lokesh.resumeapp.Support.RangeTimePickerDialog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreateBioDataActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String KEY_STATE = "state";
    private static final String KEY_CITIES = "cities";

    private ProgressDialog pDialog;
    private static final String TAG = CreateBioDataActivity.class.getSimpleName();

    CircleImageView profile_image;

    EditText etName;
    EditText etMobile;
    EditText etFatherName;
    EditText etFatherOccupation;
    EditText etMotherName;
    EditText etMotherOcp;
    EditText etDob;
    EditText etTob;
    EditText etBrother;
    EditText etSister;
    EditText etWeight;
    EditText etHeight;
    EditText etGotra;
    EditText etMamaGotra;
    EditText etComplexion;
    EditText etChildren;
    EditText etEducation;
    EditText etOccuption;
    EditText etAddress;
    EditText etDescription;

    Spinner stateSpinner;
    Spinner citiesSpinner;

    String stateStr = "";
    String cityStr = "";

    ImageView ivBack;

    RadioButton yes;
    RadioButton no;
    RadioButton male;
    RadioButton female;
    RadioButton single;
    RadioButton widowed;
    RadioButton divorced;
    RadioButton married;

    Button cvSave;

    String Married = "";
    String Sex = "";
    String Manglic = "";
    String selectedHoursStr;
    String selectedMintStr;
    String stringImages = "";


    Calendar c;
    DatePickerDialog dpDialog;
    Bitmap photo;
    Bitmap tempPhoto;
    byte[] imageBytes;

    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_EXTERNAL_STORAGE = 2;

    int from_year, from_month, from_day;
    DatePickerDialog.OnDateSetListener from_dateListener;

    private DatabaseReference mFirebaseDatabase;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bio_data);

        init();
    }

    private void init() {
        try {

            FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
            //    mFirebaseDatabase1 = mFirebaseInstance.getReference("states");
            // get reference to 'users' node
            mFirebaseDatabase = mFirebaseInstance.getReference("users");

            cvSave = findViewById(R.id.cvSave);
            profile_image = findViewById(R.id.profile_image);
            etName = findViewById(R.id.etName);
            etMobile = findViewById(R.id.etMobile);
            etFatherName = findViewById(R.id.etFatherName);
            etFatherOccupation = findViewById(R.id.etFatherOccupation);
            etMotherName = findViewById(R.id.etMotherName);
            etMotherOcp = findViewById(R.id.etMotherOcp);
            etDob = findViewById(R.id.etDob);
            etBrother = findViewById(R.id.etBrother);
            etSister = findViewById(R.id.etSister);
            etWeight = findViewById(R.id.etWeight);
            etHeight = findViewById(R.id.etHeight);
            etGotra = findViewById(R.id.etGotra);
            etMamaGotra = findViewById(R.id.etMamaGotra);
            etComplexion = findViewById(R.id.etComplexion);
            etChildren = findViewById(R.id.etChildren);
            etEducation = findViewById(R.id.etEducation);
            etOccuption = findViewById(R.id.etOccuption);
            etAddress = findViewById(R.id.etAddress);
            etTob = findViewById(R.id.etTob);
            stateSpinner = findViewById(R.id.stateSpinner);
            citiesSpinner = findViewById(R.id.citiesSpinner);
            yes = findViewById(R.id.yes);
            no = findViewById(R.id.no);
            male = findViewById(R.id.male);
            female = findViewById(R.id.female);
            single = findViewById(R.id.single);
            widowed = findViewById(R.id.widowed);
            divorced = findViewById(R.id.divorced);
            etDescription = findViewById(R.id.etDescription);
            ivBack = (ImageView) findViewById(R.id.ivBack);
            married = (RadioButton)findViewById(R.id.married);

            displayLoader();
            loadStateCityDetails();

            etDob.setOnClickListener(this);
            getCurrentDate();
            setFrom_dateListener();

            ivBack.setOnClickListener(this);
            etTob.setOnClickListener(this);
            profile_image.setImageBitmap(photo);
            profile_image.setOnClickListener(this);
            cvSave.setOnClickListener(this);

            citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    cityStr = citiesSpinner.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getCurrentDate() {
        c = Calendar.getInstance();
        from_year = c.get(Calendar.YEAR);
        from_month = c.get(Calendar.MONTH);
        from_day = c.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                dpDialog = new DatePickerDialog(CreateBioDataActivity.this, from_dateListener, from_year, from_month, from_day);
                dpDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                return dpDialog;
        }
        return null;
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.cvSave:
                isValidValues();
                break;
            case R.id.profile_image:
                selectImage();
                break;
            case R.id.etDob:
                showDialog(0);
                break;
            case R.id.etTob:
                setEtTob(etTob);
                break;
            case R.id.ivBack:
                finish();
                break;

        }

    }


    public void setEtTob(final EditText time) {
        final Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        final RangeTimePickerDialog mTimePicker;

        mTimePicker = new RangeTimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                selectedHoursStr = String.valueOf(selectedHour > 12 ? (selectedHour - 12) : selectedHour);
                selectedMintStr = String.valueOf(selectedMinute);

                if (selectedHoursStr.length() == 1) {

                    selectedHoursStr = "0" + selectedHoursStr;
                }
                if (selectedMintStr.length() == 1) {

                    selectedMintStr = "0" + selectedMintStr;
                }

                String hourStr = String.valueOf(selectedHoursStr);
                String minStr = String.valueOf(selectedHour);

                if (hourStr.length() == 0) {
                    hourStr.concat("0" + hourStr);
                }

                // hours.setText(hourStr+"");
                // minutes.setText(selectedMinute+"");

                String AM_PM;

                if (selectedHour < 12) {
                    AM_PM = "AM";

                } else {

                    AM_PM = "PM";
                }


                time.setText(hourStr + " : " + minStr + " " + AM_PM);


            }
        }, hour, minute, true);//true = 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.setMin(hour, minute);
        mTimePicker.show();
    }

    public void setCvSave() {
        SharedPreferences shared = getSharedPreferences("login_detail", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = shared.edit();
        editor1.putString("ImageString", stringImages);
        editor1.apply();

        String pic = stringImages;
        String Name = etName.getText().toString().trim();
        String Mobile = etMobile.getText().toString().trim();
        String FatherName = etFatherName.getText().toString().trim();
        String FatherOccupation = etFatherOccupation.getText().toString().trim();
        String MotherName = etMotherName.getText().toString().trim();
        String MotherOcp = etMotherOcp.getText().toString().trim();
        String Dob = etDob.getText().toString().trim();
        String Tob = etTob.getText().toString().trim();
        String Brother = etBrother.getText().toString().trim();
        String Sister = etSister.getText().toString().trim();
        String Weight = etWeight.getText().toString().trim();
        String Height = etHeight.getText().toString().trim();
        String Gotra = etGotra.getText().toString().trim();
        String MamaGotra = etMamaGotra.getText().toString().trim();
        String Complexion = etComplexion.getText().toString().trim();
        String children = etChildren.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String Education = etEducation.getText().toString().trim();
        String Occuption = etOccuption.getText().toString().trim();
        String Address = etAddress.getText().toString().trim();

        if (TextUtils.isEmpty(userId)) {
            createUser(pic, Name,
                    Mobile, FatherName,
                    FatherOccupation, MotherName,
                    MotherOcp, Dob,
                    Tob, Brother,
                    Sister, Weight,
                    Height,
                    Gotra,
                    MamaGotra, Complexion,
                    Manglic, Sex, Married,
                    Education, Occuption,
                    Address,
                    stateStr, cityStr, children, description);
        } else {
            updateUser(pic, Name,
                    Mobile, FatherName,
                    FatherOccupation, MotherName,
                    MotherOcp, Dob,
                    Tob, Brother,
                    Sister, Weight,
                    Height, Gotra,
                    MamaGotra, Complexion,
                    Manglic, Sex, Married,
                    Education, Occuption,
                    Address,
                    stateStr, cityStr, children, description);
        }

        Intent intent = new Intent(CreateBioDataActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * Creating new user node under 'users'
     */
    private void createUser(String pic, String name,
                            String mobile, String fatherName,
                            String fatherOccupation, String motherName,
                            String motherOcp, String dob,
                            String tob, String brother,
                            String sister, String weight,
                            String height,
                            String gotra, String mamaGotra,
                            String complexion, String manglic,
                            String sex, String married,
                            String education, String occupation,
                            String address, String state,
                            String city, String children, String description) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth

        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        CreateBioModel createBioModel = new CreateBioModel(
                pic, name, mobile, fatherName, fatherOccupation, motherName, motherOcp, dob, tob, brother, sister, weight,
                height, gotra, mamaGotra, complexion, manglic, sex, married, education, occupation, address, state, city,
                children, description);

        mFirebaseDatabase.child(userId).setValue(createBioModel);
    }


    private void updateUser(String pic, String name,
                            String mobile, String fatherName,
                            String fatherOccupation,
                            String motherName, String motherOcp,
                            String dob, String tob,
                            String brother, String sister,
                            String weight, String height,
                            String gotra, String mamaGotra,
                            String complexion, String manglic,
                            String sex, String married,
                            String education, String occupation,
                            String address, String state,
                            String city, String children, String description) {

        // updating the user via child nodes
        if (!TextUtils.isEmpty(pic))
            mFirebaseDatabase.child(userId).child("pic").setValue(pic);

        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(mobile))
            mFirebaseDatabase.child(userId).child("mobile").setValue(mobile);

        if (!TextUtils.isEmpty(fatherName))
            mFirebaseDatabase.child(userId).child("fatherName").setValue(fatherName);

        if (!TextUtils.isEmpty(fatherOccupation))
            mFirebaseDatabase.child(userId).child("fatherOccupation").setValue(fatherOccupation);

        if (!TextUtils.isEmpty(motherName))
            mFirebaseDatabase.child(userId).child("motherName").setValue(motherName);

        if (!TextUtils.isEmpty(motherOcp))
            mFirebaseDatabase.child(userId).child("motherOcp").setValue(motherOcp);

        if (!TextUtils.isEmpty(dob))
            mFirebaseDatabase.child(userId).child("dob").setValue(dob);

        if (!TextUtils.isEmpty(tob))
            mFirebaseDatabase.child(userId).child("tob").setValue(tob);

        if (!TextUtils.isEmpty(brother))
            mFirebaseDatabase.child(userId).child("brother").setValue(brother);

        if (!TextUtils.isEmpty(sister))
            mFirebaseDatabase.child(userId).child("sister").setValue(sister);

        if (!TextUtils.isEmpty(weight))
            mFirebaseDatabase.child(userId).child("weight").setValue(weight);

        if (!TextUtils.isEmpty(height))
            mFirebaseDatabase.child(userId).child("height").setValue(height);


        if (!TextUtils.isEmpty(gotra))
            mFirebaseDatabase.child(userId).child("gotra").setValue(gotra);

        if (!TextUtils.isEmpty(mamaGotra))
            mFirebaseDatabase.child(userId).child("mamaGotra").setValue(mamaGotra);

        if (!TextUtils.isEmpty(complexion))
            mFirebaseDatabase.child(userId).child("complexion").setValue(complexion);

        if (!TextUtils.isEmpty(manglic))
            mFirebaseDatabase.child(userId).child("manglic").setValue(manglic);

        if (!TextUtils.isEmpty(sex))
            mFirebaseDatabase.child(userId).child("sex").setValue(sex);

        if (!TextUtils.isEmpty(married))
            mFirebaseDatabase.child(userId).child("married").setValue(married);

        if (!TextUtils.isEmpty(education))
            mFirebaseDatabase.child(userId).child("education").setValue(education);

        if (!TextUtils.isEmpty(occupation))
            mFirebaseDatabase.child(userId).child("occupation").setValue(occupation);

        if (!TextUtils.isEmpty(address))
            mFirebaseDatabase.child(userId).child("address").setValue(address);

        if (!TextUtils.isEmpty(state))
            mFirebaseDatabase.child(userId).child("state").setValue(state);

        if (!TextUtils.isEmpty(city))
            mFirebaseDatabase.child(userId).child("city").setValue(city);

        if (!TextUtils.isEmpty(state))
            mFirebaseDatabase.child(userId).child("child").setValue(children);

        if (!TextUtils.isEmpty(city))
            mFirebaseDatabase.child(userId).child("description").setValue(description);
    }


    public void imageConvertIntoString(Bitmap image) {
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 30, stream1);
        imageBytes = stream1.toByteArray();
        stringImages = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public Bitmap imageConvertIntoBitmap(String encodedImage) {
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    private boolean isValidValues() {
        if (etName.getText().toString().isEmpty()) {
            etName.setError("Please enter name");
            etName.requestFocus();
            return false;

        } else if (etMobile.getText().toString().trim().isEmpty() || etMobile.getText().toString().trim().length() < 10) {
            etMobile.setError("invalid mobile number");
            etMobile.requestFocus();
            return false;

        } else if (etFatherName.getText().toString().isEmpty()) {
            etFatherName.setError("Please enter father name");
            etFatherName.requestFocus();
            return false;

        } else if (etFatherOccupation.getText().toString().isEmpty()) {
            etFatherOccupation.setError("Please enter father occuption");
            etFatherOccupation.requestFocus();
            return false;

        } else if (etMotherName.getText().toString().isEmpty()) {
            etMotherName.setError("Please enter mother name");
            etMotherName.requestFocus();
            return false;

      /*  } else if (etMotherOcp.getText().toString().isEmpty()) {
            etMotherOcp.setError("Please enter mother occuption");
            etMotherOcp.requestFocus();
            return false;*/

        } else if (etDob.getText().toString().isEmpty()) {
            etDob.setError("Please enter date of birth");
            etDob.requestFocus();
            return false;

        } else if (etTob.getText().toString().isEmpty()) {
            etTob.setError("Please enter time of birth");
            etTob.requestFocus();
            return false;

        } else if (etBrother.getText().toString().isEmpty()) {
            etBrother.setError("Please enter brother name");
            etBrother.requestFocus();
            return false;

        } else if (etSister.getText().toString().isEmpty()) {
            etSister.setError("Please enter sister name");
            etSister.requestFocus();
            return false;

        } else if (etWeight.getText().toString().trim().isEmpty() || etWeight.getText().toString().trim().length() > 3) {
            etWeight.setError("invalid weight");
            etWeight.requestFocus();
            return false;

        } else if (etHeight.getText().toString().trim().isEmpty()) {
            etHeight.setError("Please enter height");
            etHeight.requestFocus();
            return false;

        } else if (etGotra.getText().toString().isEmpty()) {
            etGotra.setError("Please enter gotra");
            etGotra.requestFocus();
            return false;

        } else if (etMamaGotra.getText().toString().isEmpty()) {
            etMamaGotra.setError("Please enter mama gotra");
            etMamaGotra.requestFocus();
            return false;

        } else if (etComplexion.getText().toString().isEmpty()) {
            etComplexion.setError("Please enter complexion");
            etComplexion.requestFocus();
            return false;

        } else if (etChildren.getText().toString().isEmpty()) {
            etChildren.setError("Please enter children");
            etChildren.requestFocus();
            return false;

        } else if (Manglic.contentEquals("")) {
            Toast.makeText(this, "Please Select maglic type", Toast.LENGTH_SHORT).show();
            return false;

        } else if (Sex.contentEquals("")) {
            Toast.makeText(this, "Please Select sex type", Toast.LENGTH_SHORT).show();
            return false;

        } else if (Married.contentEquals("")) {
            Toast.makeText(this, "Please Select marriage type", Toast.LENGTH_SHORT).show();
            return false;

        } else if (etEducation.getText().toString().isEmpty()) {
            etEducation.setError("Please enter education");
            etEducation.requestFocus();
            return false;

        } else if (etOccuption.getText().toString().isEmpty()) {
            etOccuption.setError("Please enter occouption");
            etOccuption.requestFocus();
            return false;

        } else if (etAddress.getText().toString().isEmpty()) {
            etAddress.setError("Please enter address");
            etAddress.requestFocus();
            return false;

        } else if (stateStr.equals("")) {
            Toast.makeText(this, "Please select state", Toast.LENGTH_SHORT).show();
            return false;

        } else if (cityStr.equals("")) {
            Toast.makeText(this, "Please select city", Toast.LENGTH_SHORT).show();
            return false;

        } else if (etDescription.getText().toString().isEmpty()) {
            etDescription.setError("Please enter description");
            etDescription.requestFocus();
            return false;

        } else {
            setCvSave();
            return true;
        }
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {

                    dialog.dismiss();
                    if (ActivityCompat.checkSelfPermission(CreateBioDataActivity.this, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Check Permissions Now
                        // Callback onRequestPermissionsResult interceptado na Activity MainActivity
                        ActivityCompat.requestPermissions(CreateBioDataActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                REQUEST_CAMERA);
                    } else {

                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePictureIntent, REQUEST_CAMERA);
                    }

                } else if (options[item].equals("Choose from Gallery")) {

                    dialog.dismiss();

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQUEST_EXTERNAL_STORAGE);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            try {
                if (requestCode == 1) {

                    try {

                        tempPhoto = (Bitmap) data.getExtras().get("data");
                        profile_image.setImageBitmap(tempPhoto);
                        photo = tempPhoto;

                        imageConvertIntoString(photo);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (requestCode == 2) {

                    Uri selectedImage = data.getData();

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(CreateBioDataActivity.this.getContentResolver(), selectedImage);
                    Bitmap thumbnail = bitmap;//(BitmapFactory.decodeFile(picturePath));


                    Bitmap submitThumbnail = bitmap; //Bitmap.createScaledBitmap(imgBitmap, width, height, false);
                    thumbnail = Bitmap.createScaledBitmap(thumbnail, thumbnail.getWidth(), thumbnail.getHeight(), false);

                    profile_image.setImageBitmap(thumbnail);

                    photo = thumbnail;
                    imageConvertIntoString(photo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.yes:
                if (checked) {
                    Manglic = "Yes";
                }
                break;
            case R.id.no:
                if (checked) {
                    Manglic = "No";
                }
                break;

            case R.id.anshik:
                if (checked) {
                    Manglic = "Anshik";
                }
                break;

            case R.id.male:
                if (checked) {
                    Sex = "Male";
                }
                break;
            case R.id.female:
                if (checked) {
                    Sex = "Female";
                }
                break;
            case R.id.single:
                if (checked) {
                    Married = "Single";
                }
                break;
            case R.id.widowed:
                if (checked) {
                    Married = "Widowed";
                }
                break;
            case R.id.married:
                if (checked) {
                    Married = "Married";
                }
                break;
            case R.id.divorced:
                if (checked) {
                    Married = "Divorced";
                }
                break;
        }
    }


    public void setFrom_dateListener() {
        from_dateListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                from_year = year;
                from_month = monthOfYear;
                from_day = dayOfMonth;
                etDob.setText(new StringBuilder().append(from_month + 1).append("/").append(from_day).append("/").append(from_year));
                getCurrentDate();
            }
        };

    }

    private void displayLoader() {
        pDialog = new ProgressDialog(CreateBioDataActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void loadStateCityDetails() {

        final List<State> statesList = new ArrayList<>();
        final List<String> states = new ArrayList<>();

        String cities_url = "http://api.androiddeft.com/cities/cities_array.json";

        JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                (Request.Method.GET, cities_url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray responseArray) {
                        pDialog.dismiss();
                        try {
                            //Parse the JSON response array by iterating over it
                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                                String state = response.getString(KEY_STATE);
                                JSONArray cities = response.getJSONArray(KEY_CITIES);

                                List<String> citiesList = new ArrayList<>();

                                for (int j = 0; j < cities.length(); j++) {
                                    citiesList.add(cities.getString(j));
                                }

                                statesList.add(new State(state, citiesList));
                                states.add(state);

                            }

                            final StateAdapter stateAdapter = new StateAdapter(CreateBioDataActivity.this,
                                    R.layout.state_list, R.id.spinnerText, statesList);

                            stateSpinner.setAdapter(stateAdapter);

                            stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    stateStr = states.get(position);

                                    //Populate City list to the second spinner when
                                    // a state is chosen from the first spinner
                                    State cityDetails = stateAdapter.getItem(position);
                                    List<String> cityList;

                                    cityList = cityDetails.getCities();

                                    if (position == 26) {
                                        cityList.add("Jhalawar");
                                    }


                                    ArrayAdapter citiesAdapter = new ArrayAdapter<>(CreateBioDataActivity.this,
                                            R.layout.city_list, R.id.citySpinnerText, cityList);
                                    citiesSpinner.setAdapter(citiesAdapter);


                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }


}
