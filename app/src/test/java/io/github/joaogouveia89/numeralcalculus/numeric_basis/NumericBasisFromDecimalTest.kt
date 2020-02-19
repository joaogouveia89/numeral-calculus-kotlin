package io.github.joaogouveia89.numeralcalculus.numeric_basis

import android.util.Log
import android.os.Process
import io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis.NumericBasisFromDecimal
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Log::class, Process::class)
class NumericBasisFromDecimalTest {
    @Before
    fun beforeTest() {
        PowerMockito.mockStatic(Log::class.java)
        Mockito.`when`(Log.i(ArgumentMatchers.any(), ArgumentMatchers.any())).then {
            println(it.arguments[1] as String)
            1
        }
        PowerMockito.mockStatic(Process::class.java)
        PowerMockito.doNothing().`when`(Process::class.java, "setThreadPriority", ArgumentMatchers.anyInt())
    }

    @Test
    fun decToBinTest(){
        val bin = NumericBasisFromDecimal("14", 2)
        val result = bin.call()
        assertEquals("1110", result)
    }

    @Test
    fun decToOctTest(){
        val oct = NumericBasisFromDecimal("2773", 8)
        val result = oct.call()
        assertEquals("5325", result)
    }

    @Test
    fun decToHexTest(){
        val hex = NumericBasisFromDecimal("2883", 16)
        val result = hex.call()
        assertEquals("B43", result)
    }
}