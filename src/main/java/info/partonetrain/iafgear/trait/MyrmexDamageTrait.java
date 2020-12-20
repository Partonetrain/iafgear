package info.partonetrain.iafgear.trait;

import com.github.alexthe666.iceandfire.entity.EntityDeathWorm;
import info.partonetrain.iafgear.IAFGear;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.silentchaos512.gear.api.traits.ITraitSerializer;
import net.silentchaos512.gear.api.traits.TraitActionContext;
import net.silentchaos512.gear.gear.trait.SimpleTrait;

import java.util.Collection;

public class MyrmexDamageTrait extends SimpleTrait {

    private static final float BONUS_DAMAGE = 4F;

    private static final ResourceLocation SERIALIZER_ID = IAFGear.getId("myrmex_damage_trait");

    public static final ITraitSerializer<MyrmexDamageTrait> SERIALIZER = new Serializer<>(
            SERIALIZER_ID,
            MyrmexDamageTrait::new
    );

    @Override
    public float onAttackEntity(TraitActionContext context, LivingEntity target, float baseValue) {
        float newDamage = baseValue + BONUS_DAMAGE;
        if ((target.getCreatureAttribute() != CreatureAttribute.ARTHROPOD) || target instanceof EntityDeathWorm) {
            target.attackEntityFrom(DamageSource.GENERIC, newDamage);
        } else {
            target.attackEntityFrom(DamageSource.GENERIC, baseValue) ;
        }

        //since doing damage here prevents normal knockback for some reason.
        //temporary workaround constant knockback
        target.applyKnockback(0.4F, context.getPlayer().getPosX() - target.getPosX(), context.getPlayer().getPosZ() - target.getPosZ());
        return baseValue;
    }

    public MyrmexDamageTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }

    //no GetExtraWikiLines necessary

}
