package it.finanze.siga.ejb;

import jakarta.ejb.Stateless;

@Stateless
public class SampleBean implements SampleBeanLocal {

    @Override
    public String greet() {
        return "Hello from Siga3-batch EJB!";
    }
}
