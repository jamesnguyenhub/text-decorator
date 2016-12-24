package com.tuyenmonkey.textdecorator;

import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 * Created by Tuyen Nguyen on 12/24/16.
 */

public class TextDecorator {
  private TextView textView;
  private String content;
  private SpannableString decoratedContent;
  private int flag;

  private TextDecorator(TextView textView, String content) {
    this.textView = textView;
    this.content = content;
    this.decoratedContent = new SpannableString(content);
    this.flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
  }

  public static TextDecorator decorate(TextView textView, String content) {
    return new TextDecorator(textView, content);
  }

  public TextDecorator setFlag(int flag) {
    this.flag = flag;
    return this;
  }

  public TextDecorator underline(int start, int end) {
    checkIndexOutOfBoundsException(start, end);
    decoratedContent.setSpan(new UnderlineSpan(), start, end, flag);
    return this;
  }

  public TextDecorator setTextColor(@ColorRes int resColorId, int start, int end) {
    checkIndexOutOfBoundsException(start, end);
    decoratedContent.setSpan(new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(),
        resColorId)), start, end, flag);
    return this;
  }

  public TextDecorator setBackgroundColor(@ColorRes int resColorId, int start, int end) {
    checkIndexOutOfBoundsException(start, end);
    decoratedContent.setSpan(new BackgroundColorSpan(ContextCompat.getColor(textView.getContext(),
        resColorId)), start, end, 0);
    return this;
  }

  public void build() {
    textView.setText(decoratedContent);
  }

  private void checkIndexOutOfBoundsException(int start, int end) {
    if (start < 0) {
      throw new IndexOutOfBoundsException("start is less than 0");
    } else if (end > content.length()) {
      throw new IndexOutOfBoundsException("end is greater than content length " + content.length());
    } else if (start > end) {
      throw new IndexOutOfBoundsException("start is greater than end");
    }
  }
}
