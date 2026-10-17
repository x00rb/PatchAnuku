package app.template.patches.idm

import app.morphe.patcher.Fingerprint

object IdmDisableAdsPatches {
    val APP_LEVEL_AD_INIT = Fingerprint(
        definingClass = "Lidm/internet/download/manager/e;",
        name = "<init>",
        parameters = listOf(),
    )

    val BROWSER_AD_INIT = Fingerprint(
        definingClass = "Li/rm;",
        name = "mo13964",
        parameters = listOf("Lacr/browser/lightning/activity/MyAppCompatActivity;", "Li/m15;"),
    )
}
