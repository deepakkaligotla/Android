package `in`.kaligotla.deepak.core

import android.util.Log
import `in`.kaligotla.deepak.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())
    }
}