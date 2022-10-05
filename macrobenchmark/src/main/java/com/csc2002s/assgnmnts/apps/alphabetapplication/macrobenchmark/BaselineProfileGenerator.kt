package com.csc2002s.assgnmnts.apps.alphabetapplication.macrobenchmark

import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalBaselineProfilesApi::class)
@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun startAlphabetListAlphabetDetail() {
        rule.collectBaselineProfile(PACKAGE_NAME) {
            // start the app flow
            pressHome()
            startActivityAndWait()

            val alphabetListTab = device.findObject(By.descContains("Alphabet list"))
            alphabetListTab.click()
            device.waitForIdle()
            // sleep for animations to settle
            Thread.sleep(500)

            val alphabetList = device.findObject(By.res(packageName, "alphabet_list"))
            val listItem = alphabetList.children[0]
            listItem.click()
            device.wait(Until.gone(By.res(packageName, "alphabet_list")), 5_000)
        }
    }
}
