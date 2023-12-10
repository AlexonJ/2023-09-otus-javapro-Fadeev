package ru.otus.javapro.homeworks.hw14dependencyinjection.services;

import ru.otus.javapro.homeworks.hw14dependencyinjection.model.Equation;

import java.util.List;

public interface EquationPreparer {
    List<Equation> prepareEquationsFor(int base);
}
