# 游嘉超的Readme

## APP简介

这是由游嘉超和张炜涛联合开发的一款音乐播放App。

## 我负责的模块

我主要负责 app模块，lib_base模块，module_musicplayer模块，module_recommend模块，module_songlist模块。

### 技术栈

1. **采用了MVVM架构**
2. **使用retrofit和rxjava进行网络请求**
3. **简单使用了Paging3和flow进行数据刷新**
4. **使用LiveData进行观察以确保UI刷新**
5. **使用第三方库media3进行音乐播放**
6. **简单使用了Service进行后台播放**
7. **使用Handler进行UI的刷新**

下面介绍一下各个模块的功能和实现：

### app模块

该模块主要是使用了bottomNavigationView+ViewPaper2搭建底部导航栏，以及使用了DrawerLayout+NavigationView实现抽屉式布局，以及绑定了MusicPlayerService实现了网易云类似音乐小窗播放器，以及使用了bottomsheetdialog实现了播放列表的效果（点击可以切歌）。

<img src="D:\通用\gif\13366470843345238.gif" style="zoom:80%;" />

### lib_base模块

该模块主要是对Activity和Fragment一些东西的封装，以及我队友写的SQLite数据库的封装，对ViewBinding进行了封装，避免了写重复代码（在这里，被学长批评了，viewBinding没有随Fragment的消失而结束，而是造成了内存泄漏）

### module_recommend模块

该模块主要是App的首页，实现了无限轮播图，以及一些rv的使用，实现了下拉刷新

无限轮播是在ViewPaper2适配器中将返回的item数量设为无限大（Int.MAX_VALUE），这样就滑不到尽头了，自动滑动就是；利用Timer来进行定时任务，实现自动滑动

下拉刷新则是利用SwipeRefreshLayout布局实现，在改个颜色就完成了

<img src="D:\通用\gif\13366471590853970.gif" style="zoom:80%;" />

### module_songlist模块

这个模块，我主要就写了两个Activity，一个歌曲列表，一个歌手列表。歌曲列表和歌手列表全用上了协调者布局，利用折叠，实现好看的动画效果。

建立这个模块的初衷是因为太多地方要用到一样的内容，想着跟我队友一起用这里面的一些布局，但我队友没用（哭）。（不知道是不是跨模块的原因，元素共享动画会出一些bug，所以就没有用）。

<img src="D:\通用\gif\13366472101506769.gif" style="zoom:80%;" />



<img src="D:\FFOutput\WeChat_20240727093006 00_00_00-00_00_30.gif" style="zoom:80%;" />

### module_musicplayer模块

这块是我用时最长的模块，出bug出死我了，耽误了队伍的进展，队友都要骂人了（哭）

这块主要是使用Service进行后台播放，在Activity中进行绑定。第三方播放器用的是media3

实现的功能有：

- 实现了一个音乐播放器应该有的播放，暂停，下一首，上一首，顺序播放，单曲循环
- 实现了碟旋转动画效果
- 实现了播放列表，简单的bottomsheetdialog，点击切歌
- 实现了分享功能
- 简单的Activity进入和销毁的动画
- 进度条跟随播放进度、拖动进度条改变播放进度、音频基本信息的更新展示
- 评论这一块简单使用了flow和paging3，用来刷新数据

<img src="D:\FFOutput\WeChat_20240727102315 00_00_00-00_00_30.gif" style="zoom:67%;" />

<img src="D:\FFOutput\WeChat_20240727101943 00_00_00-00_00_30.gif" style="zoom:67%;" />

## 个人感悟

在为期一个月的考核中，我也是突破了自己，不敢相信自己可以写出这样的App；在网校的学习生活中，从大一的啥也不懂，到现在可以写出这样一个App，我认为自己是真正成长了的，对安卓这条路有了一定的见解。但在自己的学习过程中还是有不足的，比如未去深入了解一个先进技术，如协程，自定义View，滑动冲突等，（自己懒了，并未深入学习），当然也觉得自己对各个知识块的认知有点片面了。总之，很感谢网校给了这么一个平台，学习到了知识，让我对以后的学习生涯有了自己的一点规划。同时，网校的学习氛围还是非常好的，每个人都非常“卷”，这让我也有动力学习下去。
