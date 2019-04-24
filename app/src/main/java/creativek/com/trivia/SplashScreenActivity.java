package creativek.com.trivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIMEOUT = 1000 * 3; // Display logo for 3 secs
    ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.splash_logo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, QuestionActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMEOUT);


    }
}
