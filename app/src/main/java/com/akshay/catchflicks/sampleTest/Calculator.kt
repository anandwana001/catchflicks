package com.akshay.catchflicks.sampleTest

/**
 * Created by akshaynandwana on
 * 07, January, 2020
 **/
class Calculator(val operation: Operation) {

    fun add(a: Int, b: Int): Int {
        return operation.add(a, b)
    }

    fun sub(a: Int, b: Int): Int {
        return operation.sub(a, b)
    }
}