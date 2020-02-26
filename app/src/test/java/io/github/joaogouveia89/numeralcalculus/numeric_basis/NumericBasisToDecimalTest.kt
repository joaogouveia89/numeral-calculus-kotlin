package io.github.joaogouveia89.numeralcalculus.numeric_basis

import android.util.Log
import io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis.NumericBasisToDecimal
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Log::class)
class NumericBasisToDecimalTest{

    @Before
    fun beforeTest() {
        PowerMockito.mockStatic(Log::class.java)
    }

    @Test
    fun binToDecTest(){
        val listener = mock(NumericBasisToDecimal.Base10ConversionListener::class.java)
        val bin = NumericBasisToDecimal("100", 2, listener)
        bin.run()

        verify(listener, times(1)).isDone("4")
    }

    @Test
    fun octToDecTest(){
        val listener = mock(NumericBasisToDecimal.Base10ConversionListener::class.java)
        val oct = NumericBasisToDecimal("11345", 8, listener)
        oct.run()

        verify(listener, times(1)).isDone("4837")
    }

    @Test
    fun hexToDecTest(){
        val listener = mock(NumericBasisToDecimal.Base10ConversionListener::class.java)
        val hex = NumericBasisToDecimal("FF", 16, listener)
        hex.run()

        verify(listener, times(1)).isDone("255")
    }

    @Test
    fun outBoundTest(){
        val listener = mock(NumericBasisToDecimal.Base10ConversionListener::class.java)
        val ob = NumericBasisToDecimal("FFFAABBA", 16, listener)
        ob.run()

        verify(listener, times(1)).outBound()
    }
}