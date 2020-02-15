package io.github.joaogouveia89.numeralcalculus.calculus.numeric_basis

import android.os.Handler
import android.os.Looper
import android.os.Message
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.math.pow

//https://developer.android.com/training/multiple-threads/create-threadpool
object NumericBasisToDecimalSingleton{

    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
    private val decodeWorkQueue: BlockingQueue<Runnable> = LinkedBlockingQueue<Runnable>()
    // Sets the amount of time an idle thread waits before terminating
    private const val KEEP_ALIVE_TIME = 1L
    // Sets the Time Unit to seconds
    private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS

    private val conversionThreadpool: ThreadPoolExecutor = ThreadPoolExecutor(
        NUMBER_OF_CORES,       // Initial pool size
        NUMBER_OF_CORES,       // Max pool size
        KEEP_ALIVE_TIME,
        KEEP_ALIVE_TIME_UNIT,
        decodeWorkQueue
    )

    private val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val task = msg.obj as NumericBasisToDecimal
            convert(task)
        }
    }

    private fun convert(task : NumericBasisToDecimal) {
        conversionThreadpool.execute(task)
    }
}