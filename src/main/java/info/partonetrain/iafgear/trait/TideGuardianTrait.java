package info.partonetrain.iafgear.trait;

import info.partonetrain.iafgear.IAFGear;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.silentchaos512.gear.api.traits.ITraitSerializer;
import net.silentchaos512.gear.gear.trait.SimpleTrait;

import java.util.Collection;

public class TideGuardianTrait extends SimpleTrait {

    private static final ResourceLocation SERIALIZER_ID = IAFGear.getId("tide_guardian_trait");

    public static final ITraitSerializer<TideGuardianTrait> SERIALIZER = new Serializer<>(
            SERIALIZER_ID,
            TideGuardianTrait::new
    );

    //everything is done in ServerEvents, may use this serializer in the future

    public TideGuardianTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }

    @Override
    public Collection<String> getExtraWikiLines() {
        Collection<String> ret = super.getExtraWikiLines();
        ret.add("When wet, gives 1 level of Strength for every gear with this trait equipped.");
        return ret;
    }

}
