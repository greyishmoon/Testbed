package com.gard.testbed.statemachine;

import com.gard.testbed.abstractions.TaskEntity;

/**
 * Created by Chris on 14/02/2016..
 */
public interface TaskState {

    TaskEntity getTaskData();
    void complete(ActivityContext context, String taskEntityName);

}
