package com.github.truongbb.tinytaskmanagement.service;

import com.github.truongbb.tinytaskmanagement.dto.TaskForm;
import com.github.truongbb.tinytaskmanagement.entity.CustomOAuth2User;
import com.github.truongbb.tinytaskmanagement.entity.Task;
import com.github.truongbb.tinytaskmanagement.entity.User;
import com.github.truongbb.tinytaskmanagement.repository.TaskRepository;
import com.github.truongbb.tinytaskmanagement.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService {

    UserRepository userRepository;

    TaskRepository taskRepository;


    public List<Task> getOwnTask() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = customOAuth2User.getEmail();
        User user = userRepository.findByUsername(email).orElseThrow(NullPointerException::new);
        return taskRepository.findByAssignedUser(user.getId());
    }

    public void saveTask(TaskForm taskForm) {
        Task task = new Task();
        BeanUtils.copyProperties(taskForm, task);

        if (!ObjectUtils.isEmpty(taskForm.getId())) {
            task.setId(taskForm.getId());
        }
        if (ObjectUtils.isEmpty(task.getId())) {
            task.setCreatedDateTime(LocalDateTime.now());
        }
        User user = userRepository.findById(taskForm.getAssignedUser()).orElseThrow(NullPointerException::new);
        task.setAssignedUser(user);

        if (!ObjectUtils.isEmpty(task.getExpectedEndTime()) && task.getExpectedEndTime().compareTo(LocalDateTime.now()) < 0) {
            task.setOverdue(true);
        }

        taskRepository.save(task);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(NullPointerException::new);
        taskRepository.delete(task);
    }
}
