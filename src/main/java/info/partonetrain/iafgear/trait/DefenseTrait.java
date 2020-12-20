package info.partonetrain.iafgear.trait;

import info.partonetrain.iafgear.IAFGear;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.silentchaos512.gear.api.traits.ITraitSerializer;
import net.silentchaos512.gear.gear.trait.SimpleTrait;

import java.util.Collection;

public class DefenseTrait extends SimpleTrait {

    private String protectionType; //unused

    private static final ResourceLocation SERIALIZER_ID = IAFGear.getId("defense_trait");

    public static final ITraitSerializer<DefenseTrait> SERIALIZER = new Serializer<>(
            SERIALIZER_ID,
            DefenseTrait::new,
            (trait, json) -> {
                trait.protectionType = JSONUtils.getString(json, "protection_type", trait.getId().getPath());
            },
            (trait, buffer) -> {
                trait.protectionType = buffer.readString();
            },
            (trait, buffer) -> {
                buffer.writeString(trait.protectionType);
            }
    );


    /**
     * Currently damage prevention is done in {@link info.partonetrain.iafgear.event.ServerEvents#onEntityDamage(LivingHurtEvent event)}
     * like in Ice and Fire. However I think it would make more sense to do it here, if SilentGear added a method to SimpleTrait.
     */
    /*
    @Override
    public float onTakeDamageWithArmor(TraitActionContext context, LivingHurtEvent event) {
        return event;
    }

     */
    public DefenseTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }

    @Override
    public Collection<String> getExtraWikiLines() {
        Collection<String> ret = super.getExtraWikiLines();
        ret.add("Prevents % amount of damage for the given damage type. Head: 10%, Chest: 30%, Legs: 20%, Feet: 10%");
        return ret;
    }

}
