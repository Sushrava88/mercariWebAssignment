package com.mercari.tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;


public class TestContext  {
BaseTest baseTest;
    public TestContext(){
        this.baseTest = new BaseTest();
//        BaseTest baseTest = new BaseTest();

    }
    @Before
    public void init(){
        baseTest.setUp();
    }

    @After
    public void teardown(){
        baseTest.tearDown();
    }

}

