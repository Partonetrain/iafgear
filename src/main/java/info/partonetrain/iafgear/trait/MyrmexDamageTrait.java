package info.partonetrain.iafgear.trait;

import com.github.alexthe666.iceandfire.entity.EntityDeathWorm;
import info.partonetrain.iafgear.IAFGear;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.silentchaos512.gear.api.traits.ITraitSerializer;
import net.silentchaos512.gear.api.traits.TraitActionContext;
import net.silentchaos512.gear.gear.trait.SimpleTrait;

public class MyrmexDamageTrait extends SimpleTrait {

    private static final float BONUS_DAMAGE = 5F;

    private static final ResourceLocation SERIALIZER_ID = IAFGear.getId("myrmex_damage_trait");

    public static final ITraitSerializer<MyrmexDamageTrait> SERIALIZER = new Serializer<>(
            SERIALIZER_ID,
            MyrmexDamageTrait::new
    );

    /*TODO: fix myrmex trait
    This is getting called twice, once on render thread once on server thread. It's not actually dealing bonus damage.
    Possibly a bug in SGear, waiting on fix
    */
    @Override
    public float onAttackEntity(TraitActionContext context, LivingEntity target, float baseValue) {
        if ((target.getCreatureAttribute() != CreatureAttribute.ARTHROPOD) || target instanceof EntityDeathWorm) {
            float newDamage = baseValue + BONUS_DAMAGE;
            System.out.println("Dealt Bonus Damage: " + newDamage);
            return newDamage;
        } else {
            System.out.println("Did NOT deal bonus damage: " + baseValue);
            return baseValue;
        }
    }

    public MyrmexDamageTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }

}
