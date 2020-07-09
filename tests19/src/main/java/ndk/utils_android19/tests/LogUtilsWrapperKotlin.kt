package ndk.utils_android19.tests

import ndk.utils_android1.LogUtils

open class LogUtilsWrapperKotlin(tag: String) {

    init {

        Companion.tag = tag
    }

    companion object {

        private var tag: String = ""

        @JvmStatic
        fun debug(message: String) {

            LogUtils.debug(tag, message)
        }
    }
}
