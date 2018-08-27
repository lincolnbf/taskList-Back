package com.lincolnbf.supero.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lincolnbf.supero.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	
}