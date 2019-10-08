package se.calculatorprogram.service;


import java.util.List;

/**
 * TermService Interface
 *
 * @param <T> being the entity.
 */
public interface TermService<T> {

    List<T> findAll();

    T find(T t);

    T create(T t);

}
