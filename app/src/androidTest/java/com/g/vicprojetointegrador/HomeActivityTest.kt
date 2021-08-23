package com.g.vicprojetointegrador

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.g.vicprojetointegrador.presentation.HomeActivity
import com.g.vicprojetointegrador.presentation.SearchMoviesActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val homeActivity =  IntentsTestRule(HomeActivity::class.java)

    @Test
    fun when_click_on_SearchView_go_to_SearchMoviesActivity(){
        onView(withId(R.id.svSearch)).perform(click())
        //ASSERT
        intended(hasComponent(SearchMoviesActivity::class.java.name))
    }

}


