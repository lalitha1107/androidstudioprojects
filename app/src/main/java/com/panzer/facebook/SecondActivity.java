package com.panzer.facebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.panzer.application.R;

public class SecondActivity extends AppCompatActivity {

    String type,facebook_id,fname,mname,lname,fullname,profileimage,emailid,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        type=getIntent().getStringExtra("type");
        facebook_id=getIntent().getStringExtra("facebook_id");
        fname=getIntent().getStringExtra("f_name");
        mname=getIntent().getStringExtra("m_name");
        lname=getIntent().getStringExtra("l_name");
        fullname=getIntent().getStringExtra("full_name");
        profileimage=getIntent().getStringExtra("profile_image");
        emailid=getIntent().getStringExtra("email_id");
        gender=getIntent().getStringExtra("gender");
        Toast.makeText(getApplicationContext(),type+facebook_id+fname+mname+lname+fullname+profileimage+emailid+gender,Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
