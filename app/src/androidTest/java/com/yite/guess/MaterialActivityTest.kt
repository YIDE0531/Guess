package com.yite.guess

import android.app.Activity
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MaterialActivityTest {
    @Rule
    @JvmField
    val activityTestRule = activityScenarioRule<MaterialActivity>()

    @Test
    fun guessWrong(){
        val scenario = activityTestRule.scenario
        lateinit var resources: Resources
        var secret = 0
        scenario.onActivity { activity->
            resources = activity.resources
            secret = activity.secretNumber.secret
        }

        val n = 5
        for (n in 1..10) {
            if (n != secret) {
                onView(withId(R.id.edt_number)).perform(clearText())
                onView(withId(R.id.edt_number)).perform(typeText(n.toString()))
                onView(withId(R.id.btn_guess)).perform(click())
                val message =
                    if (n < secret) resources.getString(R.string.bigger)
                    else resources.getString(R.string.smaller)
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resources.getString(R.string.ok))).perform(click())
            }
        }
    }

    //  猜到正確的數字後重玩
    @Test
    fun guessReStart(){
        val scenario = activityTestRule.scenario
        lateinit var resources: Resources
        var secret = 0
        scenario.onActivity { activity->
            resources = activity.resources
            secret = activity.secretNumber.secret
        }
        val n = 5
        for (n in 1..10) {
            if (n != secret) {
                onView(withId(R.id.edt_number)).perform(clearText())
                onView(withId(R.id.edt_number)).perform(typeText(n.toString()))
                onView(withId(R.id.btn_guess)).perform(click())
                val message =
                    if (n < secret) resources.getString(R.string.bigger)
                    else resources.getString(R.string.smaller)
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resources.getString(R.string.ok))).perform(click())
            }else{
                scenario.onActivity { activity->
                    hideKeyboard(activity)  //  關閉鍵盤
                }
                onView(withId(R.id.fab)).perform(click())
                onView(withText(resources.getString(R.string.ok))).perform(click())
                break
            }
        }
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}