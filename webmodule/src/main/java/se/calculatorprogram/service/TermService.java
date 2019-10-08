package se.calculatorprogram.service;


import java.util.List;

/**
 * TermService Interface
 * @param <T> being the entity.
 */
public interface TermService<T> {

    public List<T> findAll();

    public T find(T t);

    public T create(T t);

}
