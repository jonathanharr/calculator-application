package se.calculatorprogram.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import se.calculatorprogram.model.Equation;
import se.calculatorprogram.model.Term;
import se.calculatorprogram.util.InputDTO;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * EquationLoader Implementation, class helps load Equation data to {@link se.calculatorprogram.controller.TermController}
 */
@Slf4j
@Service
public class EquationLoaderImpl implements EquationLoader {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AtomicLong atomicLong;
    private Equation equation;
    private Equation.EquationBuilder equationBuilder;
    private boolean recievedAnything = false;
    private boolean hitReset = false;
    @Getter
    private final List<String> historyOfCalculations = new ArrayList<>();
    private String equationStringHolder;

    /**
     * @return a Calculator Message, either the answer, equation, or placeholder 0's.
     * @throws UnsupportedEncodingException in case of faulty Input.
     */
    public String getCalculatorMessage() throws UnsupportedEncodingException {
        if (!hitReset) {
            if (atomicLong.get() >= 1) {
                return (recievedAnything) ? equationBuilder.getEquationInputMessage() : Objects.requireNonNull(restTemplate.getForObject(getDestinationUrl(getRecentResultUrl()), Term.class)).getTermString();
            } else {
                return (recievedAnything) ? equationBuilder.getEquationInputMessage() : "0";
            }
        } else {
            hitReset = false;
            return "00";
        }
    }

    /**
     * Method "useCalculator" refers to that whenever the Calculator is used, new values have to be presented,
     * and this method does exactly that.
     *
     * @param inputDTO being input from User.
     * @throws UnsupportedEncodingException in case of faulty Input.
     */
    public void useCalculator(InputDTO inputDTO) throws UnsupportedEncodingException {
        if (inputDTO.getInputOperator().equals("hitReset")) {
            log.info("We hit reset..");
            resetCalculation();
            hitReset = true;
            return;
        }
        if (recievedAnything) {
            equationBuilder.addSecondTerm(inputDTO);
            performAndReturnCalculationResult();
            recievedAnything = false;
        } else {
            recievedAnything = true;
            equationBuilder = new Equation.EquationBuilder(inputDTO);
            equationStringHolder = equationBuilder.getEquationInputMessage();
            if (equationBuilder.flyingSoloOperation()) {
                performAndReturnCalculationResult();
                recievedAnything = false;
            }
        }
    }

    // -- Private Methods --
    // -- Calculator Related --

    /**
     * Performs the Calculation in question and places Equation in history list, as well as an answer in the Answer Term.
     * Increments our AtomicLong so we can navigate to the most recent calculation in our Rest Repository.
     *
     * @throws UnsupportedEncodingException in case of faulty Input.
     */
    private void performAndReturnCalculationResult() throws UnsupportedEncodingException {
        equation = equationBuilder.buildEquation();
        log.info("added equation = {}", equation.toString());
        atomicLong.incrementAndGet();
        Term retrievedTerm = Objects.requireNonNull(restTemplate.getForObject(getDestinationUrl(destinationString()), Term.class));
        historyOfCalculations.add(equationStringHolder += buildEquationString(equation, retrievedTerm));
        log.info(historyOfCalculations.get((int) atomicLong.get() - 1));
    }

    // -- Misc --

    /**
     * Builds an Equation String out of the Input.
     *
     * @param equation to derrive String from.
     * @param resultTerm being return Term from API.
     * @return a formatted String of either "x operator y = termResult" or "x operator = termResult"
     */
    private String buildEquationString(Equation equation, Term resultTerm) {
        return (equation.isSoloOperation()) ? "= " + resultTerm.getTermString() :
                equation.getTermArray().get(1).getTermString() + " = " + resultTerm.getTermString();
    }

    /**
     * Resets the Calculation, which as of now only consists of a Boolean, but in case of further work.
     */
    private void resetCalculation() {
        recievedAnything = false;
    }
    // -- URL Methods --

    /**
     * @return the URL + the current index of the most recent calculation.
     */
    private String getRecentResultUrl() {
        return "http://localhost:8080/calc/?term=" + atomicLong.get();
    }

    /**
     * @return a destinationString, not decoded
     */
    private String destinationString() {
        String baseURL = "http://localhost:8080/calc/" + equation.getOperationType() + "?term=" + equation.getTermArray().get(0).getTermString();
        String destination = (equation.isSoloOperation()) ? "" : "&secondTerm=" + equation.getTermArray().get(1).getTermString();
        return baseURL + destination;
    }

    /**
     * @return a decoded URL String.
     */
    private String getDestinationUrl(String url) throws UnsupportedEncodingException {
        URI termURI = new UriTemplate(url).expand(url);
        url = URLDecoder.decode(termURI.toString(), "UTF-8");
        log.info("URL Decoded = {}", url);
        return url;
    }
}
