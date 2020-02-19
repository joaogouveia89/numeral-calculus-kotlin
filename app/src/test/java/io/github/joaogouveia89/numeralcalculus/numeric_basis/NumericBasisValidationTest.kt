package io.github.joaogouveia89.numeralcalculus.numeric_basis

import io.github.joaogouveia89.numeralcalculus.ui.base_conversion.BaseConvertionValidation
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(BaseConvertionValidation::class)
class NumericBasisValidationTest {

    @Test
    fun validationBinValid(){
        assertTrue(BaseConvertionValidation.validate("11101", 2))
    }
    @Test
    fun validationBinInvalid(){
        assertFalse(BaseConvertionValidation.validate("111501", 2))
    }

    @Test
    fun validationOctValid(){
        assertTrue(BaseConvertionValidation.validate("116", 8))
    }

    @Test
    fun validationOctInvalid(){
        assertFalse(BaseConvertionValidation.validate("11996", 8))
    }

    @Test
    fun validationDecValid(){
        assertTrue(BaseConvertionValidation.validate("829", 10))
    }

    @Test
    fun validationDecInvalid(){
        assertFalse(BaseConvertionValidation.validate("829EA", 10))
    }

    @Test
    fun validationHexValid(){
        assertTrue(BaseConvertionValidation.validate("829EA", 16))
    }

    @Test
    fun validationHexInvalid(){
        assertFalse(BaseConvertionValidation.validate("829EA-,", 16))
    }
}