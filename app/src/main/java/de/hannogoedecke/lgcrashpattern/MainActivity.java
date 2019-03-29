package de.hannogoedecke.lgcrashpattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonCrash = findViewById(R.id.buttonCrash);
        if(BuildConfig.FLAVOR.equals("correctDependency")) {
            buttonCrash.setText(getString(R.string.trigger_lg_safe_notification));
        }

        buttonCrash.setOnClickListener((view) -> startServiceWithAction(MusicNotificationService.ACTION_NOTIFICATION_CRASH));
    }

    private void startServiceWithAction(String action) {
        Intent intent = new Intent(this, MusicNotificationService.class);
        intent.setAction(action);
        ContextCompat.startForegroundService(this, intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MusicNotificationService.class));
    }
}
