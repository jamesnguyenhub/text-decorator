package com.tuyenmonkey.textdecorator;

import android.content.res.ColorStateList;
import android.graphics.BlurMaskFilter;
import android.graphics.EmbossMaskFilter;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.tuyenmonkey.textdecorator.callback.OnTextClickListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tuyen Nguyen on 12/24/16.
 */
@SuppressWarnings("unused")
public class TextDecorator {
    private TextView textView;
    private String content;
    private SpannableString decoratedContent;
    private int flags;

    private TextDecorator(TextView textView, String content) {
        this.textView = textView;
        this.content = content;
        this.decoratedContent = new SpannableString(content);
        this.flags = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
    }

    public static TextDecorator decorate(TextView textView, String content) {
        return new TextDecorator(textView, content);
    }

    public TextDecorator setFlags(final int flags) {
        this.flags = flags;
        return this;
    }

    public TextDecorator underline(final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new UnderlineSpan(), start, end, flags);
        return this;
    }

    public TextDecorator underline(final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new UnderlineSpan(), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator underline(final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new UnderlineSpan(), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setTextColor(@ColorRes final int resColorId, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(), resColorId)), start, end, flags);
        return this;
    }

    public TextDecorator setTextColor(@ColorRes final int resColorId, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(), resColorId)), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setTextColor(final int resColorId, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(), resColorId)), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setBackgroundColor(@ColorRes final int colorResId, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new BackgroundColorSpan(ContextCompat.getColor(textView.getContext(), colorResId)), start, end, 0);
        return this;
    }

    public TextDecorator setBackgroundColor(@ColorRes final int colorResId, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new BackgroundColorSpan(ContextCompat.getColor(textView.getContext(), colorResId)), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setBackgroundColor(@ColorRes final int colorResId, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new BackgroundColorSpan(ContextCompat.getColor(textView.getContext(), colorResId)), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator insertBullet(final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new BulletSpan(), start, end, flags);
        return this;
    }

    public TextDecorator insertBullet(final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new BulletSpan(), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator insertBullet(final int gapWidth, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new BulletSpan(gapWidth), start, end, flags);
        return this;
    }

    public TextDecorator insertBullet(final int gapWidth, @ColorRes final int colorResId, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new BulletSpan(gapWidth, ContextCompat.getColor(textView.getContext(), colorResId)), start, end, flags);
        return this;
    }

    public TextDecorator insertBullet(final int gapWidth, @ColorRes final int colorResId, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new BulletSpan(gapWidth, ContextCompat.getColor(textView.getContext(), colorResId)), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator makeTextClickable(final OnTextClickListener listener, final int start, final int end, final boolean underlineText) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, content.substring(start, end));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(underlineText);
            }
        }, start, end, flags);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public TextDecorator makeTextClickable(final OnTextClickListener listener, final boolean underlineText, final String... texts) {
        int index;
        int fromIndex = 0;
        for (final String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        listener.onClick(view, text);
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(underlineText);
                    }
                }, index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public TextDecorator makeTextClickable(final OnTextClickListener listener, final boolean underlineText, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            final String text = content.substring(start, end);
            decoratedContent.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, text);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(underlineText);
                }
            }, start, end, flags);
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public TextDecorator makeTextClickable(final ClickableSpan clickableSpan, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(clickableSpan, start, end, flags);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public TextDecorator makeTextClickable(final ClickableSpan clickableSpan, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1){
                decoratedContent.setSpan(clickableSpan, index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public TextDecorator makeTextClickable(final ClickableSpan clickableSpan, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(clickableSpan, matcher.start(), matcher.end(), flags);
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public TextDecorator insertImage(@DrawableRes final int resId, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new ImageSpan(textView.getContext(), resId), start, end, flags);
        return this;
    }

    public TextDecorator quote(final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new QuoteSpan(), start, end, flags);
        return this;
    }

    public TextDecorator quote(final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new QuoteSpan(), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator quote(final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new QuoteSpan(), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator quote(@ColorRes final int colorResId, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new QuoteSpan(ContextCompat.getColor(textView.getContext(), colorResId)), start, end,
                flags);
        return this;
    }

    public TextDecorator quote(@ColorRes final int colorResId, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new QuoteSpan(ContextCompat.getColor(textView.getContext(), colorResId)), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator quote(@ColorRes final int colorResId, Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new QuoteSpan(ContextCompat.getColor(textView.getContext(), colorResId)), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator strikethrough(final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new StrikethroughSpan(), start, end, flags);
        return this;
    }

    public TextDecorator strikethrough(final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new StrikethroughSpan(), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator strikethrough(final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new StrikethroughSpan(), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setTextStyle(final int style, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new StyleSpan(style), start, end, flags);
        return this;
    }

    public TextDecorator setTextStyle(final int style, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1){
                decoratedContent.setSpan(new StyleSpan(style), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setTextStyle(final int style, Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new StyleSpan(style), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator alignText(final Layout.Alignment alignment, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new AlignmentSpan.Standard(alignment), start, end, flags);
        return this;
    }

    public TextDecorator alignText(final Layout.Alignment alignment, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new AlignmentSpan.Standard(alignment), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator alignText(final Layout.Alignment alignment, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.matches()) {
            decoratedContent.setSpan(new AlignmentSpan.Standard(alignment), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setSubscript(final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new SubscriptSpan(), start, end, flags);
        return this;
    }

    public TextDecorator setSubscript(final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new SubscriptSpan(), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setSubscript(final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new SubscriptSpan(), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setSuperscript(final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new SuperscriptSpan(), start, end, flags);
        return this;
    }

    public TextDecorator setSuperscript(final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new SuperscriptSpan(), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setSuperscript(final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new SuperscriptSpan(), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setTypeface(final String family, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new TypefaceSpan(family), start, end, flags);
        return this;
    }

    public TextDecorator setTypeface(final String family, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new TypefaceSpan(family), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setTypeface(final String family, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new TypefaceSpan(family), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setTextAppearance(final int appearance, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new TextAppearanceSpan(textView.getContext(), appearance), start, end,
                flags);
        return this;
    }

    public TextDecorator setTextAppearance(final int appearance, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new TextAppearanceSpan(textView.getContext(), appearance), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setTextAppearance(final int appearance, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            decoratedContent.setSpan(new TextAppearanceSpan(textView.getContext(), appearance), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setTextAppearance(final int appearance, final int colorList, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new TextAppearanceSpan(textView.getContext(), appearance, colorList), start, end,
                flags);
        return this;
    }

    public TextDecorator setTextAppearance(final int appearance, final int colorList, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new TextAppearanceSpan(textView.getContext(), appearance, colorList), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setTextAppearance(final int appearance, final int colorList, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            decoratedContent.setSpan(new TextAppearanceSpan(textView.getContext(), appearance, colorList), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setTextAppearance(String family, int style, int size, ColorStateList color, ColorStateList linkColor, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new TextAppearanceSpan(family, style, size, color, linkColor), start, end,
                flags);
        return this;
    }

    public TextDecorator setTextAppearance(String family, int style, int size, ColorStateList color, ColorStateList linkColor, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new TextAppearanceSpan(family, style, size, color, linkColor), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setTextAppearance(String family, int style, int size, ColorStateList color, ColorStateList linkColor, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new TextAppearanceSpan(family, style, size, color, linkColor), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setAbsoluteSize(final int size, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new AbsoluteSizeSpan(size), start, end, flags);
        return this;
    }

    public TextDecorator setAbsoluteSize(final int size, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new AbsoluteSizeSpan(size), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setAbsoluteSize(final int size, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new AbsoluteSizeSpan(size), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setAbsoluteSize(final int size, final boolean dip, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new AbsoluteSizeSpan(size, dip), start, end, flags);
        return this;
    }

    public TextDecorator setAbsoluteSize(final int size, final boolean dip, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new AbsoluteSizeSpan(size, dip), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setAbsoluteSize(final int size, final boolean dip, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new AbsoluteSizeSpan(size, dip), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator setRelativeSize(final float proportion, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new RelativeSizeSpan(proportion), start, end, flags);
        return this;
    }

    public TextDecorator setRelativeSize(final float proportion, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1){
                decoratedContent.setSpan(new RelativeSizeSpan(proportion), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator setRelativeSize(final float proportion, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new RelativeSizeSpan(proportion), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator scaleX(final float proportion, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new ScaleXSpan(proportion), start, end, flags);
        return this;
    }

    public TextDecorator scaleX(final float proportion, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1) {
                decoratedContent.setSpan(new ScaleXSpan(proportion), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator scaleX(final float proportion, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new ScaleXSpan(proportion), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator blur(final float radius, final BlurMaskFilter.Blur style, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new MaskFilterSpan(new BlurMaskFilter(radius, style)), start, end, flags);
        return this;
    }

    public TextDecorator blur(final float radius, final BlurMaskFilter.Blur style, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1){
                decoratedContent.setSpan(new MaskFilterSpan(new BlurMaskFilter(radius, style)), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator blur(final float radius, final BlurMaskFilter.Blur style, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new MaskFilterSpan(new BlurMaskFilter(radius, style)), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public TextDecorator emboss(final float[] direction, final float ambient, final float specular, final float blurRadius, final int start, final int end) {
        checkIndexOutOfBoundsException(start, end);
        decoratedContent.setSpan(new MaskFilterSpan(new EmbossMaskFilter(direction, ambient, specular, blurRadius)), start, end,
                flags);
        return this;
    }

    public TextDecorator emboss(final float[] direction, final float ambient, final float specular, final float blurRadius, final String... texts) {
        int index;
        int fromIndex = 0;
        for (String text : texts) {
            while ((index = content.indexOf(text,fromIndex)) != -1){
                decoratedContent.setSpan(new MaskFilterSpan(new EmbossMaskFilter(direction, ambient, specular, blurRadius)), index, index + text.length(), flags);
                fromIndex += index + text.length();
            }
        }
        return this;
    }

    public TextDecorator emboss(final float[] direction, final float ambient, final float specular, final float blurRadius, final Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            decoratedContent.setSpan(new MaskFilterSpan(new EmbossMaskFilter(direction, ambient, specular, blurRadius)), matcher.start(), matcher.end(), flags);
        }
        return this;
    }

    public void build() {
        textView.setText(decoratedContent);
    }

    private void checkIndexOutOfBoundsException(final int start, final int end) {
        if (start < 0) {
            throw new IndexOutOfBoundsException("start is less than 0");
        } else if (end > content.length()) {
            throw new IndexOutOfBoundsException("end is greater than content length " + content.length());
        } else if (start > end) {
            throw new IndexOutOfBoundsException("start is greater than end");
        }
    }
}