package cn.dancingsnow.createstates.mixins;

import cn.dancingsnow.createstates.CreateStates;
import cn.dancingsnow.createstates.GradientUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.api.equipment.goggles.IHaveHoveringInformation;
import com.simibubi.create.content.equipment.goggles.GoggleOverlayRenderer;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.createmod.catnip.lang.LangBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(value = GoggleOverlayRenderer.class, remap = false)
public class GoggleOverlayRendererMixin {

    @WrapOperation(
        method = "renderOverlay",
        at = @At(
            value = "INVOKE",
            target = "Lcom/simibubi/create/api/equipment/goggles/IHaveHoveringInformation;addToTooltip(Ljava/util/List;Z)Z"
        )
    )
    private static boolean onAddTooltip(
        IHaveHoveringInformation instance,
        List<Component> tooltip,
        boolean b,
        Operation<Boolean> original,
        @Local(index = 11) BlockEntity be
    ) {
        original.call(instance, tooltip, b);
        if (be instanceof KineticBlockEntity kbe) {
            if (kbe.isOverStressed()) {
                new LangBuilder(CreateStates.MODID)
                    .add(Component.translatable("tooltip.create_states.type_2").withStyle(GradientUtil.RAINBOW))
                    .forGoggles(tooltip);
            } else if (kbe.getTheoreticalSpeed() <= 0) {
                new LangBuilder(CreateStates.MODID)
                    .add(Component.translatable("tooltip.create_states.type_0").withStyle(GradientUtil.RAINBOW))
                    .forGoggles(tooltip);
            } else {
                new LangBuilder(CreateStates.MODID)
                    .add(Component.translatable("tooltip.create_states.type_1").withStyle(GradientUtil.RAINBOW))
                    .forGoggles(tooltip);
            }
        }
        return true;
    }
}
