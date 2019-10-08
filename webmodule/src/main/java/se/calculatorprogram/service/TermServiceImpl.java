package se.calculatorprogram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.calculatorprogram.model.Term;
import se.calculatorprogram.model.repositories.TermRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TermService Implementation. Handles the storage and retrieval of Terms from the API. See {@link se.calculatorprogram.controller.RestCalculationController}
 */
@Slf4j
@Service
public class TermServiceImpl implements TermService<Term> {

    @Autowired
    private TermRepository termRepository;

    /**
     * Finds all Terms within the API. Mostly used for troubleshooting.
     *
     * @return
     */
    @Override
    public List<Term> findAll() {
        return termRepository.findAll().stream().map(e -> new Term(e.getId(), e.getTermString())).collect(Collectors.toList());
    }

    /**
     * Finds a given term.
     *
     * @param term
     * @return
     */
    @Override
    public Term find(Term term) {
        log.info("Finding Term = {}", term);
        return termRepository.findById(term.getId()).orElse(new Term("666"));
    }

    /**
     * Creates a given Term.
     *
     * @param term
     * @return
     */
    @Transactional
    @Override
    public Term create(Term term) {
        log.info("Creating Term = {}", term.getTermString());
        Term newTerm = new Term(term.getTermString());
        termRepository.save(newTerm);
        return new Term(newTerm.getId(), newTerm.getTermString());
    }
}
