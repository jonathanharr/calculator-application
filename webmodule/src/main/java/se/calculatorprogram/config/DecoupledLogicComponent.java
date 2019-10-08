package se.calculatorprogram.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.PostConstruct;

/**
 * Decouples Logic only.
 */
@Slf4j
@AllArgsConstructor
@Component
public class DecoupledLogicComponent {

    private final SpringResourceTemplateResolver template;

    @PostConstruct
    public void init() {
        template.setUseDecoupledLogic(true);
    }

}
