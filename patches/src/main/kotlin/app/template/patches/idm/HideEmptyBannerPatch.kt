package app.template.patches.idm

import app.morphe.patcher.patch.resourcePatch
import org.w3c.dom.Element

val idmHideEmptyBannerResourcePatch = resourcePatch {
    execute {
        listOf(
            "res/layout/activity_main.xml",
            "res/layout/activity_main_bottom.xml",
            "res/layout/activity_torrent_details.xml",
            "res/layout/app_bar_main.xml",
        ).forEach { path ->
            document(path).use { doc ->
                val containers = doc.getElementsByTagName("LinearLayout")
                for (i in 0 until containers.length) {
                    val ll = containers.item(i) as Element
                    if (ll.getAttribute("android:minHeight") != "60dp") continue
                    var hasSlot = false
                    val descendants = ll.getElementsByTagName("*")
                    for (j in 0 until descendants.length) {
                        val child = descendants.item(j) as? Element
                        if (child?.getAttribute("android:id") == "@+id/appodealBannerView") {
                            hasSlot = true
                            break
                        }
                    }
                    if (hasSlot) {
                        ll.setAttribute("android:minHeight", "0dp")
                    }
                }
            }
        }
    }
}
