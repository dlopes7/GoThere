package com.gothere.gothere;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

public class LauncherActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent;

        FacebookSdk.sdkInitialize(getApplicationContext());

        //Opens either Main or Login depending on if the user is logged in or not
        if (AccessToken.getCurrentAccessToken() != null){
            intent = new Intent(this, LoginActivity.class);
        }else{
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
    }


}
