package com.bwie.android.zyaozhong0213;

import android.app.Activity;
import android.view.View;

import com.bwie.android.zyaozhong0213.annotation.BindView;
import com.bwie.android.zyaozhong0213.annotation.ContentView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InjectUtils {
    public static void inject(Activity activity) throws Exception {
        injectContentView(activity);
        injectView(activity);
    }

    private static void injectView(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        //获取所有的字段
        Field[] fields = aClass.getDeclaredFields();

        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                BindView bindView = field.getAnnotation(BindView.class);
                if (bindView != null) {
                    int value = bindView.value();
                    View view = activity.findViewById(value);
                    field.setAccessible(true);
                    try {
                        field.set(activity, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private static void injectContentView(Activity activity) throws Exception {
        Class<? extends Activity> aClass = activity.getClass();
        ContentView contentView = aClass.getAnnotation(ContentView.class);

        if (contentView != null) {
            int value = contentView.value();
            Method method = aClass.getMethod("setContentView", int.class);
            method.invoke(activity, value);
        }
    }
}
