# 本模块主要是将MVVM进行实例说明并采用Retrofit、livedata、RxJava、协成、Flow进行数据比对
# kotlin的协成引入配合livedata非常方便调用，不在沿用之前的网络接口需要设置回调方法，
直接以同步的方式实现异步的方法。而且不需要利用Handler或者Rxjava进行线程切换了，直接通知LiveData数据进行变更

# ViewDataBinding 针对将xml进行简单数据处理，如果遇到复杂的数据时就不是很推荐。
# DataBinding 可以针对xml进行复杂数据管理，进行绑定对象操作。在xml中引入
# <layout> <data></data> </layout> 规则进行数据绑定

