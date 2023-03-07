package com.hodehin.employeeapp.repositiry;

import com.hodehin.employeeapp.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    <T>Page<T> findAllBy(Class<T> type, Pageable pageable);
}
