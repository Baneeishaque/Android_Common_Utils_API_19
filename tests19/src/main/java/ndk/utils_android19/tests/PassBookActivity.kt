package ndk.utils_android19.tests

import ndk.utils_android16.models.sortable_tableView.pass_book.PassBookEntryV2
import ndk.utils_android19.PassBookActivityBase

class PassBookActivity : PassBookActivityBase() {

    private val writeExternalStoragePermissionRequest: Int = 0

    override fun configureApplicationName(): String {

        return ApplicationSpecification.applicationName
    }

    override fun configurePassBookUrl(): String {

        return "http://account-ledger-server.herokuapp.com/http_API/select_User_Accounts.php?user_id=13&parent_account_id=3331"
    }

    override fun isSortingAvailable(): Boolean {

        return false
    }

    override fun configureCurrentAccountShortName(): String {

        return ""
    }

    override fun configureCurrentAccountLongName(): String {

        return ""
    }

    override fun configure_ROW_LONG_CLICK_ACTIONS(clickedData: PassBookEntryV2?) {

        API19UtilsTestsLogger.debug(clickedData.toString())
//        API19UtilsTestsLoggerKotlin.debug(clickedData.toString())
    }

    override fun configureUserId(): String {

        return ""
    }

    override fun isV2(): Boolean {

        return true
    }

    override fun configureCurrentAccountId(): String {

        return "3331"
    }

    override fun configureWriteExternalStoragePermissionRequestCode(): Int {

        return writeExternalStoragePermissionRequest
    }
}
