package com.tuyenmonkey.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.tuyenmonkey.textdecorator.TextDecorator;
import com.tuyenmonkey.textdecorator.callback.OnTextClickListener;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final TextView tvContent = (TextView)findViewById(R.id.tvContent);
    final String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis et porta ipsum. Praesent vitae libero a mi sodales accumsan. Donec tempor nulla turpis, vitae pellentesque ligula consectetur sed. Quisque commodo lorem eget ipsum pulvinar consequat. Nam erat risus, rhoncus quis ligula sed, tempor venenatis nulla. Duis quis placerat quam.";

    TextDecorator
        .decorate(tvContent, text)
        .setTextColor(R.color.colorAccent, "Lorem", "amet")
        .setBackgroundColor(R.color.colorPrimary, "dolor", "elit")
        .strikethrough("Duis", "Praesent")
        .underline("sodales", "quam")
        .setSubscript("vitae")
        .makeTextClickable(new OnTextClickListener() {
          @Override public void onClick(View view, String text) {
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
          }
        }, false, "porta", "commodo", "tempor venenatis nulla")
        .setTextColor(android.R.color.holo_green_light, "porta", "commodo", "tempor venenatis nulla")
        .build();

    //TextDecorator
    //    .decorate(tvContent, text)
    //    .setTextColor(R.color.colorAccent, 0, 5)
    //    .setBackgroundColor(R.color.colorPrimary, 6, 11)
    //    .strikethrough(12, 26)
    //    .setTextStyle(Typeface.BOLD | Typeface.ITALIC, 27, 40)
    //    .setTypeface("serif", 70, 77)
    //    .setSuperscript(78, 86)
    //    .setSubscript(87, 92)
    //    .underline(120, 200)
    //    .blur(3, BlurMaskFilter.Blur.NORMAL, 0, 2)
    //    .makeTextClickable(new OnTextClickListener() {
    //      @Override public void onClick(View view, String text) {
    //        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    //      }
    //    }, 250, 270, false)
    //    .setTextColor(android.R.color.holo_green_light, 250, 270)
    //    .build();
  }
}
