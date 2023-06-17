package io.github.andyrusso.untogglesprint.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.StickyKeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StickyKeyBinding.class)
public class MixinStickyKeyBinding {
    // Inject in the "Hold" functionality, where `pressed` is being modified
    @ModifyArg(method = "setPressed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;setPressed(Z)V", ordinal = 1))
    private boolean reverseSprint(boolean pressed) {
        // Detect if this keybinding is a sprint key
        if (((StickyKeyBinding) (Object) this).getTranslationKey().equals("key.sprint")) {
            if (pressed && MinecraftClient.getInstance().player != null) {
                // Make the player stop sprinting if pressed the sprint key
                MinecraftClient.getInstance().player.setSprinting(false);
            }
            return !pressed;
        }
        return pressed;
    }
}