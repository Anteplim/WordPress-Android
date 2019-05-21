package org.wordpress.android.e2e.pages;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;

import org.wordpress.android.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.wordpress.android.support.WPSupportUtils.isElementDisplayed;
import static org.wordpress.android.support.WPSupportUtils.waitForElementToBeDisplayed;

public class EditorPage {
    private static ViewInteraction publishButton = onView(withId(R.id.menu_save_post));

    private static ViewInteraction editor = onView(withId(R.id.aztec));

    private static ViewInteraction titleField = onView(allOf(withId(R.id.title), withHint("Title")));

    private static ViewInteraction publishConfirmation = onView(allOf(
            withText("Post published"), isDescendantOfA(withId(R.id.snackbar))));

    public EditorPage() {
        editor.check(matches(isDisplayed()));
    }

    public EditorPage enterTitle(String postTitle) {
        titleField.perform(typeText(postTitle), ViewActions.closeSoftKeyboard());

        return this;
    }

    public EditorPage enterContent(String postContent) {
        editor.perform(typeText(postContent), ViewActions.closeSoftKeyboard());

        return this;
    }

    public boolean publishPost() {
        publishButton.perform(click());
        onView(withText("PUBLISH NOW"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        waitForElementToBeDisplayed(publishConfirmation);
        return isElementDisplayed(publishConfirmation);
    }
}
