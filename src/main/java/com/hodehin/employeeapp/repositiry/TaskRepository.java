package com.hodehin.employeeapp.repositiry;

import com.hodehin.employeeapp.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select t from Task t left join fetch t.employees")
    @Override
    List<Task> findAll();

    <T>Page<T> findAllBy(Class<T> type, Pageable pageable);
}
