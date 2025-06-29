package cn.dancingsnow.createstates;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum KineticBlockProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;


    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        CompoundTag data = blockAccessor.getServerData();
        if (data.contains("type")) {
            int type = data.getInt("type");
            switch (type) {
                case 0:
                    iTooltip.add(Component.translatable("tooltip.create_states.type_0").withStyle(GradientUtil.RAINBOW));
                    break;
                case 1:
                    iTooltip.add(Component.translatable("tooltip.create_states.type_1").withStyle(GradientUtil.RAINBOW));
                    break;
                case 2:
                    iTooltip.add(Component.translatable("tooltip.create_states.type_2").withStyle(GradientUtil.RAINBOW));
                    break;
            }
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlockEntity() instanceof KineticBlockEntity be) {
            /* types
               0: no rotate
               1: rotate
               2: overStressed
             */
            if (be.isOverStressed()) {
                compoundTag.putInt("type", 2);
            } else if (be.getTheoreticalSpeed() <= 0) {
                compoundTag.putInt("type", 0);
            } else {
                compoundTag.putInt("type", 1);
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return CreateStates.id("kinetic");
    }
}
