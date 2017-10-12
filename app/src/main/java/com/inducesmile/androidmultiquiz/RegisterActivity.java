package com.inducesmile.androidmultiquiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inducesmile.androidmultiquiz.database.DBHandler;
import com.inducesmile.androidmultiquiz.database.DatabaseQuery;
import com.inducesmile.androidmultiquiz.entities.Client;
import com.inducesmile.androidmultiquiz.helper.MySharedPreference;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText emailET;
    private String name;
    private String email;
    private MySharedPreference sharedPreference;
    private DBHandler dbh;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.awadsdzx));
        dbh = new DBHandler(RegisterActivity.this);
        sharedPreference = new MySharedPreference(RegisterActivity.this);



        Button register = (Button) findViewById(R.id.register_button);
        assert register != null;
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameET = (EditText) findViewById(R.id.name);
                emailET = (EditText) findViewById(R.id.email);
                name = nameET.getText().toString();
                email = emailET.getText().toString();


                    /*	Another test case:
                        public static boolean isValidEmailId(String email) {
                        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
                        Pattern p = Pattern.compile(emailPattern);
                        Matcher m = p.matcher(email);
                        return m.matches();
                    }
                 /*   public static boolean isValidEmailAddress(String email) {
                        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
                        java.util.regex.Matcher m = p.matcher(email);
                        return m.matches();
                    Toast.makeText(RegisterActivity.this, "\r\nYour comments will be appreciated.", Toast.LENGTH_LONG).show(); }*/
                if (name.matches("")) {
                    System.out.println("Entered e-mail address is Valid.");
                } else {
                    Toast.makeText(RegisterActivity.this, "Entered e-mail address is Invalid.", Toast.LENGTH_LONG).show();

                }
                if (name.matches("") || email.matches("")) {
                    Toast.makeText(RegisterActivity.this, "You must enter both fields.", Toast.LENGTH_LONG).show();

                }


              else {
                    client = new Client(name, email);
                    dbh.addClient(client);
                    sharedPreference.setSessionState(true);
                    Intent quizMenuIntent = new Intent(RegisterActivity.this, QuizMenuActivity.class);
                    startActivity(quizMenuIntent);
                }
            }
        });

        Button asGuest = (Button)findViewById(R.id.guest_button);
        assert asGuest != null;
        asGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizMenuIntent = new Intent(RegisterActivity.this, QuizMenuActivity.class);
                startActivity(quizMenuIntent);
            }
        });
    }
}
