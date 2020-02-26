package io.github.joaogouveia89.numeralcalculus.numeric_basis

import io.github.joaogouveia89.numeralcalculus.ui.base_conversion.BaseConvertionValidation
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(BaseConvertionValidation::class)
class NumericBasisValidationTest {

    private lateinit var baseConvertionValidation: BaseConvertionValidation

    @Before
    fun setup(){
        baseConvertionValidation = BaseConvertionValidation()
    }

    @Test
    fun validationBinValid(){
        assertTrue(baseConvertionValidation.validate("11101", 2))
    }
    @Test
    fun validationBinInvalid(){
        assertFalse(baseConvertionValidation.validate("111501", 2))
    }

    @Test
    fun validationOctValid(){
        assertTrue(baseConvertionValidation.validate("116", 8))
    }

    @Test
    fun validationOctInvalid(){
        assertFalse(baseConvertionValidation.validate("11996", 8))
    }

    @Test
    fun validationDecValid(){
        assertTrue(baseConvertionValidation.validate("829", 10))
    }

    @Test
    fun validationDecInvalid(){
        assertFalse(baseConvertionValidation.validate("829EA", 10))
    }

    @Test
    fun validationHexValid(){
        assertTrue(baseConvertionValidation.validate("829EA", 16))
    }

    @Test
    fun validationHexInvalid(){
        assertFalse(baseConvertionValidation.validate("829EA-,", 16))
    }
}