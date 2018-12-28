package com.example.codemaker.sample;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlanaActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    TextView textViewMsg;
    Button buttonLogin, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alana);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewMsg = (TextView) findViewById(R.id.txtMsg);
        buttonLogin = (Button) findViewById(R.id.button);
        btnCall = (Button) findViewById(R.id.btnCall);

        SharedPreferences shared = getSharedPreferences("com.example.codemaker.sample.SharedPreferences", MODE_PRIVATE);
        String value = "Username: " + (shared.getString("username", "")) + " and Password: " + (shared.getString("password", ""));
        textViewMsg.setText(value);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password;
                username = editTextUsername.getText().toString();
                password = editTextPassword.getText().toString();
                /*if(password.equals("123")){
                    Toast.makeText(AlanaActivity.this, "Login Sucess", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AlanaActivity.this , MainActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }else{
                    Toast.makeText(AlanaActivity.this, "enter a valid username or password", Toast.LENGTH_SHORT).show();
                }*/

                SharedPreferences shared = getSharedPreferences("com.example.codemaker.sample.SharedPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.commit();// commit is important here.
                Toast.makeText(AlanaActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                //SharedPreferences shared = getSharedPreferences("com.example.codemaker.sample.SharedPreferences", MODE_PRIVATE);
                String value = "Username: " + (shared.getString("username", "")) + " and Password: " + (shared.getString("password", ""));
                textViewMsg.setText(value);

                //Toast.makeText(AlanaActivity.this, "Username: " + username + " Password: " + password , Toast.LENGTH_SHORT).show();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:8606594103"));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Paused", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Resumed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Destroyed", Toast.LENGTH_SHORT).show();
    }
}
