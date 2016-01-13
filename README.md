# This library is not release formally.

# AndroidExtension

AndroidExtension try to provide useful and smooth extensions in kotlin for android developer.

## Kotlin Version

As kotlin may changes big in different version, we will try to adapt the latest version as soon as possible. The library
 is compatible with latest Kotlin 1.0.0-beta-4584. 

## Reference

```
repositories {
    jcenter()
}

compile 'com.ohmerhe.kotlinthree:andex:0.1.2@aar'
```

## Usage

### Get Resource

```
getString(context, R.String.app_name)
getDrawable(context, R.drawable.ic_launcher)
getColor(context, R.color.red)
```

### Find View 

in `activity/fragment/view` or use corresponding instance to call.

```
val imageView: ImageView = findView(R.id.image_view)

val imageView = findView<ImageView>(R.id.image_view)
```
### Toast

in `activity/service/fragment` or use corresponding instance to call.

```
// Toast.LENGTH_SHORT is optional
toastText(R.String.toast, Toast.LENGTH_SHORT) 

toastText("toast", Toast.LENGTH_SHORT) 

// toastView is custom view
toastView(toastView, Toast.LENGTH_SHORT) 
```

#AndroidExtension

AndroidExtension用kotlin为安卓开发者提供的扩展库，让安卓开发变得更简单。

## Kotlin版本

由于kotlin版本更新比较快，我们会尽力让我们的库跟上最新的版本，目前为止我们用最新的kotlin版本1.0.0-beta-4584。

## 项目依赖

```
repositories {
    jcenter()
}

compile 'com.ohmerhe.kotlinthree:andex:0.1.2@aar'
```

## 使用方法

### Resource

```
getString(context, R.String.app_name)
getDrawable(context, R.drawable.ic_launcher)
getColor(context, R.color.red)
```

### Find View 

在`activity/fragment/view`中这样调用：

```
val imageView: ImageView = findView(R.id.image_view)

val imageView = findView<ImageView>(R.id.image_view)
```
也可以使用对应的类的对象调用。

### Toast

在activity、service或者fragment里面可以这样调用：

```
// Toast.LENGTH_SHORT 这个参数是可选参数
toastText(R.String.toast, Toast.LENGTH_SHORT) 

toastText("toast", Toast.LENGTH_SHORT)

// toastView是你自定义的视图
toastView(toastView, Toast.LENGTH_SHORT) 
```
也可以使用对应的类的对象调用。

#License


    Copyright 2015 KotlinThree

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.