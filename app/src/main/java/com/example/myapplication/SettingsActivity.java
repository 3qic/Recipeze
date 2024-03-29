package com.example.myapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.audiofx.Equalizer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//Code für Veränderung der Schriftgröße https://stackoverflow.com/questions/12704216/how-to-change-the-font-size-in-a-whole-application-programmatically-android
//Code für Veränderung der Farbe mit Shared Preferences: https://www.youtube.com/watch?v=GlR7wqWEomU

public class SettingsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    SharedPreferences sharedPreferences, app_preferences;
    SharedPreferences.Editor editor;
    ThemeChooser themeChooser;



    int appTheme;
    int themeColor;
    int appColor;
    float fontSize;


    private RadioGroup ColorSettings;
    private RadioGroup CharSizeSettings;
    private RadioButton CharSize1;
    private RadioButton CharSize2;
    private RadioButton Color1;
    private RadioButton Color2;
    private RadioButton Color3;
    private RadioButton Color4;
    private RadioButton Color5;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        appColor = app_preferences.getInt("color", 0);
        appTheme = app_preferences.getInt("theme", 0);
        themeColor = appColor;


        if (themeColor == 0) {
            setTheme(Constant.theme);
        } else if (appTheme == 0) {
            setTheme(Constant.theme);
        } else {
            setTheme(appTheme);
        }


        setContentView(R.layout.activity_settings);
        setupToolbar();
        themeChooser = new ThemeChooser();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        findViews();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeChooser.setColorTheme();


                int color = ColorSettings.getCheckedRadioButtonId();
                int charSize = CharSizeSettings.getCheckedRadioButtonId();

                if (charSize == CharSize1.getId()){
                    Constant.fontSize = Constant.fontSizeSmall;
                    editor.putFloat("fontSize", Constant.fontSize);
                    editor.commit();
                    showToast("Schriftgröße 'klein' gespeichert");
                }

                else if (charSize == CharSize2.getId()){
                    Constant.fontSize = Constant.fontSizeMedium;
                    editor.putFloat("fontSize", Constant.fontSize);
                    editor.commit();
                    showToast("Schriftgröße 'Groß' gespeichert");
                }



                if (color == Color1.getId()) {
                    Constant.color = 0xFFE6CD38;
                    themeChooser.setColorTheme();
                    editor.putInt("color", Constant.color);
                    editor.putInt("theme", Constant.theme);
                    editor.commit();
                    showToast("Farbe 'Gelb' gespeichert");

                } else if (color == Color2.getId()) {
                    Constant.color = 0xFF1CCA33;
                    themeChooser.setColorTheme();
                    editor.putInt("color", Constant.color);
                    editor.putInt("theme", Constant.theme);
                    editor.commit();
                    showToast("Farbe 'Grün' gespeichert");


                } else if (color == Color3.getId()) {
                    Constant.color = 0xFF13ABC2;
                    themeChooser.setColorTheme();
                    editor.putInt("color", Constant.color);
                    editor.putInt("theme", Constant.theme);
                    editor.commit();
                    showToast("Farbe 'Blau' gespeichert");

                }else if (color == Color4.getId()) {
                    Constant.color = 0xFF3D3939;
                    themeChooser.setColorTheme();
                    editor.putInt("color", Constant.color);
                    editor.putInt("theme", Constant.theme);
                    editor.commit();
                    showToast("Farbe 'Schwarz' gespeichert");

                }else if (color == Color5.getId()) {
                    Constant.color = 0xFFE72A2A;
                    themeChooser.setColorTheme();
                    editor.putInt("color", Constant.color);
                    editor.putInt("theme", Constant.theme);
                    editor.commit();
                    showToast("Farbe 'Rot' gespeichert");
                }
                fontSize = app_preferences.getFloat("fontSize",0);
                changeFont(getResources().getConfiguration(), fontSize);
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }

    public void showToast(String message){
        Toast toast = Toast.makeText(this,message,Toast.LENGTH_SHORT);
        toast.show();
    }



    public void setupToolbar() {
        final Toolbar toolbar = findViewById(R.id.settings_toolbar);
        toolbar.setTitle("Einstellungen");

    }

    public void findViews() {
        ColorSettings = findViewById(R.id.radio_group_colors);
        CharSizeSettings = findViewById(R.id.radio_group_char_size);
        buttonSave = findViewById(R.id.save_button);
        CharSize1 = findViewById(R.id.radio_button_charsize_1);
        CharSize2 = findViewById(R.id.radio_button_charsize_2);
        Color1 = findViewById(R.id.radio_button_color_1);
        Color2 = findViewById(R.id.radio_button_color_2);
        Color3 = findViewById(R.id.radio_button_color_3);
        Color4 = findViewById(R.id.radio_button_color_4);
        Color5 = findViewById(R.id.radio_button_color_5);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    private void changeFont(Configuration configuration, float scale){
        configuration.fontScale = scale;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }

}
