package com.example.kirke.helloworld.test.steps;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.example.kirke.helloworld.MainActivity;
import com.example.kirke.helloworld.R;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.*;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
@CucumberOptions(
        features = "features",
        glue = "com.example.kirke.helloworld.test.steps")
public class GreetingStepdefs {
    @Rule
    public final ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Given("a user with name {string}")
    public void aUserHasAName(String name) {
        onView(ViewMatchers.withId(R.id.editName)).perform(replaceText(name));
    }

    @When("the user clicks the greet button")
    public void theUserClicksGreet() {
        onView(withId(R.id.greetButton)).perform(click());
    }

    @Then("the app should greet the user by name")
    public void itShouldGreetTheUserByName(List<String> greetings) throws InterruptedException {
        sleep(200);
        onView(withId(R.id.greeting)).check(matches(withText(isIn(greetings))));
    }

    @Given("a user who has not provided a name")
    public void aUserHasNoName() {
        onView(withId(R.id.editName)).perform(clearText());
    }

    @Then("the app should greet the entire world")
    public void itShouldGreetTheEntireWorld(List<String> greetings) throws InterruptedException {
        sleep(200);
        onView(withId(R.id.greeting)).check(matches(withText(isIn(greetings))));
    }
}
