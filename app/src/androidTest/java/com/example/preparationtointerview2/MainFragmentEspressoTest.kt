package com.example.preparationtointerview2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.preparationtointerview2.view.MainFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.preparationtointerview2.view.MainAdapter
import junit.framework.TestCase
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<MainFragment>

    @Before
    fun setup() {
        //Запускаем Fragment в корне Activity
        scenario = launchFragmentInContainer()
    }

    @Test
    fun testFragmentNotNull(){
        scenario.onFragment {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun testViewModelInFragment(){
        scenario.onFragment {
            TestCase.assertNotNull(it.viewModel)
        }
    }

    @Test
    fun testRecyclerView(){
        scenario.onFragment {
            if (it.lifecycle.currentState==Lifecycle.State.CREATED){
            onView(ViewMatchers.isRoot()).perform(delay())
            onView(withId(R.id.filmList))
                .perform(
                    RecyclerViewActions.scrollToPosition<MainAdapter.FilmListViewHolder>(10)
                )
            }
        }
    }

    @Test
    fun perfomClickOnItem(){
        scenario.onFragment {
            if (it.lifecycle.currentState==Lifecycle.State.CREATED){
                onView(ViewMatchers.isRoot()).perform(delay())
                onView(withId(R.id.filmList))
                    .perform(
                        RecyclerViewActions
                            .actionOnItemAtPosition<MainAdapter.FilmListViewHolder>(
                                10,
                                click()
                            )
                    )
            }
        }
    }




    private fun delay(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(1000)
            }
        }
    }
}