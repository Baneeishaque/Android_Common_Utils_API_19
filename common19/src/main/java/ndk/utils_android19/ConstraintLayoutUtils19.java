package ndk.utils_android19;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.Objects;

public class ConstraintLayoutUtils19 {

    public static ConstraintSet getCustomDpMarginLeftRightLayoutConnectedConstraintSet(ConstraintLayout constraintLayout, View component, int customDpOfMargin) {

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(Objects.requireNonNull(constraintLayout));

        constraintSet.connect(component.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, customDpOfMargin);
        constraintSet.connect(component.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, customDpOfMargin);

        return constraintSet;
    }

    public static ConstraintSet get16dpMarginLeftRightLayoutConnectedConstraintSet(ConstraintLayout constraintLayout, View component) {

        return getCustomDpMarginLeftRightLayoutConnectedConstraintSet(constraintLayout, component, 16);
    }

    public static ConstraintSet getCustomDpMarginLeftRightLayoutBottomComponentConnectedConstraintSet(ConstraintLayout constraintLayout, View component, View topComponent, int customDpOfMargin) {

        ConstraintSet constraintSet = ConstraintLayoutUtils19.getCustomDpMarginLeftRightLayoutConnectedConstraintSet(constraintLayout, component,customDpOfMargin);
        constraintSet.connect(component.getId(), ConstraintSet.TOP, topComponent.getId(), ConstraintSet.BOTTOM, customDpOfMargin);
        return constraintSet;
    }

}
