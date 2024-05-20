package br.com.fiapmspedidos.domain.shared;

public interface IExecuteArgs<T, J> {
    T execute(J domainObject);
}
