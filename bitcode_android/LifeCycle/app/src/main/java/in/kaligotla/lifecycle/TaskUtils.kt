package `in`.kaligotla.lifecycle

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.Q)
fun getTaskInfo(context: Context): String {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val stringBuilder = StringBuilder()
    val taskList = activityManager.getRunningTasks(10)

    stringBuilder.append("Total Running Tasks: ${taskList.size}\n\n")
    for (info in taskList) {
        val topClassName = info.topActivity?.className?.substringAfterLast('.') ?: "Unknown"
        val baseClassName = info.baseActivity?.className?.substringAfterLast('.') ?: "Unknown"
        stringBuilder.append("Task ID: ${info.taskId}\n")
        stringBuilder.append("Num Activities: ${info.numActivities}\n")
        stringBuilder.append("Top: $topClassName\n")
        stringBuilder.append("Base: $baseClassName\n")
        stringBuilder.append("-----------------------------------------------\n")
    }
    return stringBuilder.toString()
}