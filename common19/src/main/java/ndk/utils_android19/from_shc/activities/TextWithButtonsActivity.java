package ndk.utils_android19.from_shc.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.util.Pair;

import java.util.Objects;

import ndk.utils_android1.ActivityUtils;
import ndk.utils_android1.ContextActivity;
import ndk.utils_android1.DisplayHelper;
import ndk.utils_android19.R;

public abstract class TextWithButtonsActivity extends ContextActivity {

    public ScrollView scrollView;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_with_buttons);

        scrollView = findViewById(R.id.scrollView);
        progressBar = findViewById(R.id.progressBar);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        TextView textViewFirst = findViewById(R.id.textViewFirst);
        textViewFirst.setText(configureTextViewFirstText());

        addButtonsWithClickEventsUnderConstraintLayoutComponent(constraintLayout, configureButtonsWithClickEvents(), textViewFirst);
    }

    protected abstract String configureTextViewFirstText();

    protected abstract Pair[] configureButtonsWithClickEvents();

    private void addButtonsWithClickEventsUnderConstraintLayoutComponent(ConstraintLayout constraintLayout, Pair[] buttons, View component) {

        for (final Pair button_item : buttons) {

            Button button = createButton(Objects.requireNonNull(button_item.first).toString(), (View.OnClickListener) button_item.second);
            addComponentToBottomOfConstraintLayoutComponent(constraintLayout, button, component);
            component = button;
        }
    }

    private void addButtonsWithClickEventsToConstraintLayout(ConstraintLayout constraintLayout, Pair[] buttons) {

        boolean isFirstComponent = true;
        Button previousButton = null;

        for (final Pair button_item : buttons) {

            Button button = createButton(Objects.requireNonNull(button_item.first).toString(), v -> ActivityUtils.startActivity(currentActivityContext, (Class) button_item.second));

            isFirstComponent = addComponentToTopOfConstraintLayoutOrBottomOfConstraintLayoutComponent(constraintLayout, button, isFirstComponent, previousButton);

            previousButton = button;
        }
    }

    private boolean addComponentToTopOfConstraintLayoutOrBottomOfConstraintLayoutComponent(ConstraintLayout constraintLayout, View component, boolean isFirstComponent, Button previousComponent) {

        //Original Code
//        addComponentToConstraintLayout(constraintLayout, component, getLeftRightConstraintLayoutTopOrComponentBottomConnectedConstraintSet(constraintLayout, component, isFirstComponent, previousComponent));

        //Second Code
        constraintLayout.addView(component);
        //TODO : Bug
        ConstraintSet constraintSet = getLeftRightConstraintLayoutTopOrComponentBottomConnectedConstraintSet(constraintLayout, component, isFirstComponent, previousComponent);
        constraintSet.applyTo(constraintLayout);

        if (isFirstComponent) {
            isFirstComponent = false;
        }

        return isFirstComponent;
    }

    private ConstraintSet getLeftRightConstraintLayoutTopOrComponentBottomConnectedConstraintSet(ConstraintLayout constraintLayout, View component, boolean isFirstComponent, View previousComponent) {

        ConstraintSet constraintSet = getLeftRightConnectedConstraintSet(constraintLayout, component);
        if (isFirstComponent) {
            constraintSet.connect(component.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 16);
        } else {
            constraintSet.connect(component.getId(), ConstraintSet.TOP, previousComponent.getId(), ConstraintSet.BOTTOM, 16);
        }
        return constraintSet;
    }

    private void addComponentToBottomOfConstraintLayoutComponent(ConstraintLayout constraintLayout, View component, View topComponent) {

        //Original Code
//        addComponentToConstraintLayout(constraintLayout, component, getLeftRightComponentBottomConnectedConstraintSet(constraintLayout, component, topComponent));

        //Third Code
        constraintLayout.addView(component);
        //TODO : Bug
        getLeftRightComponentBottomConnectedConstraintSet(constraintLayout, component, topComponent).applyTo(constraintLayout);

    }

    private void addComponentToConstraintLayout(ConstraintLayout constraintLayout, View component, ConstraintSet constraintSet) {

        // Add Component to Layout
        constraintLayout.addView(component);
        constraintSet.applyTo(constraintLayout);
    }

    private ConstraintSet getLeftRightComponentBottomConnectedConstraintSet(ConstraintLayout constraintLayout, View component, View topComponent) {

        ConstraintSet constraintSet = getLeftRightConnectedConstraintSet(constraintLayout, component);
        constraintSet.connect(component.getId(), ConstraintSet.TOP, topComponent.getId(), ConstraintSet.BOTTOM, 16);
        return constraintSet;
    }

    private ConstraintSet getLeftRightConnectedConstraintSet(ConstraintLayout constraintLayout, View component) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(Objects.requireNonNull(constraintLayout));

        constraintSet.connect(component.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 16);
        constraintSet.connect(component.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 16);

        return constraintSet;
    }

    private Button createButton(String text, View.OnClickListener clickEvent) {

        //Create Button Dynamically
        Button button = new Button(this);
        button.setText(text);
        button.setId(View.generateViewId());
        //TODO : Sentence case for text

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, DisplayHelper.dpToPixel(48, getApplicationContext()));
        button.setLayoutParams(layoutParams);

        button.setOnClickListener(clickEvent);
        return button;
    }
}
