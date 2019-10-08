package se.calculatorprogram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.calculatorprogram.Calculator;
import se.calculatorprogram.model.Term;
import se.calculatorprogram.service.TermService;

import java.util.concurrent.atomic.AtomicLong;

/**
 * RestCalculationController, the main API for storing and retrieving the Terms.
 */
@Slf4j
@RestController
@RequestMapping("calc/")
public class RestCalculationController {

    @Autowired
    private TermService<Term> termService;

    /**
     * Finds a Given Term within the API.
     * @param term
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Term find(Term term) {
        log.info("Finding Term = {}", term.toString());
        return termService.find(term);
    }

    /**
     * Performs a Sub Equation.
     * @param term
     * @param secondTerm
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "=", method = RequestMethod.GET)
    public Term equalsItself(@RequestParam(value="term", defaultValue="0") String term,
                     @RequestParam(value="secondTerm", defaultValue ="0") String secondTerm) {
        log.info("PostMapping, term  = {}, and term = {}", term, secondTerm);
        return termService.create(new Term(term));
    }

    /**
     * Performs a Ceiling calculation.
     * @param term
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "cei", method = RequestMethod.GET)
    public Term cei(@RequestParam(value="term", defaultValue="ERROR") String term) {
        return termService.create(new Term(String.valueOf(Math.ceil(Double.parseDouble(term)))));
    }

    /**
     * Performs a Floor calculation.
     * @param term
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "floor", method = RequestMethod.GET)
    public Term floor(@RequestParam(value="term", defaultValue="ERROR") String term) {
        return termService.create(new Term(String.valueOf(Math.floor(Double.parseDouble(term)))));
    }

    /**
     * Performs a round calculation.
     * @param term
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "round", method = RequestMethod.GET)
    public Term round(@RequestParam(value="term", defaultValue="ERROR") String term) {
        return termService.create(new Term(String.valueOf(Math.round(Double.parseDouble(term)))));
    }

    /**
     * Performs an Addition Equation.
     * @param term
     * @param secondTerm
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public Term add(@RequestParam(value="term", defaultValue="0") String term,
        @RequestParam(value="secondTerm", defaultValue ="0") String secondTerm) {
        return termService.create(new Term(String.valueOf(Double.parseDouble(term) + Double.parseDouble(secondTerm))));
    }

    /**
     * Performs a Sub Equation.
     * @param term
     * @param secondTerm
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "sub", method = RequestMethod.GET)
    public Term sub(@RequestParam(value="term", defaultValue="0") String term,
        @RequestParam(value="secondTerm", defaultValue ="0") String secondTerm) {
        log.info("PostMapping, term  = {}, and term = {}", term, secondTerm);
        return termService.create(new Term(String.valueOf(Double.parseDouble(term) - Double.parseDouble(secondTerm))));
    }

    /**
     * Performs a Sub Equation.
     * @param term
     * @param secondTerm
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "mult", method = RequestMethod.GET)
    public Term mult(@RequestParam(value="term", defaultValue="0") String term,
        @RequestParam(value="secondTerm", defaultValue ="0") String secondTerm) {
        log.info("PostMapping, term  = {}, and term = {}", term, secondTerm);
        return termService.create(new Term(String.valueOf(Double.parseDouble(term) * Double.parseDouble(secondTerm))));
    }

    /**
     * Performs a Division Equation
     * @param term
     * @param secondTerm
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "div", method = RequestMethod.GET)
    public Term div(@RequestParam(value="term", defaultValue="0") String term,
        @RequestParam(value="secondTerm", defaultValue ="0") String secondTerm) {
        log.info("PostMapping, term  = {}, and term = {}", term, secondTerm);
        return termService.create(new Term(String.valueOf(Double.parseDouble(term) / Double.parseDouble(secondTerm))));
    }

    /**
     * Performs a MOD/% Equation
     * @param term
     * @param secondTerm
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "mod", method = RequestMethod.GET)
    public Term mod(@RequestParam(value="term", defaultValue="0") String term,
                    @RequestParam(value="secondTerm", defaultValue ="0") String secondTerm) {
        log.info("PostMapping, term  = {}, and term = {}", term, secondTerm);
        return termService.create(new Term(String.valueOf(Double.parseDouble(term) % Double.parseDouble(secondTerm))));
    }

    /**
     * Performs a MAX Equation
     * @param term
     * @param secondTerm
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "max", method = RequestMethod.GET)
    public Term max(@RequestParam(value="term", defaultValue="0") String term,
                    @RequestParam(value="secondTerm", defaultValue ="0") String secondTerm) {
        log.info("PostMapping, term  = {}, and term = {}", term, secondTerm);
        return termService.create(new Term(String.valueOf(Math.max(Double.parseDouble(term), Double.parseDouble(secondTerm)))));
    }

    /**
     * Performs a MIN Equation
     * @param term
     * @param secondTerm
     * @return calculated Result in the form of a JSON.
     */
    @RequestMapping(value = "min", method = RequestMethod.GET)
    public Term min(@RequestParam(value="term", defaultValue="0") String term,
                    @RequestParam(value="secondTerm", defaultValue ="0") String secondTerm) {
        log.info("PostMapping, term  = {}, and term = {}", term, secondTerm);
        return termService.create(new Term(String.valueOf(Math.min(Double.parseDouble(term), Double.parseDouble(secondTerm)))));
    }

}
