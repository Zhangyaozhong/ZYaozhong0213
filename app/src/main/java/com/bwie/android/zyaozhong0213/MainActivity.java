package com.bwie.android.zyaozhong0213;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.android.zyaozhong0213.annotation.BindView;
import com.bwie.android.zyaozhong0213.annotation.ContentView;
import com.bwie.android.zyaozhong0213.annotation.DraweeViewAnnotation;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.reflect.Field;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.sdv)
    @DraweeViewAnnotation
    SimpleDraweeView draweeView;
    @BindView(R.id.yuanxing_btn)
    Button yuanxing_btn;
    @BindView(R.id.yuanjiao_btn)
    Button yuanjiao_btn;
    @BindView(R.id.proportion_btn)
    Button proportion_btn;
    @BindView(R.id.get_btn)
    Button get_btn;
    @BindView(R.id.gif_btn)
    Button git_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            InjectUtils.inject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Uri uri = Uri.parse("http://img18.3lian.com/d/file/201711/24/529494b4dc8b9368cbf309e29be38f90.png");
        draweeView.setImageURI(uri);
//        setContentView(R.layout.activity_main);
        yuanxing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoundingParams roundingParams = new RoundingParams();
                roundingParams.setRoundAsCircle(true);
                GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
                builder.setRoundingParams(roundingParams);
                GenericDraweeHierarchy hierarchy = builder.build();
                draweeView.setHierarchy(hierarchy);
                draweeView.setImageURI(uri);
            }
        });

        //圆角
        yuanjiao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoundingParams roundingParams = new RoundingParams();
                roundingParams.setCornersRadius(30f);
                GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
                builder.setRoundingParams(roundingParams);
                GenericDraweeHierarchy hierarchy = builder.build();
                draweeView.setHierarchy(hierarchy);
                draweeView.setImageURI(uri);
            }
        });

        //宽高比
        proportion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draweeView.setAspectRatio(2.71f);
                draweeView.setImageURI(uri);
            }
        });
        //Toast注解内容
        get_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class<? extends Activity> aClass = MainActivity.class;
                //获取所有的字段
                Field[] fields = aClass.getDeclaredFields();

                if (fields != null && fields.length > 0) {
                    for (Field field : fields) {
                        DraweeViewAnnotation draweeViewAnnotation = field.getAnnotation(DraweeViewAnnotation.class);
                        if (draweeViewAnnotation != null) {
                            String value = draweeViewAnnotation.name();
                            Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

                        }
                    }
                }

            }
        });
        git_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri1 = Uri.parse("http://www.zhaoapi.cn/images/girl.gif");
                DraweeController controllerBuilder = Fresco.newDraweeControllerBuilder()
                        .setUri(uri1)
                        .setAutoPlayAnimations(true)
                        //内存优化
                        .setOldController(draweeView.getController())
                        .build();
                draweeView.setController(controllerBuilder);

            }
        });

    }
}
