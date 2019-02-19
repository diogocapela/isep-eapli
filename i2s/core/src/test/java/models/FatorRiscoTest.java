/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FatorRiscoTest {

    private FatorRisco fatorRisco;

    public FatorRiscoTest() {
    }

    @Before
    public void setUp() {
        fatorRisco = new FatorRisco("Densidade", "Densidade de...");
    }

    @After
    public void tearDown() {
    }

    /**
     * Titulo do fator de risco não pode ser nulo
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFatorRiscoTituloMustNotBeNull() {
        System.out.println("must have a title");
        FatorRisco instance = new FatorRisco(null, "Envolvente01");
    }

    /**
     * Test of getTitulo method, of class FatorRisco.
     */
    @Test
    public void testGetTitulo() {
        System.out.println("getTitulo");
        FatorRisco instance = fatorRisco;
        String expResult = "Densidade";
        String result = instance.toDTO().getTitulo();
        assertEquals(expResult, result);

    }

}
