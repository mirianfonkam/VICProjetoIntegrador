package com.g.vicprojetointegrador

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.g.vicprojetointegrador.presentation.SearchMoviesActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchMoviesActivityTest {

    @get:Rule
    val searchMoviesActivity =  ActivityScenarioRule(SearchMoviesActivity::class.java)

    @Test
    fun when_type_on_SearchView_display_SearchResults(){
        onView(withId(R.id.svSearchQuery)).perform(typeText("Avatar\n"))
    }

    //swipeRight()
//    inAdapterView(Matcher)
//    atPosition(Integer)
//    onChildView(Matcher)
}


