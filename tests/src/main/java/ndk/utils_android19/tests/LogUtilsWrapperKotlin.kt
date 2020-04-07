package ndk.utils_android19.tests

import ndk.utils_android16.Log_Utils
import ndk.utils_android19.BuildConfig

open class LogUtilsWrapperKotlin(tag:String) {

    init {

        Companion.tag=tag;
    }

    companion object{

        private var tag:String = ""

        @JvmStatic
        fun debug(message: String) {

            Log_Utils.debug(tag, message, BuildConfig.DEBUG)
        }
    }
}
