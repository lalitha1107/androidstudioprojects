package com.panzer.facebook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.panzer.application.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    //For facebook
    private CallbackManager callbackManager;

    private LinearLayout facebook_button;
    ProgressDialog progress;
    private String facebook_id,f_name, m_name, l_name, gender, profile_image, full_name, email_id;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getSupportActionBar().hide();
        }catch (NullPointerException e){

        }
        setContentView(R.layout.facebook_activity);

        facebook_button=(LinearLayout)findViewById(R.id.btnSignUpFacebook);

        progress=new ProgressDialog(MainActivity.this);
        progress.setMessage("Please wait");
        progress.setIndeterminate(false);
        progress.setCancelable(false);

        facebook_id=f_name= m_name= l_name= gender= profile_image= full_name= email_id="";

        //for facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        //register callback object for facebook result
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                progress.show();

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                String email = object.optString("email");
                                String uid = object.optString("id");
                                String gender=object.optString("gender");
                                //loginPresenter.loginBySocial(email, uid);


                                Profile profile = Profile.getCurrentProfile();

                                if (profile != null) {
                                    facebook_id=profile.getId();
                                    f_name=profile.getFirstName();
                                    m_name=profile.getMiddleName();
                                    l_name=profile.getLastName();
                                    full_name=profile.getName();


                                    profile_image=profile.getProfilePictureUri(400, 400).toString();
                                }


                                Intent i=new Intent(MainActivity.this, SecondActivity.class);
                                i.putExtra("type","facebook");
                                i.putExtra("facebook_id",uid);
                                i.putExtra("f_name",f_name);
                                i.putExtra("m_name",m_name);
                                i.putExtra("l_name",l_name);
                                i.putExtra("full_name",full_name);
                                i.putExtra("profile_image",profile_image);
                                i.putExtra("email_id",email);
                                i.putExtra("gender",gender);

                                progress.dismiss();
                                startActivity(i);
                                finish();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email");
                request.setParameters(parameters);
                request.executeAsync();














             /*  Profile profile = Profile.getCurrentProfile();

                if (profile != null) {
                    facebook_id=profile.getId();
                    f_name=profile.getFirstName();
                    m_name=profile.getMiddleName();
                    l_name=profile.getLastName();
                    full_name=profile.getName();


                    profile_image=profile.getProfilePictureUri(400, 400).toString();
                }
                //Toast.makeText(FacebookLogin.this,"Wait...",Toast.LENGTH_SHORT).show();
                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    email_id=object.getString("email");
                                    gender=object.getString("gender");
                                    //Start new activity or use this info in your project.
                                    Intent i=new Intent(MainActivity.this, SecondActivity.class);
                                    i.putExtra("type","facebook");
                                    i.putExtra("facebook_id",facebook_id);
                                    i.putExtra("f_name",f_name);
                                    i.putExtra("m_name",m_name);
                                    i.putExtra("l_name",l_name);
                                    i.putExtra("full_name",full_name);
                                    i.putExtra("profile_image",profile_image);
                                    i.putExtra("email_id",email_id);
                                    i.putExtra("gender",gender);

                                    progress.dismiss();
                                    startActivity(i);
                                    finish();
                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                     e.printStackTrace();
                                }

                            }

                        });

                request.executeAsync();*/
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "login_canceled_facebooklogin", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this,"login_failed_facebooklogin",Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

        //facebook button click
        facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile", "user_friends", "email"));
            }
        });
    }

    //for facebook callback result.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}


































/*
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
*/


