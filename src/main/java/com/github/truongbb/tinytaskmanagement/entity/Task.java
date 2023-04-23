package com.github.truongbb.tinytaskmanagement.entity;

import com.github.truongbb.tinytaskmanagement.statics.TaskStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tasks")
public class Task {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    String description;

    @Enumerated(EnumType.STRING)
    TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "assign_user_id")
    User assignedUser;

    @Column(name = "created_date_time")
    LocalDateTime createdDateTime;

    @Column(name = "expected_end_time")
    LocalDateTime expectedEndTime;

    @Column(name = "overdue")
    Boolean overdue;

}
