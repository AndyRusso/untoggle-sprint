package io.github.andyrusso.untogglesprint.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameOptions.class)
public class MixinGameOptions {
    @Inject(method = "method_42491", at = @At("HEAD"), cancellable = true)
    private static void untoggleText(Text optionText, Boolean value, CallbackInfoReturnable<Text> cir) {
        // Change the "Sprint: Hold" to "Sprint: Untoggle" or another translation.
        cir.setReturnValue(Text.translatable(value ? "options.key.toggle" : "untoggle-sprint.untoggle"));
    }
}