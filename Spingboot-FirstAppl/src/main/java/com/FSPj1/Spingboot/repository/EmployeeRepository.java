package com.FSPj1.Spingboot.repository;
import com.FSPj1.Spingboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee,Long>
{
}
