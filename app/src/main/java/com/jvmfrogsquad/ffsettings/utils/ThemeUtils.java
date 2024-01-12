import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

public class ThemeUtils {

    private static final String TAG = "ThemeUtils";

    public static void setTheme(Context context, int mode) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.uiMode = mode;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static int getTheme(Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        return configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
    }

    public static void applyTheme(Activity activity) {
        int mode = getTheme(activity);
        switch (mode) {
            case Configuration.UI_MODE_NIGHT_NO:
                activity.setTheme(R.style.AppTheme_Light);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                activity.setTheme(R.style.AppTheme_Dark);
                break;
            case Configuration.UI_MODE_NIGHT_FOLLOW_SYSTEM:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    if (isSystemInNightMode(activity)) {
                        activity.setTheme(R.style.AppTheme_Dark);
                    } else {
                        activity.setTheme(R.style.AppTheme_Light);
                    }
                } else {
                    activity.setTheme(R.style.AppTheme_Light);
                }
                break;
            default:
                activity.setTheme(R.style.AppTheme_Light);
                break;
        }
    }

    private static boolean isSystemInNightMode(Activity activity) {
        int nightModeFlags = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }

    public static void changeTheme(Activity activity, int newMode) {
        int oldMode = getTheme(activity);
        Log.d(TAG, "Changing theme from " + oldMode + " to " + newMode);
        setTheme(activity, newMode);
        activity.recreate();
    }

    public static void changeThemeWithTransition(Activity activity, int newMode, int duration) {
        int oldMode = getTheme(activity);
        Log.d(TAG, "Changing theme from " + oldMode + " to " + newMode);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.uiMode = newMode;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        activity.getWindow().setEnterTransition(new FadeTransition(duration));
        activity.getWindow().setExitTransition(new FadeTransition(duration));
        activity.recreate();
    }
        
    // Эффект расходящегося круга от места клика пальца
    public static void changeThemeWithDivergentCircleTransition(Activity activity, int newMode, int duration) {
        int oldMode = getTheme(activity);
        Log.d(TAG, "Changing theme from " + oldMode + " to " + newMode);
    
        // Создаем View, относительно которой будет выполняться анимация раскрытия круга
        View viewToAnimate = activity.findViewById(android.R.id.content);

        // Определите координаты центра анимации (где пользователь коснулся экрана)
        int centerX = /* определите координату X */;
        int centerY = /* определите координату Y */;

        // Вычисляем радиус, который будет достаточно большим, чтобы покрыть весь экран
        int startRadius = 0;
        int endRadius = (int) Math.hypot(viewToAnimate.getWidth(), viewToAnimate.getHeight());

        // Создаем анимацию CircularReveal
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                viewToAnimate,
                centerX,
                centerY,
                startRadius,
                endRadius
        );

        // Применяем созданную вами кастомную анимацию
        Animation customAnimation = AnimationUtils.loadAnimation(activity, R.anim.circular_reveal);
        viewToAnimate.startAnimation(customAnimation);

        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.uiMode = newMode;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        activity.getWindow().setEnterTransition(new FadeTransition(duration));
        activity.getWindow().setExitTransition(new FadeTransition(duration));
        activity.recreate();
    }

    // Анимация круга сжимающиеся обратно к месту клика пальца
    public static void changeThemeWithRemovableCircleTransition(Activity activity, int newMode, int duration) {
        int oldMode = getTheme(activity);
        Log.d(TAG, "Changing theme from " + oldMode + " to " + newMode);
    
        // Создаем View, относительно которой будет выполняться анимация раскрытия круга
        View viewToAnimate = activity.findViewById(android.R.id.content);

        // Определите координаты центра анимации (где пользователь коснулся экрана)
        int centerX = /* определите координату X */;
        int centerY = /* определите координату Y */;

        // Вычисляем радиус, который будет достаточно большим, чтобы покрыть весь экран
        int startRadius = 0;
        int endRadius = (int) Math.hypot(viewToAnimate.getWidth(), viewToAnimate.getHeight());

        // Создаем анимацию CircularReveal
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                viewToAnimate,
                centerX,
                centerY,
                startRadius,
                endRadius
    );

        // Применяем созданную вами кастомную анимацию
        Animation customAnimation = AnimationUtils.loadAnimation(activity, R.anim.circular_reveal);
        viewToAnimate.startAnimation(customAnimation);

        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.uiMode = newMode;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        activity.getWindow().setEnterTransition(new FadeTransition(duration));
        activity.getWindow().setExitTransition(new FadeTransition(duration));
        activity.recreate();

        // Добавляем обратную анимацию после recreate()
        Animation reverseAnimation = AnimationUtils.loadAnimation(activity, R.anim.circular_conceal);
        viewToAnimate.startAnimation(reverseAnimation);
    }


    public static void setDynamicColors(Context context, boolean enabled) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            DynamicColorManager.getInstance(context).setDynamicColorsEnabled(enabled);
        }
    }

    public static boolean isDynamicColorsEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return DynamicColorManager.getInstance(context).isDynamicColorsEnabled();
        } else {
            return false;
        }
    }
}
