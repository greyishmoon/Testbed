package com.gard.testbed.EventBus;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

/**
 * Created by Chris on 06/03/2016..
 */
public class EventBus<T> {

    private final Subject<T, T> subject;

    public static <T> EventBus<T> createSimple() {
        return new EventBus<>();//PublishSubject is created in constructor
    }

    public static <T> EventBus<T> createRepeating(int numberOfEventsToRepeat) {
        return new EventBus<>(ReplaySubject.<T>createWithSize(numberOfEventsToRepeat));
    }

    public static <T> EventBus<T> createWithLatest() {
        return new EventBus<>(BehaviorSubject.<T>create());
    }


    public EventBus() {
        this(PublishSubject.<T>create());
    }

    public EventBus(Subject<T, T> subject) {
        this.subject = subject;
    }


    public <E extends T> void post(E event) {
        subject.onNext(event);
    }

    public Observable<T> observe() {
        return subject;
    }


    public <E extends T> Observable<E> observeEvents(Class<E> eventClass) {
        return subject.ofType(eventClass);//pass only events of specified type, filter all other
    }

}
