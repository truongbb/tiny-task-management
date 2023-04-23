package com.github.truongbb.tinytaskmanagement.dto;

import com.github.truongbb.tinytaskmanagement.statics.TaskStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

@Data
@Component
public class TaskForm {

    Long id;

    String name;

    String description;

    TaskStatus status;

    Long assignedUser;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime expectedEndTime;

}
