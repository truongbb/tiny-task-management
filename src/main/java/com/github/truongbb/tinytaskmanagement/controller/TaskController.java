package com.github.truongbb.tinytaskmanagement.controller;

import com.github.truongbb.tinytaskmanagement.dto.TaskForm;
import com.github.truongbb.tinytaskmanagement.entity.Task;
import com.github.truongbb.tinytaskmanagement.service.TaskService;
import com.github.truongbb.tinytaskmanagement.service.UserService;
import com.github.truongbb.tinytaskmanagement.statics.TaskStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    UserService userService;

    @RequestMapping("/list")
    public String getOwnTask(Model model) {
        List<Task> tasks = taskService.getOwnTask();
        model.addAttribute("tasks", tasks);
        return "index.html";
    }

    @GetMapping("/new/{status}")
    public String showNewTaskForm(@PathVariable(name = "status") String status, TaskForm task, Model model) {
        model.addAttribute("users", userService.findAll());
        task.setStatus(TaskStatus.valueOf(status));
        model.addAttribute("task", task);
        return "new-task";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateTaskForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("update-task");
        Task task = taskService.findById(id);
        mav.addObject("task", task);
        mav.addObject("users", userService.findAll());
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks/list";
    }

    @PostMapping
    public String saveTask(@ModelAttribute("task") TaskForm task) {
        taskService.saveTask(task);
        return "redirect:/tasks/list";
    }

}
