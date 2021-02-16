package com.example.vrajpatel.way2cis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginActivity extends AppCompatActivity {
    CircularProgressButton log;
    Button but;
    public static EditText u_name;
    public static EditText pass;
    String data="";
    String username,password;
    public static final String PREFS_NAME="Preference";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        but=(Button)findViewById(R.id.learn);
        u_name=(EditText)findViewById(R.id.username);
        u_name.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        pass=(EditText)findViewById(R.id.password);
        pass.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        log=(CircularProgressButton)findViewById(R.id.log);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String us = sharedPreferences.getString("USERNAME","");
        String pa = sharedPreferences.getString("PASSWORD","");
        u_name.setText(us);
        pass.setText(pa);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://way2cis.com/"));
                startActivity(intent);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=u_name.getText().toString();
                password=pass.getText().toString();
                Log.v("us",username);
                Log.v("pa",password);
                log.startAnimation();
                new login().execute(username,password);
            }
        });
    }
    public class login extends AsyncTask<String,Void,String> {
        String par1,par2;

        @Override
        protected void onPostExecute(String s) {
            /*String name= String.valueOf(u_name.getText());
            String pass1= String.valueOf(pass.getText());
            String name1="system";
            String pass2="system";
            */
            if (s == null) {
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray user = jsonObject.getJSONArray("item");

                for (int i=0;i<user.length();i++) {
                    JSONObject j2 = user.getJSONObject(i);
                    String result = j2.get("p1").toString();
                    if (result.equals("1"))
                    {
                        Log.v("hmm","Inside Edge");
                        Log.v("hmm1",par1);
                        Log.v("hmm2",par2);
                        SharedPreferences preferences = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("USERNAME",par1);
                        editor.putString("PASSWORD",par2);
                        editor.commit();
                        Toast.makeText(LoginActivity.this,"login successful",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        Animatoo.animateZoom(LoginActivity.this);
                        u_name.setText("");
                        pass.setText("");
                        log.revertAnimation();
                    }
                    else {
                        Log.v("hmm55","Inside Edge");
                        Log.v("hmm23",par1);
                        Log.v("hmm236",par2);
                        Toast.makeText(LoginActivity.this,"login Unsuccessful",Toast.LENGTH_LONG).show();
                        log.revertAnimation();
                        final AlertDialog.Builder mBuilder=new AlertDialog.Builder(LoginActivity.this);
                        final View mView=getLayoutInflater().inflate(R.layout.login_error_popup,null);
                        Button try1=(Button) mView.findViewById(R.id.try_again);
                        Button close1=(Button) mView.findViewById(R.id.close);
                        mBuilder.setView(mView);
                        final AlertDialog dialog=mBuilder.create();
                        dialog.show();
                        try1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                u_name.setText("");
                                pass.setText("");
                            }
                        });
                        close1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                    }
                }
            }
            catch (Exception e) {
                e.getMessage();
            }
        }

        @Override
        protected String doInBackground(String... strings) {

            par1=strings[0].replace(" ","%20");
            par2=strings[1].replace(" ","%20");
            Log.v("hello",par1);
            Log.v("hello2",par2);
            String buffer="";
            String temp="";
            try {
                Thread.sleep(300);
                String u = "https://flexile-collision.000webhostapp.com/connect/login.php?"+"p1="+par1+"&p2="+par2;
                URL url = new URL(u);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                Log.v("hmm6616","Inside Edge");
                Log.v("hmm266",par1);
                Log.v("hmm1566",par2);

                InputStream inputStream = connection.getInputStream();
                if (inputStream == null) {
                    return temp;
                }
                else {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line="";

                    while ((line = reader.readLine()) != null) {
                        buffer += line;
                    }
                    return buffer;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return buffer;
        }
    }
}
