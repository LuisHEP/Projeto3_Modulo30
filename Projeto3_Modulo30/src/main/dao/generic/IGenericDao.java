package main.dao.generic;

import java.util.List;

public interface IGenericDao<T> {

    void save(T entity);

    List<T> findAll();

    // Outros métodos podem ser adicionados conforme necessário
}
