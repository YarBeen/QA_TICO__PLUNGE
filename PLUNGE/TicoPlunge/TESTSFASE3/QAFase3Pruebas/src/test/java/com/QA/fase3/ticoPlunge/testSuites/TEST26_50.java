package com.QA.fase3.ticoPlunge.testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.QA.fase3.ticoPlunge.tests.HirePageTest;
import com.QA.fase3.ticoPlunge.tests.HistorialDeComprasTest;
import com.QA.fase3.ticoPlunge.tests.ListaDeUsuariosTest;
import com.QA.fase3.ticoPlunge.tests.MainPageTests;
import com.QA.fase3.ticoPlunge.tests.MyProfileTests;
import com.QA.fase3.ticoPlunge.tests.PlanesTest;
import com.QA.fase3.ticoPlunge.tests.StaffTests;

@RunWith(Suite.class)
@SuiteClasses({StaffTests.class, MainPageTests.class, MyProfileTests.class, HirePageTest.class })
public class TEST26_50 {

}
