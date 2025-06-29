package cn.dancingsnow.createstates;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(CreateStates.MODID)
public class CreateStates {
    public static final String MODID = "create_states";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static long CLIENT_TICK = 0;

    public CreateStates() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public static ResourceLocation id(String id) {
        return new ResourceLocation(MODID, id);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientEvents {

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                CLIENT_TICK++;
            }
        }
    }
}
