package com.zoyo.core.common.rx;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * ---日期----------维护人---------
 * 2017/11/24     zuoyouming
 */
public class RxBus {

    private final FlowableProcessor<Object> bus;

    /**
     * PublishSubject只会把在订阅发生的时间点之后来自原始Flowable的数据发射给观察者
     */
    private RxBus() {
        bus = PublishProcessor.create().toSerialized();
    }

    public static RxBus getInstance() {
        return RxBusHolder.instance;
    }

    private static class RxBusHolder {
        private static final RxBus instance = new RxBus();
    }


    /**
     * 提供了一个新的事件
     *
     * @param o
     */
    public void post(Object o) {
        bus.onNext(o);
    }

    /**
     * 根据传递的eventType类型返回特定类型(eventType)的被观察者
     *
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return bus.ofType(eventType);   //根据类型,筛选post()发送的数据,过滤获取指定的上游事件
    }

    /**
     * 封装默认订阅
     *
     * @param eventType
     * @param action
     * @param <T>
     * @return
     */
    public <T> Disposable toDefaultFlowable(Class<T> eventType, Consumer<T> action) {
        return bus.ofType(eventType).compose(RxUtil.<T>rxSchedulerHelper()).subscribe(action);
    }


    /**
     * 用于传递简单数据,不需特意创建JavaBean
     *
     * @param code
     * @param o
     */
    public void defaultPost(int code, Object o) {
        bus.onNext(new RxBusBaseMessage(code, o));
    }

    public Flowable<RxBusBaseMessage> toDefaultFlowable(Class<RxBusBaseMessage> eventType, final int code) {

        return bus.ofType(eventType)
                .filter(new Predicate<RxBusBaseMessage>() {
                    @Override
                    public boolean test(@NonNull RxBusBaseMessage rxBusBaseMessage) throws Exception {
                        //过滤code和eventType都相同的事件
                        return rxBusBaseMessage.getCode() == code;
                    }
                });
    }
}
