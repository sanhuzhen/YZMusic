 

# ZWT的Readme

## app简介

我们的YZMusic是一个类网易云的软件，实现了搜索，音乐播放，MV播放等功能。

这里是我们的app的简单演示

<img src="https://github.com/sanhuzhen/YZMusic/blob/ZWT/zwt%E7%9A%84README/WeChat_20240727093935%2000_00_00-00_00_30.gif?raw=true" style="zoom:50%;" />

<img src="https://github.com/sanhuzhen/YZMusic/blob/ZWT/zwt%E7%9A%84README/WeChat_20240727102026%2000_00_00-00_00_30.gif?raw=true" style="zoom:50%;" />

## 我负责的模块及功能

我主要负责了search，mvplay，mine，hot，login这几个模块，关于技术会在对应模块进行解释

### Login模块

<img src="https://github.com/sanhuzhen/YZMusic/blob/ZWT/zwt%E7%9A%84README/WeChat_20240727103028%2000_00_00-00_00_30.gif?raw=true" style="zoom:50%;" />

主要使用了vp2，MVVM架构，sp数据库以及一个计时器完成了登录模块。实现了一个简单的登录并可以向Mine这个模块进行数据传递

### Mine模块

<img src="https://github.com/sanhuzhen/YZMusic/blob/ZWT/zwt%E7%9A%84README/WeChat_20240727103407%2000_00_00-00_00_30.gif?raw=true" />

在这个模块,我主要使用了MVVM架构，sp数据库技术，sqlite数据库。在头像位置做了一个本地的图片更改，在进入歌单位置以后用popuMenu可以进行分享与“下载“（接口问题只能实现伪下载） ，在网络请求中使用了ProgressBar来进行加载显示。

### Hot模块

<img src="https://github.com/sanhuzhen/YZMusic/blob/ZWT/zwt%E7%9A%84README/WeChat_20240727103816%2000_00_00-00_00_30.gif?raw=true" style="zoom:50%;" />

这个模块其实东西比较重复，相对来说，比较新的东西就是协调者布局和共享元素动画（动画也不是很明显）。然后就是简单的rv+网络请求，再后面在歌单界面用ProgressBar进行加载动画。

### Search模块

<img src="https://github.com/sanhuzhen/YZMusic/blob/ZWT/zwt%E7%9A%84README/WeChat_20240727104229%2000_00_00-00_00_30.gif?raw=true" style="zoom:50%;" />

这里使用了vp2作为底部的页面布局，就是传统的rv+网络请求。稍微好一点的个人认为就是用了一个共享的ViewModel进行通信，拿到了在searchView里面的字符，在三个fragment中分别实现了各自对应的网络请求

### MV播放模块

<img src="https://github.com/sanhuzhen/YZMusic/blob/ZWT/zwt%E7%9A%84README/WeChat_20240727104800%2000_00_00-00_00_30.gif?raw=true" style="zoom:67%;" />

这里的mv播放使用了第三方的dkPlayer，一样的分享，稍微出色一点的地方是评论使用了bottomsheetdialog。并且在翻阅评论的时候并不会影响mv的播放

## 体现的技术

- 运用了MVVM框架，使用ViewModel来缓存数据。
- 使用了rxjava与retrofit进行网络请求，在其他线程进行请求，并在主线程接收数据。
- 在actvity与fragment之间的通信选择使用ViewModel来实现，实现了想要的数据共享
- 使用了sp以及SQLite数据库进行储存数据
- 使用dkPlayer这个第三方进行了视频播放

## 个人总结

总的来说，这次考核真的学到了很多的东西，比如 AlertDialog以及其他的dialog，还有PopupMenu等，然后一直以来没有分包意识的我学会了规范的分包。在这里真的很感谢我的队友，给我提供帮助，在我有思路但是不知道该使用什么控件之时对我使用了醍醐灌顶大法。而且因为相对来说底子没有那么好也一直比较照顾我

### 不足

代码还是比较臃肿，很多东西本可以没有那么麻烦，但还是写了很多。

有一点点懒惰，有些时候想做一些比较取巧的方法来完成自己的东西。

代码相对来说还是不是很规范，写起来乱七八糟的想到什么写什么，起名也是一塌糊涂

这一点一直被队友批评（他说的对），ui就是一坨，毫无美观可言。

### 展望

网校学习氛围深深打动了我，一个个都非常的能学，从他们身上学到了“卷”这个东西的终极奥义——随地大小学。我也认识到了自己的学识浅薄，将会更加努力地完成对于安卓的学习。