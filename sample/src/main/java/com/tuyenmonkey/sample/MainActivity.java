package com.tuyenmonkey.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.tuyenmonkey.textdecorator.TextDecorator;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final TextView tvTest = (TextView)findViewById(R.id.tvTest);
    TextDecorator
        .decorate(tvTest, "Tui la Tuyen Monkey!")
        .setTextColor(R.color.colorPrimaryDark, 0, 5)
        .setBackgroundColor(R.color.colorAccent, 0, 5)
        .build();
  }
}
