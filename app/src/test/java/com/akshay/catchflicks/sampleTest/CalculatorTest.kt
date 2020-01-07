package com.akshay.catchflicks.sampleTest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by akshaynandwana on
 * 07, January, 2020
 **/

/**
 * Using Mockito's runner to run our test
 * @RunWith -> taking the single class in constructor to run
 */
@RunWith(MockitoJUnitRunner::class)
class CalculatorTest {

    /**
     * Mocking the operation class
     * Mocking is usefule when we have to test code which has some outer dependency.
     * We use mockito to fake that dependency.
     */
    @Mock
    private lateinit var operation: Operation

    private lateinit var calculator: Calculator

    /**
     * @Before things to setup before the test begins
     */
    @Before
    fun setup() {
        calculator = Calculator(operation)
    }

    /**
     * @Test defines this method is the test
     */
    @Test
    fun givenValidInput_whenAdd_shouldCallAddOperation() {
        val a = 5
        val b = 10
        calculator.add(a, b)
        verify(operation).add(
            a,
            b
        )              // verifying that the operation object is calling add method
        verify(operation, never()).sub(
            a,
            b
        )     // verifying that the operation object never call sub method

    }
}