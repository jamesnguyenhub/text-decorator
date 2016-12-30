# Text Decorator
[![Build Status](https://travis-ci.org/nntuyen/text-decorator.svg?branch=master)](https://travis-ci.org/nntuyen/text-decorator)

Decorate your TextView easily

![](screenshot/screenshot2.gif)

## Usage

```java
TextDecorator
        .decorate(textView, text)
        .setTextColor(R.color.colorAccent, 0, 5)
        .setBackgroundColor(R.color.colorPrimary, 6, 11)
        .strikethrough(12, 26)
        .setTextStyle(Typeface.BOLD | Typeface.ITALIC, 27, 40)
        .setTypeface("serif", 70, 77)
        .setSuperscript(78, 86)
        .setSubscript(87, 92)
        .underline(120, 200)
        .blur(3, BlurMaskFilter.Blur.NORMAL, 0, 2)
        .makeTextClickable(new OnTextClickListener() {
          @Override public void onClick(View view, String text) {
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
          }
        }, 250, 270, false)
        .build();
```
or

```java
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
```

## Download

Add it in your root build.gradle at the end of repositories:

```
allprojects {
  repositories {
    ...
    maven { 
        url "https://oss.sonatype.org/content/repositories/snapshots" 
    }
}
```

```
dependencies {
    compile 'com.tuyenmonkey:text-decorator:1.0.0-SNAPSHOT'
}
```

## License

    Copyright 2016 Tuyen Monkey

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
