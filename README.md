# Works Widget

This project contains basic widget for helping you tackle design problem such as layout with constrained ratio.

## ConstrainedFrameLayout
A FrameLayout subclass which allows you to create a width height ratio constrained layout

<center>
![](screenshot/fig1.png)
<br/>
Figure. 1 - Constrained FrameLayout
</center>

**Layout examples:**
``` xml
<view
            class="works.ConstrainedFrameLayout"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            works:ratioHorizontal="integer"
            works:ratioVertical="integer"
            works:keep="width|height"/>
``` 

This widget is especially useful for handling ImageView dimension. 

## Usage

### Adding as dependency

**Manual**
 * [Download AAR](https://github.com/yunarta/works-widget/releases/download/v1.0.1/works-widget-1.0.1.aar)
 * Put the AAR in the **libs** subfolder of your Android project
 * If you are using gradle you can use this dependency setting below
``` groovy
compile(name:'works-widget-1.0.1', ext:'aar')
```

or

**Gradle dependency**

``` groovy
compile 'com.mobilesolutionworks:works-widget:1.0@aar'
```

**Maven dependency**

``` xml
<dependency>
	<groupId>com.mobilesolutionworks</groupId>
	<artifactId>works-widget</artifactId>
	<version>1.0</version>
	<type>pom</type>
</dependency>
```
