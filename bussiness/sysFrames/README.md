# 本模块主要是将MVVM进行实例说明并采用Retrofit、livedata、RxJava、协成、Flow进行数据比对
# kotlin的协成引入配合livedata非常方便调用，不在沿用之前的网络接口需要设置回调方法，
直接以同步的方式实现异步的方法。而且不需要利用Handler或者Rxjava进行线程切换了，直接通知LiveData数据进行变更

# ViewDataBinding 针对将xml进行简单数据处理，如果遇到复杂的数据时就不是很推荐。
# DataBinding 可以针对xml进行复杂数据管理，进行绑定对象操作。在xml中引入
# <layout> <data></data> </layout> 规则进行数据绑定

# webView 和 原生安卓交互

# 通过 loadUrl 、evaluteJavaScript进行调用js代码操作
# 安全漏洞包括
# 1、接口造成任意访问本地代码 API4.2之间定义本地代码需要加上@addJavaScript的注释
# 2、内置的searchBoxJavaBridge存在远程代码执行漏洞 API4.2 之前存在该漏洞，4.2以后不在有漏洞了
# webView.removeJavaScriptInterface("searchBoxJavaBridge")
# 3、存在获取明文密码  websetting.setPassword(false)
# 4、存在访问本地文件信息 websetting.allowFileAccess等

# JetPack Hilt

# 1、在Application上添加@HiltAndroidApp注释,表明添加Hilt全局组件后面就会给@AndroidEntryPoint
# 注释的类提供组件。
# 2、引用的数据类 通过对构造函数添加@Inject注释 进行实例化操作
# 3、在Activity 或者 Fragment上添加@AndroidEntryPoint 并@Inject引入数据类
# 4、对引用接口定义的数据类，需要通过创建Module类和结合接口实现类进行接口实例化、
# @Module
# @InstallIn(ActivityComponent::class)
# @Binds

# 对于多接口需要添加
# @Qualifier
# @Retention(AnnotationRetention.BINARY)
# annotation class xxxx 注释类

# MMKV 是有腾讯推出的轻量级存储框架

# 为了解决我们常用的SharePreference框架中存在的缺点，
# （1）不能进行跨进程
# （2）多线程中进行效率低，执行commit、put操作时需要对资源进行锁操作
# MMKV可以解决这个问题同时针对存储采用了protoBuf（google提出的数据结构序列化）协议提升存储效率

# navigation

# 为了解决以前框架常用的在主页面通过Activity+Fragment进行Fragment页面切换
# 如果遇到多个Fragment在界面进行切换时就会让代码变得臃肿
# navigation的导航框架会减少Activity的代码编写，只需要通过在res文件夹下创建navigation文件
# 同时在该文件创建naviGraph.xml文件里面就可以编写多种fragment进行跳转切换了。
# 不仅在xml中定义每个fragment跳转的Action, 还需要在每个Fragment代码界面中通过
# Navigation.findNavController(xx).navigate(xxxx)代码进行跳转

# Room 持久化存储

# 通过Entity("xxxx")定义表格 并通过@Primary、@ColumnInfo设置字段
# 创建Dao接口并添加@Dao注解和@Insert、@Query、@Delete、@Update
# 创建数据库管理类需要继承RoomDataBase




