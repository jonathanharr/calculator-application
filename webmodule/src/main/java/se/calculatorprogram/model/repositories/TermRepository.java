package se.calculatorprogram.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.calculatorprogram.model.Term;

/**
 * Repository, for storing and retrieving Terms.
 */
public interface TermRepository extends JpaRepository<Term, Long> {
}
