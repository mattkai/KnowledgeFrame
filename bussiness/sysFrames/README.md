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
# annotation class xxxx




