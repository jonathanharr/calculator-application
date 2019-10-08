package se.calculatorprogram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity Class for Terms.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String termString;

    /**
     * Public Constructor, takes String value of Term.
     *
     * @param termString being the String Value of the Number.
     */
    public Term(String termString) {
        this.termString = termString;
    }


}
