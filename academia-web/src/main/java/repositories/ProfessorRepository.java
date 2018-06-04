package repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.examplebr.edu.ifal.academia.academiatiweb.modelo.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

	
}
