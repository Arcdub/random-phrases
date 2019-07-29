package arcsky.steph.phasewords;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

public class App extends Application {

    private static final String LOG_TAG = App.class.getName();
    final String THEME_PREFS = "ThemeFile";

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences themePrefs = getSharedPreferences(THEME_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor themePrefsEditor = themePrefs.edit();

        // Run code contained within only on a fresh install with no previous data/cache.
        final String FIRST_RUN_PREFS = "firstRunPrefs";
        SharedPreferences firstRunPrefs = getSharedPreferences(FIRST_RUN_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor firstRunPrefsEditor =
                getSharedPreferences(FIRST_RUN_PREFS, MODE_PRIVATE).edit();


        // One-time, first app-launch code is run here.
        if (!firstRunPrefs.getBoolean("firstRun", false)) {
            firstRunPrefsEditor.putBoolean("firstRun", true);
            firstRunPrefsEditor.apply();

            themePrefsEditor.putString("defaultColorsMode", "1");
//            themePrefsEditor.putBoolean("defaultColorsMode", false);
            themePrefsEditor.apply();
            //Log.i(LOG_TAG, "defaultColorsMode SET!");
        }
    }
}
