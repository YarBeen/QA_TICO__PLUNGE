package com.QA.fase3.ticoPlunge.testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.QA.fase3.ticoPlunge.tests.HistorialDeComprasTest;
import com.QA.fase3.ticoPlunge.tests.ListaDeUsuariosTest;
import com.QA.fase3.ticoPlunge.tests.PlanesTest;

@RunWith(Suite.class)
@SuiteClasses({ListaDeUsuariosTest.class, PlanesTest.class, HistorialDeComprasTest.class })
public class TEST51_75 {

}
