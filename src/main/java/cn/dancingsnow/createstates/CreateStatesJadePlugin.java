package cn.dancingsnow.createstates;

 import com.simibubi.create.content.kinetics.base.KineticBlock;
 import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class CreateStatesJadePlugin implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(KineticBlockProvider.INSTANCE, KineticBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(KineticBlockProvider.INSTANCE, KineticBlock.class);
    }
}
