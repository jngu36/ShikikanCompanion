package com.example.shikikancompanion;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();

        TextView t = findViewById(R.id.hello);
        String sayHi = "Hi!\n" +
                "\n" +
                "I'm Shin, an avid gamer that plays a ton of games and the author of this app.\n" +
                "\n" +
                "I made this app as part of a nearly non-existent portfolio of projects I'm involved in as a broke and currently not hired programmer but I also made this app as a convenient means to have certain resources and calculators all in one place in a utility app.\n" +
                "\n" +
                "Most importantly, I want to give special thanks to several people that either inspired some of the features or had assets I needed to make the presentation a lot more nicer than what it was originally. People and resources include:\n" +
                "\n" +
                "-The staff behind: https://www.gfwiki.com/, some icons and a lot of data validation were taken from there.\n" +
                "-@gflkr (http://gfl.zzzzz.kr/) for the high quality images\n" +
                "-aaeeschylus for the original recipe calculator (https://aaeeschylus.github.io/main.html) as far as memory goes\n" +
                "-GFL's Moegirl wiki for the thumbnail art\n" +
                "-Battery formula came from here \"https://cafe.naver.com/girlsfrontlinekr/1343675\"\n" +
                "\n" +
                "You can always find updates at my github (https://github.com/jngu36/ShikikanCompanion) and it will always be free. I will try to update and fix my app when I have time for it but it will not be something I can promise forever.\n" +
                "\n" +
                "You can contact at:\n" +
                "Email: jnshin36@gmail.com\n" +
                "Discord: Shin Megami Senpai#5803\n" +
                "\n" +
                "Though if you try to contact me through Discord, I might freak out a little bit at first cause I feel like anyone I don't know messaging me are out to get me. Or something.";

        t.setText(sayHi);
    }

    private void init() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .85), (int) (height * .85));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }
}
