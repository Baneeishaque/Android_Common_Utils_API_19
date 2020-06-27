package ndk.utils;

import com.google.android.material.tabs.TabLayout;

public class Tab_Layout_Utils {

    public static void select_Tab(TabLayout tabLayout, int tab_index) {
        (tabLayout.getTabAt(tab_index)).select();
    }
}
