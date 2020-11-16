package info.partonetrain.iafgear.init;


import info.partonetrain.iafgear.trait.DragonDamageTrait;
import info.partonetrain.iafgear.trait.PhantasmalTrait;
import net.silentchaos512.gear.gear.trait.TraitSerializers;

public class IAFGearTraits {
    public static void registerSerializers() {
        TraitSerializers.register(DragonDamageTrait.SERIALIZER);
        TraitSerializers.register(PhantasmalTrait.SERIALIZER);

    }
}