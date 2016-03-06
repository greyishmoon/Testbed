package com.gard.testbed.EventBus;


/**
 * Created by Chris on 06/03/2016..
 */
public class Kernel {

    private static final EventBus<IEvent> eventBus = EventBus.createWithLatest();//singleton bus
    private static final ActivityManager activityManager = new ActivityManager(eventBus);
    private static final EngagementAgent engagementAgent = new EngagementAgent(eventBus);
    private static final ObserveAll observeAllEvents = new ObserveAll(eventBus);
    private static final ObserveBoth observeBothEvents = new ObserveBoth(eventBus);

    public static void main(String[] args) {

//        Subscription mSubscription;

//        mSubscription = eventBus.observe().subscribe(new Action1<IEvent>() {
//            @Override
//            public void call(IEvent e) {
//                System.out.println(e.getName());
//            }
//        });


        eventBus.post(new EngagementEvent("EngagementEvent 1 - kernel post", "Eng msg 1"));
        eventBus.post(new ActivityEvent("ActivityEvent 1 - kernel post", "Act msg 1"));
        eventBus.post(new EngagementEvent("EngagementEvent 2 - kernel post", "Eng msg 2"));
        eventBus.post(new ActivityEvent("ActivityEvent 2 - kernel post", "Act msg 2"));

        System.out.println();
        eventBus.post(new OtherEvent("OtherEvent 1 - kernel post"));
        eventBus.post(new OtherEvent("OtherEvent 2 - kernel post"));

        System.out.println();
        engagementAgent.postEventTest("EngagementEvent 3 - EA post", "Eng msg 3");
        activityManager.postEventTest("ActivityEvent 3 - AM post", "Act msg 3");

        System.out.println();

        activityManager.unsuscribe();
        engagementAgent.unsuscribe();

        eventBus.post(new ActivityEvent("AFTER AM Event", "Act msg post"));
        eventBus.post(new EngagementEvent("AFTER EA Event", "Eng msg post"));


        System.out.println("END");

    }
}
