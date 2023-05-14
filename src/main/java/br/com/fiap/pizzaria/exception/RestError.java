package br.com.fiap.pizzaria.exception;

public record RestError (
        int cod,
        String message
) {}