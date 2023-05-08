package studio.jvmfrog.ffsettings.ui;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import studio.jvmfrog.ffsettings.MyApplication;
import studio.jvmfrog.ffsettings.R;
import studio.jvmfrog.ffsettings.databinding.ActivityMainBinding;

import studio.jvmfrog.ffsettings.utils.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;
    private ActionBar actionBar;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.instance.setNightMode();
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (SharedPreferencesUtils.getBoolean(this, "useDynamicColors"))
            DynamicColors.applyToActivityIfAvailable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        actionBar = getSupportActionBar();

        if (!SharedPreferencesUtils.getBoolean(this, "isFirstOpen")) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(new ContextThemeWrapper(this, R.style.Theme_FFSettings_MaterialAlertDialog));
            builder.setIcon(R.drawable.ic_round_insert_emoticon_24);
            builder.setTitle(R.string.welcome);
            builder.setMessage(R.string.welcome_message);
            builder.setPositiveButton("OK", (dialog, which) -> SharedPreferencesUtils.saveBoolean(this, "isFirstOpen", true));
            builder.show();
        }

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        BottomNavigationView bottomNav = findViewById(R.id.bottomAppBar);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int id = navHostFragment.getNavController().getCurrentDestination().getId();
        if (!(id == R.id.devicesFragment || id == R.id.deviceSettingsFragment || id == R.id.settingsFragment)) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void recreate() {
        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        super.recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = navHostFragment.getNavController().getCurrentDestination().getId();
        int item = menuItem.getItemId();
        switch (item) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.settings:
                if (id == R.id.manufacturerFragment2)
                    navController.navigate(R.id.action_manufacturerFragment2_to_settingsFragment);
                else if (id == R.id.aboutAppFragment)
                    navController.navigate(R.id.action_aboutAppFragment_to_settingsFragment);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}