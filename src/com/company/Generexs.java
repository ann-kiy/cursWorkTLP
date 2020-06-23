package com.company;

import com.mifmif.common.regex.Generex;
import dk.brics.automaton.Automaton;

import java.util.Random;

public class Generexs extends Generex {
    public Generexs(String regex) {
        super(regex);
    }

    public Generexs(Automaton automaton) {
        super(automaton);
    }

    public Generexs(String regex, Random random) {
        super(regex, random);
    }

    public Generexs(Automaton automaton, Random random) {
        super(automaton, random);
    }
}
