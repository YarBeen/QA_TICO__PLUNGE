package com.QA.fase3.ticoPlunge.testSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.QA.fase3.ticoPlunge.tests.Class_Test;
import com.QA.fase3.ticoPlunge.tests.FeedBackTest;
import com.QA.fase3.ticoPlunge.tests.LoginAsAdmin_Test;
import com.QA.fase3.ticoPlunge.tests.Register_Test;

@RunWith(Suite.class)
@SuiteClasses({Class_Test.class, Register_Test.class,FeedBackTest.class,LoginAsAdmin_Test.class  })
public class AllTests {

}
