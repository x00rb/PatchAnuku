package app.template.patches.idm

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch

object IdmHideEmptyBannerPatches {
    val SHOW_BANNER_AD = Fingerprint(
        definingClass = "Lacr/browser/lightning/activity/MyAppCompatActivity;",
        name = "showBannerAd",
        parameters = listOf(),
    )
}

@Suppress("unused")
val idmHideEmptyBannerBytecodePatch = bytecodePatch(
    name = "Hide Empty Banner (Bytecode)",
    description = "Hides the empty bottom banner container in IDM at runtime.",
    default = true
) {
    compatibleWith(IdmConstants.COMPATIBILITY_IDM)
    execute {
        IdmHideEmptyBannerPatches.SHOW_BANNER_AD.method.apply {
            addInstructions(
                0,
                """
                const v0, 0x7f0a018f
                invoke-virtual {p0, v0}, Landroid/app/Activity;->findViewById(I)Landroid/view/View;
                move-result-object v0
                if-nez v0, :got_view
                return-void
                :got_view
                invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;
                move-result-object v0
                if-nez v0, :got_parent
                return-void
                :got_parent
                check-cast v0, Landroid/view/View;
                const/4 v1, 0x8
                invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V
                return-void
                """.trimIndent(),
            )
        }
    }
}
