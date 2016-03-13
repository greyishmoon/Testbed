package com.gard.testbed.engine.eventBusx.test;


import com.gard.testbed.abstractions.IEvent;
import com.gard.testbed.engine.EventBus;
import com.gard.testbed.engine.eventBusx.events.ActivityEvent;
import com.gard.testbed.engine.eventBusx.events.MessageType;

/**
 * Created by Chris on 06/03/2016..
 */
public class KernelTest {

    private static final EventBus<IEvent> eventBus = EventBus.createWithLatest();//singleton bus
    private static final ActivityManagerTest activityManager = new ActivityManagerTest(eventBus);
    private static final EngagementAgentTest engagementAgent = new EngagementAgentTest(eventBus);
    private static final ObserveAllTest observeAllEvents = new ObserveAllTest(eventBus);
    private static final ObserveBothTest observeBothEvents = new ObserveBothTest(eventBus);

    public static void main(String[] args) {

//        Subscription mSubscription;

//        mSubscription = eventBusx.observe().subscribe(new Action1<IEvent>() {
//            @Override
//            public void call(IEvent e) {
//                System.out.println(e.getTarget());
//            }
//        });


        eventBus.post(new EngagementEvent("EngagementEvent 1 - kernel post", MessageType.COMPLETE, "Eng msg 1"));
        eventBus.post(new ActivityEvent("ActivityEvent 1 - kernel post", MessageType.COMPLETED, "Act msg 1"));
        eventBus.post(new EngagementEvent("EngagementEvent 2 - kernel post", MessageType.COMPLETE, "Eng msg 2"));
        eventBus.post(new ActivityEvent("ActivityEvent 2 - kernel post", MessageType.COMPLETED, "Act msg 2"));

        System.out.println();
        eventBus.post(new OtherEvent("OtherEvent 1 - kernel post", MessageType.COMPLETE, "Other msg 1"));
        eventBus.post(new OtherEvent("OtherEvent 2 - kernel post", MessageType.COMPLETE, "Other msg 2"));

        System.out.println();
        engagementAgent.postEventTest("EngagementEvent 3 - EA post", "Eng msg 3");
        activityManager.postEvent("ActivityEvent 3 - AM post", MessageType.COMPLETED, "Act msg 3");

        System.out.println();

        activityManager.unsuscribe();
        engagementAgent.unsuscribe();

        eventBus.post(new ActivityEvent("AFTER AM Event", MessageType.COMPLETED, "Act msg post"));
        eventBus.post(new EngagementEvent("AFTER EA Event", MessageType.COMPLETE, "Eng msg post"));


        System.out.println("END");

    }
}
