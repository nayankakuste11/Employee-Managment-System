package com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.Repositories;

import com.codingshuttle.spingbootwebtutorial.spingbootwebtutorial.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}
