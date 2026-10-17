package app.template.patches.idm

import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.patch.bytecodePatch

@Suppress("unused")
val disableAdsPatch = bytecodePatch(
    name = "Disable Ads",
    description = "Disables Appodeal ads in IDM - Internet Download Manager.",
    default = true
) {
    compatibleWith(IdmConstants.COMPATIBILITY_IDM)

    execute {
        IdmDisableAdsPatches.APP_LEVEL_AD_INIT.method.addInstruction(0, "return-void")
        IdmDisableAdsPatches.BROWSER_AD_INIT.method.addInstruction(0, "return-void")
    }
}
