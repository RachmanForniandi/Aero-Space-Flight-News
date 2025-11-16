package rachman.forniandi.core.utilRemote

import android.content.Context
import android.content.Intent

object NavigationHelper {

    const val EXTRA_CONTENT_ID = "EXTRA_CONTENT_ID"
    const val EXTRA_CONTENT_TYPE = "EXTRA_CONTENT_TYPE"
    const val EXTRA_FROM_FAVORITE = "EXTRA_FROM_FAVORITE"

    fun navigateToMain(context: Context, id: Int, type: String) {
        val intent = Intent("rachman.forniandi.aerospaceflightnews.MAIN").apply {
            putExtra(EXTRA_CONTENT_ID, id)
            putExtra(EXTRA_CONTENT_TYPE, type)
            putExtra(EXTRA_FROM_FAVORITE, true)
        }
        context.startActivity(intent)
    }
}