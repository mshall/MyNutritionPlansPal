package com.activities;

import android.util.Log;

import com.allemny.R;
import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by stephentuso on 11/15/15.
 */
public class WelcomeActivity extends com.stephentuso.welcome.ui.WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {
        Log.e("INSIDE WELCOME","INSIDE WELCOME NOW");
        return new WelcomeScreenBuilder(this)
                .theme(R.style.CustomWelcomeScreenTheme)
                .defaultTitleTypefacePath("Montserrat-Bold.ttf")
                .defaultHeaderTypefacePath("Montserrat-Bold.ttf")
                .titlePage(R.drawable.photo, "Welcome", R.color.orange_background)
                .basicPage(R.drawable.photo, "Simple to use", "Add a welcome screen to your app with only a few lines of code.", R.color.red_background)
                .parallaxPage(R.layout.parallax_example, "Easy parallax", "Supply a layout and parallax effects will automatically be applied", R.color.purple_background, 0.2f, 2f)
                .basicPage(R.drawable.photo, "Customizable", "All elements of the welcome screen can be customized easily.", R.color.blue_background)
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }


    public static String welcomeKey() {
        return "WelcomeScreen";
    }

}
