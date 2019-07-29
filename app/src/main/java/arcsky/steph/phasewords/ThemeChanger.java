package arcsky.steph.phasewords;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ThemeChanger extends AppCompatActivity {

    private CardView defaultThemeChanger;
    private CardView monotoneThemeChanger;
    private Context mContext;
    private ImageView mDefaultImageView;
    private ImageView mMonotoneImageView;
    final String THEME_PREFS = "ThemeFile";
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_activity);

        // Assign a bunch of objects for use in the Activity.
        defaultThemeChanger = findViewById(R.id.default_theme_changer);
        monotoneThemeChanger = findViewById(R.id.monotone_theme_changer);
        mDefaultImageView = findViewById(R.id.default_theme_image);
        mMonotoneImageView = findViewById(R.id.monotone_theme_image);

        // Assign and setup the Activity's toolbar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.grey_900));

        Glide
                .with(this)
                .load(R.drawable.default_theme)
                .into(mDefaultImageView);

        Glide
                .with(this)
                .load(R.drawable.monotone_theme)
                .into(mMonotoneImageView);

        // Assign a set of SharedPreferences relating to the app's Theme.
        SharedPreferences sharedPrefsTheme = getSharedPreferences(THEME_PREFS, MODE_PRIVATE);
        final SharedPreferences.Editor themePrefsEditor = sharedPrefsTheme.edit();
//        Boolean defaultColorsMode = sharedPrefsTheme.getBoolean("defaultColorsMode", false);
//        Boolean monotoneMode = sharedPrefsTheme.getBoolean("monotoneMode", false);

        // Set a Listener on the defaultThemeChanger to run code when it's clicked.
        defaultThemeChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("fragmentOptionSelected", "defaultColorsMode!");
                themePrefsEditor.putString("defaultColorsMode", "1");
                themePrefsEditor.putString("monotoneMode", "2");
                themePrefsEditor.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        // Set a Listener on the monotoneThemeChanger to run code when it's clicked.
        monotoneThemeChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("fragmentOptionSelected", "monotoneColorsMode!");
                themePrefsEditor.putString("defaultColorsMode", "2");
                themePrefsEditor.putString("monotoneMode", "1");
                themePrefsEditor.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_theme_changer, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
