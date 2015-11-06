package com.iotech.discover;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Api.ApiService;
import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @InjectView(R.id.input_name)
    EditText _firstNameText;
    @InjectView(R.id.input_lastname)
    EditText _lastNameText;
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.input_address)
    EditText _addressText;
    @InjectView(R.id.input_birthdate)
    EditText _birthdateText;
    @InjectView(R.id.input_sex)
    EditText _sexText;
    @InjectView(R.id.input_telNumber)
    EditText _telNumberText;

    @InjectView(R.id.btn_signup)
    Button _signupButton;
    @InjectView(R.id.link_login)
    TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setBackgroundDrawableResource(R.drawable.mlogin_paris_eiffel);
        ButterKnife.inject(this);
        addCalendar();
        addSex();

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.register_creating_account));
        progressDialog.show();

        String firstName = _firstNameText.getText().toString();
        String lastName = _lastNameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String address = _addressText.getText().toString();
        String birthdate = _birthdateText.getText().toString();

        // TODO: onSignupSuccess() si l'enregistrement est ok côté serveur
        Context context = getApplicationContext();
        //registerAPI(context, firstName, lastName, email, address, birthdate, sex, telNumber, password);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 500);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), R.string.register_failed, Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String firstName = _firstNameText.getText().toString();
        String lastName = _lastNameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String telNumber = _telNumberText.getText().toString();
        String address = _addressText.getText().toString();
        String birthdate = _birthdateText.getText().toString();

        if (firstName.isEmpty() || firstName.length() < 3) {
            _firstNameText.setError(getString(R.string.input_at_least_3_chars));
            valid = false;
        } else {
            _firstNameText.setError(null);
        }

        if (lastName.isEmpty() || lastName.length() < 3) {
            _lastNameText.setError(getString(R.string.input_at_least_3_chars));
            valid = false;
        } else {
            _lastNameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError(getString(R.string.input_invalid_mail));
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError(getString(R.string.input_at_least_4_chars));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (telNumber.length() != 10) {
            _telNumberText.setError(getString(R.string.input_tel_10_digits));
            valid = false;
        } else {
            _telNumberText.setError(null);
        }

        return valid;
    }

    public void registerAPI (Context context, final String firstName, final String lastName, final String mail, final String address, final String birthdate, final String sex, final String telNumber, final String password) {
        /*JSONObject obj = new JSONObject();
        try {
            obj.put("first_name", firstName);
            obj.put("last_name", lastName);
            obj.put("email", mail);
            obj.put("address", address);
            obj.put("birthdate", birthdate);
            obj.put("sex", sex);
            obj.put("telephone_number", telNumber);
            obj.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, "http://46.101.218.111/api/v1/auth",obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        //hideProgressDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //hideProgressDialog();
                        System.out.println(error);
                    }
                });
        queue.add(jsObjRequest);*/

        ApiService apiService = new RestAdapter.Builder().setEndpoint(ApiService.ENDPOINT).build().create(ApiService.class);

        apiService.registration(firstName, lastName, mail, address, birthdate, sex, telNumber, password, new Callback<String>() {
            @Override
            public void success(String token, Response response) {
                //register ok
                SharedPreferences.Editor editor = getSharedPreferences("pref", MODE_PRIVATE).edit();
                editor.putString("token", token);
                editor.commit();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(SignupActivity.this, "Erreur de connexion : " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addCalendar(){
        //todo : don't display keyboard
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

                _birthdateText.setText(sdf.format(myCalendar.getTime()));
            }

        };

        _birthdateText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DatePickerDialog dpd = new DatePickerDialog(SignupActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                myCalendar.setTime(new Date());
                myCalendar.add(Calendar.YEAR, -16);
                dpd.getDatePicker().setMaxDate(myCalendar.getTime().getTime());
                dpd.setTitle(getString(R.string.birthdate_picker_title));
                if (event.getAction() == MotionEvent.ACTION_DOWN) dpd.show();
                return false;
            }
        });
    }

    public void addSex () {
        final AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("Votre sexe").setItems(R.array.sex_array,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog,int which){
                        String[] sexArray =  getResources().getStringArray(R.array.sex_array);
                        _sexText.setText(sexArray[which]);
                    }
                }
        );

        _sexText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                AlertDialog dialog = builder.create();
                if (event.getAction() == MotionEvent.ACTION_DOWN) dialog.show();
                return false;
            }
        });
    }
}