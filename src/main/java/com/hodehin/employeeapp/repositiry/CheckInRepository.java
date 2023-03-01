package com.hodehin.employeeapp.repositiry;

import com.hodehin.employeeapp.model.ClockInOutEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepository extends JpaRepository<ClockInOutEmployee,Long> {
}
