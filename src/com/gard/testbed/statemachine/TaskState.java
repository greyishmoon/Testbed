package com.gard.testbed.statemachine;

import com.gard.testbed.activities.TaskEntity;

/**
 * Created by Chris on 14/02/2016.
 */
public interface TaskState {

    public TaskEntity getTaskData();
    public void complete(ActivityContext context, String taskEntityName);

}
