package it.finanze.siga.ejb;

import jakarta.ejb.Local;

@Local
public interface SampleBeanLocal {
    String greet();
}
