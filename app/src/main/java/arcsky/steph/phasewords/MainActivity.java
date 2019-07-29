package arcsky.steph.phasewords;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mainBackground;
    private TextView whatWeNeedIs;
    private TextView randomWord1;
    private TextView randomWord2;
    private TextView randomWord3;
    private FloatingActionButton floatingActionButton;
//    Handler handler = new Handler();
    private int i = 0;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPrefsEditor;
    private Toolbar toolbar;
    final String THEME_PREFS = "ThemeFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign a set of SharedPreferences relating to the app's Theme.
        SharedPreferences sharedPrefsTheme = getSharedPreferences(THEME_PREFS, MODE_PRIVATE);
        final String defaultColorsMode = sharedPrefsTheme.getString("defaultColorsMode", "1");
        final String monotoneMode = sharedPrefsTheme.getString("monotoneMode", "2");
//        final Boolean defaultColorsMode = sharedPrefsTheme.getBoolean("defaultColorsMode", false);
//        final Boolean monotoneMode = sharedPrefsTheme.getBoolean("monotoneMode", false);
//        SharedPreferences.Editor themePrefsEditor = getSharedPreferences(THEME_PREFS, MODE_PRIVATE).edit();

//        // Run code contained within only on a fresh install with no previous data/cache.
//        final String FIRST_RUN_PREFS = "firstRunPrefs";
//        SharedPreferences firstRunPrefs = getSharedPreferences(FIRST_RUN_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor firstRunPrefsEditor =
//                getSharedPreferences(FIRST_RUN_PREFS, MODE_PRIVATE).edit();
//
//        // One-time, first app-launch code is run here.
//        if (!firstRunPrefs.getBoolean("firstRun", false)) {
//            firstRunPrefsEditor.putBoolean("firstRun", true);
//            firstRunPrefsEditor.apply();
//
//            themePrefsEditor.putBoolean("defaultColorsMode", false);
//            themePrefsEditor.apply();
//            Log.i("firstRun", "defaultColorsMode SET!");
//        }

        // Assign a bunch of objects for use in the Activity.
        mainBackground = findViewById(R.id.main_background);
        whatWeNeedIs = findViewById(R.id.what_we_need_is_textView);
        randomWord1 = findViewById(R.id.random_word_1);
        randomWord2 = findViewById(R.id.random_word_2);
        randomWord3 = findViewById(R.id.random_word_3);
        floatingActionButton = findViewById(R.id.floating_action_button);

        // Assign and setup the Activity's toolbar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // If the defaultColorsMode Theme is selected/active, run the code below.
        if (defaultColorsMode.equals("1")) {
            mainBackground.setBackgroundColor(getResources().getColor(R.color.teal_faded_700));
            //Log.i("defaultColorsMode", "Background SET");
        // Else, if the monotoneMode Theme is selected/active, run the code below.
        } else if (monotoneMode.equals("1")) {
            mainBackground.setBackgroundColor(getResources().getColor(R.color.grey_800));
            floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.red_faded_800));
            toolbar.setBackgroundColor(getResources().getColor(R.color.grey_900));
            //Log.i("monotoneMode", "Background SET");

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.grey_900));
            window.setNavigationBarColor(getResources().getColor(R.color.grey_900));
        // If no Theme is selected/active, run the code below to set the defaultColorsMode.
        } else {
            mainBackground.setBackgroundColor(getResources().getColor(R.color.teal_faded_700));
            //Log.i("noColorsMode", "Background SET");
        }


        final String[] wordListOne = {"repeatedly", "3 & 1/2 foot", "short", "wasted", "unaccomplished", "simple", "frightening", "enormously", "nerdy", "stupid", "unsatisfying", "satisfyingly", "mischievous", "anxious", "fat", "lame", "spooky", "greasy", "cocky", "antisocial", "lukewarm", "underdeveloped", "super", "smooth", "delightful", "surprisingly", "unbelievably", "messy", "fake", "unfriendly", "hideous", "lovely", "flashy", "deeply", "bland", "creepy", "crippled", "drunk", "unsettling", "tasteless", "gigantic", "deceptive", "corrupted", "old", "unrestrained", "flamboyant", "hungry", "mildly", "annoying", "plain", "unbelievably", "ominous", "grumpy", "illiterate", "cool", "conniving", "offensive", "greedy", "sad", "dense", "obnoxious", "damn", "totally", "unpopular", "energetic", "useless", "new", "strong", "flawed", "slippery", "irritating", "eight-legged", "extra", "somewhat", "incredible", "blind", "dim-witted", "bloody", "small", "massively", "painful", "tasty", "long", "unpredictable", "bulging", "scary", "introverted", "scorched", "crude", "complicated", "death-defying", "spunky", "affectionate", "temporarily", "partly", "overwhelmingly", "crappy", "dangerous", "terrifying", "articulate", "vivid", "shy", "demanding", "sneaky", "unacceptable", "futuristic", "medieval", "embarrassing", "expensive", "thick", "burnt", "disgraceful", "horrible", "chill", "bewildering", "ice-cold", "amusing", "5-minute", "contorted", "hyperactive", "dull", "highly", "triggered", "rabid", "unfathomably", "dorky", "bright", "pale", "uncertain", "frail", "hilarious", "booming", "rough", "ear-piercing", "unconventional", "abnormal", "classic", "stunning", "shocking", "fascinating", "foolish", "reckless", "enticing", "wild", "helpful", "transparent", "aimless", "friendly", "gracious", "courteous", "gentle", "benign", "bizarre", "rare", "peculiar", "funny", "large", "awkward", "entertaining", "eleven-ton", "120cm tall", "immense", "tremendous", "gargantuan", "colossal", "sizable", "microscopic", "diseased", "sick", "contaminated", "stressed", "astonishing", "decent", "twelve-toed", "blindingly", "tattered", "battered", "alarming", "lifeless", "slightly", "stoned", "weary", "slimy", "albino", "prehistoric"};

        final String[] wordListTwo = {"overweight", "Chinese", "untrustworthy", "dehydrated", "skinny", "confusing", "mutated", "dying", "uninspiring", "tiresome", "American", "stinking", "slow", "problematic", "primitive", "calloused", "inspiring", "disappointing", "harmless", "violent", "flying", "2D", "moronic", "unreliable", "inappropriate", "bearded", "infantile", "invisible", "purple", "sleepy", "rainbow-colored", "bi-racial", "shape-shifting", "flabby", "alien", "chaotic", "bug-eyed", "rotund", "cowardly", "muscular", "disastrous", "hot", "sun-burned", "undulating", "adventurous", "filthy", "trashy", "millennial", "fearful", "excitable", "million-year-old", "youthful", "paranormal", "space-faring", "dirty", "repetitive", "fantastic", "malfunctioning", "hypothetical", "psychic", "red-faced", "evil", "extroverted", "shouting", "500kg", "18th century", "carnivorous", "cheesy", "unconvincing", "incoherent", "deep-fried", "delicious", "time-consuming", "German", "Jurassic", "demented", "inconvenient", "200-pound", "70-year-old", "middle-aged", "Russian", "crying", "$45", "$9,000", "ignorant", "melancholy", "feeble", "gullible", "Australian", "Brazilian", "idiotic", "time-travelling", "sweaty", "disturbing", "orange", "failed", "3-meter-wide", "imaginary", "dinosaur", "baby", "dead", "ill-tempered", "diverse", "wimpy", "oriental", "European", "dad", "six-hour-long", "face-eating", "flesh-ripping", "green", "spontaneous", "stale", "color-blind", "Swedish", "Danish", "spicy", "manly", "backstabbing", "virus-infected", "disease-ridden", "otherworldly", "ghoulish", "bloated", "out of shape", "zombie", "undead", "depressing", "unimpressive", "appalling", "distracting", "confrontational", "demoralizing", "nuclear", "mangled", "injured", "malnourished", "immature", "disfigured", "aggressive", "out-of-touch", "incompetent", "8-bit", "life-altering", "irrational", "rude", "abrasive", "naked", "starved", "psychotic", "35-pound", "pink", "rebellious", "free-loading", "confused", "turquoise", "golden", "vampire", "monstrous", "mercenary", "disgusting", "compelling", "unplanned", "prolonged", "odd", "long-winded", "eccentric", "weird", "sad", "Japanese", "kung fu", "maimed", "damaged", "blue", "glowing", "flesh-eating", "glorious", "unhealthy", "unprecedented", "insecure", "red", "pointless", "jobless", "drawn-out", "dismissive", "painful", "clumsy", "murderous", "immortal", "hostile", "threatening", "dramatic", "super-effective", "criminal", "pro", "selfish", "illegal", "crooked", "mind-bending", "bogus", "gyrating", "pet", "ugly", "grotesque", "reptilian", "robotic", "unsuccessful"};

        final String[] wordListThree = {"chain-smoker", "fruit basket", "road trip", "movie", "firefighter", "ghost", "assassin", "hobo", "moose", "butt-scratcher", "back-scratcher", "monkey", "poop", "goat", "butler", "counterfeit", "failure", "astronaut", "liver transplant", "goose", "office worker", "dictator", "rabbit", "conman", "video game", "athlete", "filmmaker", "actor", "bank robber", "president", "world war", "sandwich", "mystery novel", "life story", "heart attack", "substitute teacher", "artist", "cosplayer", "comedian", "cashier", "babysitter", "mailman", "dragon", "sheriff's deputy", "gardener", "battle plan", "simulator", "dance-off", "head of state", "branch manager", "police force", "insurance plan", "vacation", "duck", "revolution", "introduction", "veterinarian", "janitor", "disaster", "hotdog", "commercial break", "kick to the pants", "Hungarian", "Scotsman", "Brit", "dachshund", "global summit", "baseball game", "bald eagle", "tour", "guard dog", "poodle", "giraffe", "crowd", "dinner party", "bar fight", "public execution", "horror story", "documentary", "cringe compilation", "cyclops", "conversation", "barber", "fart", "adventurer", "Canadian", "pilot", "smartphone", "competition", "family gathering", "blind date", "psychopath", "barbecue", "individual", "ox", "investigation", "mongoose", "Spiderman", "butterfly", "cat", "slap to the face", "stand-off", "grandma", "grandpa", "second cousin", "cook", "rash", "disease", "plague", "doctor", "surgeon", "explosion", "joke", "blunder", "buffoon", "tournament", "life", "childhood", "ice cream", "squeal", "bathroom break", "hitman", "salesman", "cruise", "contest", "farmer", "lollipop", "lawyer", "year", "Thanksgiving", "Christmas", "black friday", "invasion", "wizard", "population", "pelican", "seal", "top hat", "dentist", "seminar", "octopus", "revival", "cookbook", "weatherman", "class project", "natural disaster", "humpback whale", "birthday party", "shark", "ocean diver", "leopard", "beagle", "fisherman", "surprise attack", "Triceratops", "snake", "water buffalo", "python", "pigeon party", "caveman", "gremlin", "nurse", "rapper", "bus driver", "pterodactyl", "iguana", "hawk", "walrus", "fantasy", "Iron Man", "Batman", "superhero", "ninja", "uncle", "aunt", "car", "witch", "garbage collector", "English teacher", "body guard", "shopping spree", "remark", "comment", "interview", "kind of day", "Hulk", "body builder", "wild goose chase", "chat", "double agent", "classroom", "flight", "sing-a-long", "bear fight", "moon landing", "poker game", "scream", "war cry", "presentation", "gangster", "axe", "swordfight", "spider", "bird", "dolphin", "bowel movement", "crocodile", "lobster", "bengal tiger", "pack of wolves", "heartbreak", "bunch of dudes", "pedestrian", "life change", "job offer", "delivery", "family reunion", "conference call", "way of life", "eating habit", "hairstyle", "haircut", "amputation", "interrogation", "pop quiz", "weekend", "prime minister", "field trip", "Velociraptor", "foreigner", "festival", "massage", "horse", "turtle", "rattlesnake", "fighter jet", "moron", "geek", "real estate agent", "people-pleaser", "toad", "deer", "robot", "person", "shoplifter", "communist"};

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        };

        final int oneLength = wordListOne.length;
        final int twoLength = wordListTwo.length;
        final int threeLength = wordListThree.length;

        // Instantiate and set the length of an alpha animation (from invisible to visible).
        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(500);
        // Instantiate and set the length of an alpha animation (from visible to invisible).
        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(500);

        // Set a Listener on the FloatingActionButton to run code when it's clicked.
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rand1 = (int) (Math.random() * oneLength);
                int rand2 = (int) (Math.random() * twoLength);
                int rand3 = (int) (Math.random() * threeLength);

                final String firstWord = wordListOne[rand1];
                String middleWord = wordListTwo[rand2];
//                String blankOrComma = " ";
//                if (middleWord.contains("yet inspiring")) {
//                    blankOrComma = ", ";
//                }

//                final String randomString1 = firstWord + blankOrComma;
                final String randomString1 = firstWord;
                final String randomString2 = middleWord;
                final String randomString3 = wordListThree[rand3];


//                SharedPreferences sharedPrefsTheme = getSharedPreferences(THEME_PREFS, MODE_PRIVATE);
//                Boolean defaultColorsMode = sharedPrefsTheme.getBoolean("defaultColorsMode", false);
//                Boolean monotoneMode = sharedPrefsTheme.getBoolean("monotoneMode", false);
//                mSharedPrefsEditor = sharedPrefsTheme.edit();
                String iValue = valueOf(i);

                // If the defaultColorsMode Theme is selected and active, then run the code below.
                if (defaultColorsMode.equals("1")) {
                    switch (i) {
                        case (0):
                            mainBackground.setBackground(getDrawable(R.drawable.transition_colors1));
                            TransitionDrawable transition = (TransitionDrawable) mainBackground.getBackground();
                            transition.startTransition(200);
                            floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.teal_faded_700));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.purple_faded_900));
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(getResources().getColor(R.color.purple_faded_900));
                            window.setNavigationBarColor(getResources().getColor(R.color.purple_faded_900));
                            i = i + 1;
                            //Log.i("DEF FIRST i equals = ", iValue);
                            break;
                        case (1):
                            mainBackground.setBackground(getDrawable(R.drawable.transition_colors2));
                            TransitionDrawable transition2 = (TransitionDrawable) mainBackground.getBackground();
                            transition2.startTransition(200);
                            floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.purple_faded_700));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.blue_faded_900));
                            Window window2 = getWindow();
                            window2.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window2.setStatusBarColor(getResources().getColor(R.color.blue_faded_900));
                            window2.setNavigationBarColor(getResources().getColor(R.color.blue_faded_900));
                            i = i + 1;
                            //Log.i("DEF SECOND i equals = ", iValue);
                            break;
                        case (2):
                            mainBackground.setBackground(getDrawable(R.drawable.transition_colors3));
                            TransitionDrawable transition3 = (TransitionDrawable) mainBackground.getBackground();
                            transition3.startTransition(200);
                            floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue_faded_700));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.red_faded_900));
                            Window window3 = getWindow();
                            window3.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window3.setStatusBarColor(getResources().getColor(R.color.red_faded_900));
                            window3.setNavigationBarColor(getResources().getColor(R.color.red_faded_900));
                            i = i + 1;
                            //Log.i("DEF THIRD i equals = ", iValue);
                            break;
                        case (3):
                            mainBackground.setBackground(getDrawable(R.drawable.transition_colors4));
                            TransitionDrawable transition4 = (TransitionDrawable) mainBackground.getBackground();
                            transition4.startTransition(200);
                            floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.red_faded_800));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.grey_900));
                            Window window4 = getWindow();
                            window4.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window4.setStatusBarColor(getResources().getColor(R.color.grey_900));
                            window4.setNavigationBarColor(getResources().getColor(R.color.grey_900));
                            i = i + 1;
                            //Log.i("DEF FOURTH i equals = ", iValue);
                            break;
                        case (4):
                            mainBackground.setBackground(getDrawable(R.drawable.transition_colors5));
                            TransitionDrawable transition5 = (TransitionDrawable) mainBackground.getBackground();
                            transition5.startTransition(200);
                            floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.grey_800));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.brown_800));
                            Window window5 = getWindow();
                            window5.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window5.setStatusBarColor(getResources().getColor(R.color.brown_800));
                            window5.setNavigationBarColor(getResources().getColor(R.color.brown_800));
                            i = i + 1;
                            //Log.i("DEF FIFTH i equals = ", iValue);
                            break;
                        case (5):
                            mainBackground.setBackground(getDrawable(R.drawable.transition_colors6));
                            TransitionDrawable transition6 = (TransitionDrawable) mainBackground.getBackground();
                            transition6.startTransition(200);
                            floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.brown_600));
                            toolbar.setBackgroundColor(getResources().getColor(R.color.teal_faded_900));
                            Window window6 = getWindow();
                            window6.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window6.setStatusBarColor(getResources().getColor(R.color.teal_faded_900));
                            window6.setNavigationBarColor(getResources().getColor(R.color.teal_faded_900));
                            i = i - 5;
                            //Log.i("DEF SIXTH i equals = ", iValue);
                            break;
                    }
                    // Else if the monotoneMode Theme is selected and active, then run the code below.
                } else if (monotoneMode.equals("1")) {

//                    switch (i) {
//                        case (0):
//                            mainBackground.setBackground(getDrawable(R.drawable.transition_colors5));
//                            TransitionDrawable transition5 = (TransitionDrawable) mainBackground.getBackground();
//                            transition5.startTransition(200);
//                            floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.grey_800));
//                            toolbar.setBackgroundColor(getResources().getColor(R.color.brown_800));
//                            i = i + 1;
//                            Log.i("MONO FIRST i equals = ", iValue);
//                            break;
//                        case (1):
//                            mainBackground.setBackground(getDrawable(R.drawable.transition_colors7));
//                            TransitionDrawable transition6 = (TransitionDrawable) mainBackground.getBackground();
//                            transition6.startTransition(200);
//                            floatingActionButton.setBackgroundTintList(getResources().getColorStateList(R.color.brown_600));
//                            toolbar.setBackgroundColor(getResources().getColor(R.color.grey_900));
//                            i = i - 1;
//                            Log.i("MONO SECOND i equals = ", iValue);
//                            break;
//                    }

                }

                // If the randomPhrases TextViews are empty, then run the code below.
                if (randomWord1.getText().toString().isEmpty()) {
                    whatWeNeedIs.startAnimation(out);

                    // Handler to delay textView's fade-in effect.
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            randomWord1.startAnimation(in);
                            randomWord1.setText(randomString1);
                            randomWord2.startAnimation(in);
                            randomWord2.setText(randomString2);
                            randomWord3.startAnimation(in);
                            randomWord3.setText(randomString3);

                            if (startWithVowel(firstWord)) {
                                whatWeNeedIs.startAnimation(in);
                                whatWeNeedIs.setText(R.string.what_we_need_is_an);
                            } else if (!startWithVowel(firstWord)) {
                                whatWeNeedIs.startAnimation(in);
                                whatWeNeedIs.setText(getString(R.string.what_we_need_is));
                            }
                        }
                    }, 200);

                    // If the randomPhrases TextViews are populated/not empty, run the code below.
                } else {
                    randomWord1.startAnimation(out);
                    randomWord2.startAnimation(out);
                    randomWord3.startAnimation(out);
                    whatWeNeedIs.startAnimation(out);

                    // Handler to delay textView's fade-in effect.
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            randomWord1.startAnimation(in);
                            randomWord1.setText(randomString1);
                            randomWord2.startAnimation(in);
                            randomWord2.setText(randomString2);
                            randomWord3.startAnimation(in);
                            randomWord3.setText(randomString3);
                            if (startWithVowel(firstWord)) {
                                whatWeNeedIs.startAnimation(in);
                                whatWeNeedIs.setText(R.string.what_we_need_is_an);
                            } else if (!startWithVowel(firstWord)) {
                                whatWeNeedIs.startAnimation(in);
                                whatWeNeedIs.setText(getString(R.string.what_we_need_is));
                            }
                        }
                    }, 200);

                }
            }
        });
    }

    // Checks if a String word starts with a vowel.
    static boolean startWithVowel(String word) {
        return "aeiouAEIOU".indexOf(word.charAt(0)) >= 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        final String THEME_PREFS = "ThemeFile";
//        SharedPreferences sharedPrefsTheme = getSharedPreferences(THEME_PREFS, MODE_PRIVATE);
//        Boolean defaultColorsMode = sharedPrefsTheme.getBoolean("defaultColorsMode", false);
//        Boolean monotoneMode = sharedPrefsTheme.getBoolean("monotoneMode", false);
//
//        if (defaultColorsMode) {
//
//        } else if (monotoneMode) {
//
//        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Access and/or modify the SharedPreferences file called "ThemeFile", which contains the
        // app's theme preferences.
//        final String THEME_PREFS = "ThemeFile";
//        SharedPreferences sharedPrefsTheme = getSharedPreferences(THEME_PREFS, MODE_PRIVATE);
//        Boolean defaultColorsMode = sharedPrefsTheme.getBoolean("defaultColorsMode", false);
//        Boolean monotoneMode = sharedPrefsTheme.getBoolean("monotoneMode", false);

        Context contextDarkThemeWrapper = new ContextThemeWrapper(this, R.style.Popup_Menu_Dark);

        // Handle presses on the action bar items
        final int itemId = item.getItemId();

        if (itemId == R.id.change_color_theme) {
            Intent intent = new Intent(getApplicationContext(), ThemeChanger.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

//            Log.i("onOptionsItemSelected", "changeColorTheme PRESSED!");
//            if (defaultColorsMode) {
//                Log.i("onOptionsItemSelected", "monotoneColorsMode!");
////                setTheme(R.style.AppTheme_Light_NoActionBar);
//                SharedPreferences.Editor themePrefsEditor = getSharedPreferences(THEME_PREFS, MODE_PRIVATE).edit();
//                themePrefsEditor.remove("defaultColorsMode");
//                themePrefsEditor.putBoolean("monotoneMode", true);
//                themePrefsEditor.apply();
//
//                finish();
//                startActivity(getIntent());
//                return true;
//            } else if (monotoneMode) {
//                Log.i("onOptionsItemSelected", "defaultColorsMode!");
////                setTheme(R.style.AppTheme_Dark_NoActionBar);
//                SharedPreferences.Editor themePrefsEditor = getSharedPreferences(THEME_PREFS, MODE_PRIVATE).edit();
//                themePrefsEditor.remove("monotoneMode");
//                themePrefsEditor.putBoolean("defaultColorsMode", true);
//                themePrefsEditor.apply();
//
//                finish();
//                startActivity(getIntent());
//                return true;
//            } else {
//                Log.i("onOptionsItemSelected", "monotoneColorsMode!");
////                setTheme(R.style.AppTheme_Light_NoActionBar);
//                SharedPreferences.Editor themePrefsEditor = getSharedPreferences(THEME_PREFS, MODE_PRIVATE).edit();
//                themePrefsEditor.remove("defaultColorsMode");
//                themePrefsEditor.putBoolean("monotoneMode", true);
//                themePrefsEditor.apply();
//
//                finish();
//                startActivity(getIntent());
//                return true;
//            }
        } else if (itemId == R.id.menu_extras) {
            View menuItemView = findViewById(R.id.menu_extras);
            PopupMenu popup = new PopupMenu(contextDarkThemeWrapper, menuItemView);

            // Inflate the menu from xml
            popup.inflate(R.menu.popup_extras_menu);
            popup.getMenu();

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_privacy_policy:
                            String url = "https://sites.google.com/view/arcskydev-apps-privacy-policy/home";
                            Intent privacyPolicyIntent = new Intent(Intent.ACTION_VIEW);
                            privacyPolicyIntent.setData(Uri.parse(url));
                            if (privacyPolicyIntent.resolveActivity(getPackageManager()) != null) {
                                startActivity(privacyPolicyIntent);
                            }
                            return true;
                        default:
                            return false;
                    }
                }
            });
            // Show the menu
            popup.show();
        }

        return super.onOptionsItemSelected(item);
    }

    /* Method with which to programmatically delete the App's cache. */
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Method to get the App's directory for deletion if deleteCache() is called. */
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
