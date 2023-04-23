package com.github.truongbb.tinytaskmanagement.repository;

import com.github.truongbb.tinytaskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.assignedUser.id = :assignedUserId")
    List<Task> findByAssignedUser(Long assignedUserId);


}
