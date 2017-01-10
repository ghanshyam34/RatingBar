# Android Ratingbar demo
This Ratingbar android demo contains the code to show the rating bar on view

##Getting Started

for showing ratingbar on view , you have to put following line of code in XML where you want to use

```<com.gs.myratingbarlibrary.MyRatingBar
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

![screenshot_2017-01-10-19-03-58](https://cloud.githubusercontent.com/assets/13448460/21808513/a865643c-d769-11e6-8629-91e7cdd1d4a7.png)

<img src="https://camo.githubusercontent.com/..." data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="200" height="400" />
