package com.csc2002s.assgnmnts.apps.alphabetapplication.macrobenchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlphabetDetailBenchmarks {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun alphabetDetailCompilationNone() = benchmarkAlphabetDetail(CompilationMode.None())

    @Test
    fun alphabetDetailCompilationPartial() = benchmarkAlphabetDetail(CompilationMode.Partial())

    @Test
    fun alphabetDetailCompilationFull() = benchmarkAlphabetDetail(CompilationMode.Full())

    private fun benchmarkAlphabetDetail(compilationMode: CompilationMode) =
        benchmarkRule.measureRepeated(
            packageName = PACKAGE_NAME,
            metrics = listOf(FrameTimingMetric()),
            compilationMode = compilationMode,
            iterations = 10,
            startupMode = StartupMode.COLD,
            setupBlock = {
                startActivityAndWait()
                goToAlphabetListTab()
            }
        ) {
            goToPlantDetail()
        }
}

fun MacrobenchmarkScope.goToPlantDetail(index: Int? = null) {
    val alphabetListSelector = By.res(packageName, "alphabet_list")
    val recycler = device.findObject(alphabetListSelector)

    // select different item each iteration, but only from the visible ones
    val currentChildIndex = index ?: ((iteration ?: 0) % recycler.childCount)

    val child = recycler.children[currentChildIndex]
    child.click()
    // wait until plant list is gone
    device.wait(Until.gone(alphabetListSelector), 5_000)
}
