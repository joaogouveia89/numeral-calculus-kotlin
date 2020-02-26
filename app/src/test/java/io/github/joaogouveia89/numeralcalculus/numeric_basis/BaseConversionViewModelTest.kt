package io.github.joaogouveia89.numeralcalculus.numeric_basis

import android.util.Log
import io.github.joaogouveia89.numeralcalculus.ui.base_conversion.BaseConversionViewModel
import io.github.joaogouveia89.numeralcalculus.ui.base_conversion.BaseConvertionValidation
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Log::class, BaseConvertionValidation::class, BaseConversionViewModel::class)
class BaseConversionViewModelTest {
    private lateinit var viewModel : BaseConversionViewModel
    private lateinit var baseConvertionValidation: BaseConvertionValidation

    @Before
    fun setup(){
        mockStatic(Log::class.java)
        viewModel = BaseConversionViewModel()
        baseConvertionValidation = mock(BaseConvertionValidation::class.java)
    }

    @Test
    fun initUserInputTest(){
        viewModel.initUserInput("1110", 0)
        assertEquals("1110", viewModel.userInput)
    }
}