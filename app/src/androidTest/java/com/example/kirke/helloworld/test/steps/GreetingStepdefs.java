package com.example.kirke.helloworld.test.steps;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import com.example.kirke.helloworld.MainActivity;
import com.example.kirke.helloworld.R;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Rule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GreetingStepdefs {
    @Rule
    public final ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, true);

    private Activity activity;

    @Before
    public void setup() {
        activity = activityRule.launchActivity(null);
    }

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

        final TextView message = activity.findViewById(R.id.message);
        assertThat(message.getText().toString(), isIn(greetings));
    }

    @Given("a user who has not provided a name")
    public void aUserHasNoName() {
        onView(withId(R.id.editName)).perform(clearText());
    }

    @Then("the app should greet the entire world")
    public void itShouldGreetTheEntireWorld(List<String> greetings) throws InterruptedException {
        sleep(200);

        final TextView message = activity.findViewById(R.id.message);
        assertThat(message.getText().toString(), isIn(greetings));
    }

    @After
    public void tearDown() {
        activity.finish();
    }
}
