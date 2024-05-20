package br.com.fiapmspedidos.domain.shared;

public interface IExecuteVoid<T> {
    void execute(T domainObject);
}
