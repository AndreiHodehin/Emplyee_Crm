package com.hodehin.employeeapp.repositiry;


import com.hodehin.employeeapp.dto.EmployeeCheckListDto;
import com.hodehin.employeeapp.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select new com.hodehin.employeeapp.dto.EmployeeSimpleDto(e.id,e.firstName,e.lastName,e.department.name,e.personalInfo.birthDate,e.personalInfo.address,e.personalInfo.phoneNumber,e.employeeDetail.hired,e.employeeDetail.salary) from Employee e ")
    <T>Page<T> findAllBy(Class<T> type, Pageable pageable);
    @Query(value = "select distinct e from Employee e left join fetch e.tasks left join fetch e.checkList left join fetch e.department")
    List<Employee> findAll();
    @Query(value = "select new com.hodehin.employeeapp.dto.EmployeeCheckListDto(e.id,e.firstName,e.lastName,c.timeIn,c.timeOut) from Employee e left join e.checkList c")
    Page<EmployeeCheckListDto> findAllCheckListInfo(Pageable pageable);
}
