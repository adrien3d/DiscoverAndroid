package com.iotech.discover;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import api.ApiService;
import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_login)
    Button _loginButton;
    @InjectView(R.id.link_signup)
    TextView _signupLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setBackgroundDrawableResource(R.drawable.mlogin_paris_aerian);
        ButterKnife.inject(this);

        if (!isInternetConnected()) {
            alertDialogNoInternet();
        }

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()/* || !isConnected()*/) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

       /* final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.login_authentication));
        progressDialog.show();*/

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO : onLoginSuccess si auth ok côté serveur
        Context context = getApplicationContext();
        loginAPI(context, email, password);
/*
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 500);*/
    }

    public boolean isInternetConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), R.string.login_failed, Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

/*        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
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
        }*/
        onLoginSuccess();
        return valid;
    }

    public void alertDialogNoInternet (){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(getString(R.string.nointernet_title));
        builder1.setMessage(getString(R.string.nointernet_message));
        builder1.setCancelable(true);
        builder1.setPositiveButton(getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),
                                "Merci", Toast.LENGTH_SHORT)
                                .show();
                        //dialog.cancel();
                    }
                });
        builder1.setNegativeButton(getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void loginAPI (Context context, final String mail, final String password) {
       /* JSONObject obj = new JSONObject();
        try {
            obj.put("email", mail);
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

        apiService.authentication(mail, password, new Callback<String>() {
            @Override
            public void success(String token, Response response) {
                //loginOk
                SharedPreferences.Editor editor = getSharedPreferences("pref", MODE_PRIVATE).edit();
                editor.putString("token", token);
                editor.commit();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(LoginActivity.this, "Erreur de connexion : " + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
