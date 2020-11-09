package fallingleaves.fallingleaves.client;

import fallingleaves.fallingleaves.FallingLeavesConfig;
import fallingleaves.fallingleaves.particle.FallingLeafParticle;
import fallingleaves.fallingleaves.particle.FallingSpruceLeafParticle;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class FallingLeavesClient implements ClientModInitializer {

    public static FallingLeavesConfig config;

    public static Identifier id(String path) {
        return new Identifier("fallingleaves", path);
    }

    public static final Block[] coniferLeaves = new Block[] {
        Blocks.SPRUCE_LEAVES
    };

    public static DefaultParticleType FALLING_LEAF;
    public static DefaultParticleType FALLING_SPRUCE_LEAF;

    @SuppressWarnings("rawtypes")
    public static boolean isCustomParticle(ParticleType p) {
        return p == FALLING_LEAF || p == FALLING_SPRUCE_LEAF;
    }

    @Override
    public void onInitializeClient() {
        AutoConfig.register(FallingLeavesConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(FallingLeavesConfig.class).getConfig();

        FALLING_LEAF = Registry.register(Registry.PARTICLE_TYPE, id("falling_leaf"), FabricParticleTypes.simple());
        ParticleFactoryRegistry.getInstance().register(FALLING_LEAF, FallingLeafParticle.DefaultFactory::new);

        FALLING_SPRUCE_LEAF = Registry.register(Registry.PARTICLE_TYPE, id("falling_leaf_spruce"), FabricParticleTypes.simple());
        ParticleFactoryRegistry.getInstance().register(FALLING_SPRUCE_LEAF, FallingSpruceLeafParticle.DefaultFactory::new);
    }
}
