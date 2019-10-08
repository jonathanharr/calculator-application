package se.calculatorprogram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.calculatorprogram.service.EquationLoader;
import se.calculatorprogram.util.InputDTO;
import se.calculatorprogram.util.references.PageViews;
import se.calculatorprogram.util.references.ReferenceUtil;

import java.io.UnsupportedEncodingException;

/**
 * TermController, regulates our main Applications WEB UI presentation.
 */
@Slf4j
@RequestMapping("/")
@Controller
public class TermController {

    @Autowired
    private EquationLoader equationLoader;

    /**
     * RequestMapping, loads the StartPage with correct values.
     *
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model) throws UnsupportedEncodingException {
        model.addAttribute(ReferenceUtil.CURRENT_TERM, equationLoader.getCalculatorMessage());
        model.addAttribute(ReferenceUtil.INPUT_DTO, new InputDTO());
        model.addAttribute(ReferenceUtil.PASSED_EQUATIONS, equationLoader.getHistoryOfCalculations());
        log.info("RequestMapping, model = {}", model);
        return PageViews.HOME;
    }

    /**
     * PostMapping, handles all inputs.
     *
     * @param inputDTO
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping(PageViews.INDEX)
    public String useCalculator(@ModelAttribute InputDTO inputDTO, Model model) throws UnsupportedEncodingException {
        log.info("PostMapping, term  = {}", inputDTO.getInputFromCalculator());
        equationLoader.useCalculator(inputDTO);
        log.info("PostMapping, model = {}", model);
        return PageViews.REDIRECT;
    }
}
