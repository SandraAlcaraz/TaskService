package com.sandra.service.task;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final static Integer id= 12;
    private final static Integer userId=13;
    private final static String title="cooking";
    private final static String description="cooking cookies";
    private final static String difficulty="hard";
    private final static String dueDate="11/11/2019";
    private final static String reminder="10/11/2019";
    private final static Boolean done= true;
    private final static String completionDate="11/11/2019";
    @RequestMapping(value = "/employee", method = RequestMethod.GET)

    public Task firstPage() {

        Task task = new Task();
        task.setId(id);
        task.setUserId(userId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setReminder(reminder);
        task.setDone(done);
        task.setCompletionDate(completionDate);
        return task;
    }

}
