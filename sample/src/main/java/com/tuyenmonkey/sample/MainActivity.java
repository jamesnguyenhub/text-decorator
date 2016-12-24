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
    String text = "Tui la Tuyen Monkey!\nlsjdflksdj";
    TextDecorator
        .decorate(tvTest, text)
        .build();
  }
}
