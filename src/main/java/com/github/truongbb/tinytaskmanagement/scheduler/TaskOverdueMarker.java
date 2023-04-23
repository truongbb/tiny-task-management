package com.github.truongbb.tinytaskmanagement.scheduler;

import com.github.truongbb.tinytaskmanagement.entity.Task;
import com.github.truongbb.tinytaskmanagement.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskOverdueMarker {

    TaskRepository taskRepository;

    @Scheduled(fixedRate = 60000)
    public void scheduleFixedDelayTask() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Task> tasks = taskRepository.findAll()
                .stream().peek(task -> {
                    if (!ObjectUtils.isEmpty(task.getExpectedEndTime()) && task.getExpectedEndTime().compareTo(currentTime) < 0) {
                        task.setOverdue(true);
                    }
                }).collect(Collectors.toList());
        taskRepository.saveAll(tasks);
    }

}
