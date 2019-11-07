# Android Ratingbar demo
This Ratingbar android demo contains the code to show the rating bar on view

```
       dependencies {
	   implementation 'com.github.ghanshyam34:RatingBar:7cb4390547'
	}
```

##Getting Started

for showing ratingbar on view , you have to put following line of code in XML where you want to use

```
<com.gs.myratingbarlibrary.MyRatingBar
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:space="0dp"
    app:maxCount="5"
    app:activeColor="@android:color/black"
    app:rating="3"
    app:myRatingbarStyle="medium"
  />
  ```


   OR
you can also create it porgrammatically and can customize to fit according to your requirement without extra padding and fit to center according to your requirement etc...

```
  MyRatingBar myRatingBar = new MyRatingBar(this);
  myRatingBar.setMaxCount(5);
  myRatingBar.setRatingCount(3.5f);
  myRatingBar.setRatingStyle(MyRatingBar.STYLE_LARGE);
  myRatingBar.setBackgroundColor(Color.WHITE);
  setContentView(myRatingBar);
```

Please see the below example in Screen shots

<img src="https://cloud.githubusercontent.com/assets/13448460/21808513/a865643c-d769-11e6-8629-91e7cdd1d4a7.png" data-canonical-src="https://cloud.githubusercontent.com/assets/13448460/21808513/a865643c-d769-11e6-8629-91e7cdd1d4a7.png" width="320" height="450" />



## License


```
MIT License

Copyright (c) 2019 Ghanshyamp

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
  ```
