package info.partonetrain.iafgear.trait;

import com.github.alexthe666.citadel.server.entity.datatracker.EntityPropertiesHandler;
import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.github.alexthe666.iceandfire.entity.props.FrozenEntityProperties;
import info.partonetrain.iafgear.IAFGear;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.silentchaos512.gear.api.traits.ITraitSerializer;
import net.silentchaos512.gear.api.traits.TraitActionContext;
import net.silentchaos512.gear.gear.trait.SimpleTrait;

import java.util.Collection;

public class DragonDamageTrait extends SimpleTrait {

    private String damageType;
    private int effectScale = 15;    //this could be maybe be serialized from json
    private static final int EFFECT_MULTIPLIER = 20;
    private static final float BONUS_DAMAGE = 13.5F;
    private static final float BONUS_DAMAGE_LIGHTNING = 9.5F;

    private static final ResourceLocation SERIALIZER_ID = IAFGear.getId("dragon_damage_trait");

    public static final ITraitSerializer<DragonDamageTrait> SERIALIZER = new Serializer<>(
            SERIALIZER_ID,
            DragonDamageTrait::new,
            (trait, json) -> {
                trait.damageType = JSONUtils.getString(json, "damage_type", trait.getId().getPath());
            },
            (trait, buffer) -> {
                trait.damageType = buffer.readString();
            },
            (trait, buffer) -> {
                buffer.writeString(trait.damageType);
            }
    );


    @Override
    public float onAttackEntity(TraitActionContext context, LivingEntity target, float baseValue) {
        float damageToDeal = baseValue;
        switch (damageType) {
            case "fire":
                if (target instanceof EntityIceDragon) {
                    //target.attackEntityFrom(DamageSource.IN_FIRE, BONUS_DAMAGE);
                    damageToDeal = baseValue + BONUS_DAMAGE;
                }
                burn(target);
                break;
            case "ice":
                if (target instanceof EntityFireDragon) {
                    //target.attackEntityFrom(DamageSource.DROWN, BONUS_DAMAGE);
                    damageToDeal = baseValue + BONUS_DAMAGE;
                }
                freeze(target);
                break;
            case "lightning":
                if (target instanceof EntityFireDragon || target instanceof EntityIceDragon) {
                    //target.attackEntityFrom(DamageSource.LIGHTNING_BOLT, BONUS_DAMAGE_LIGHTNING);
                    damageToDeal = baseValue + BONUS_DAMAGE_LIGHTNING;;
                }
                lightning(target, context.getPlayer());
                break;
            default:
                break;
        }
        //doKnockback(target, context.getPlayer());

        return damageToDeal;
    }

    public void burn(LivingEntity target) {
        target.setFire(effectScale);
    }

    public void freeze(LivingEntity target) {
        FrozenEntityProperties frozenProps = EntityPropertiesHandler.INSTANCE.getProperties(target, FrozenEntityProperties.class);
        frozenProps.setFrozenFor(effectScale * EFFECT_MULTIPLIER);
        target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, effectScale * EFFECT_MULTIPLIER, 2));
    }

    public void lightning(LivingEntity target, LivingEntity attacker) {
        boolean canSpawnLightning = true;
        if (attacker.swingProgress > 0.2) {
            canSpawnLightning = false;
        }
        if (!attacker.world.isRemote && canSpawnLightning) {
            LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(target.world);
            lightningboltentity.moveForced(target.getPositionVec());
            if (!target.world.isRemote) {
                target.world.addEntity(lightningboltentity);
            }
        }
    }

    /*
    public void doKnockback(LivingEntity target, LivingEntity attacker){
        target.applyKnockback( 1F, attacker.getPosX() - target.getPosX(), attacker.getPosZ() - target.getPosZ());
    }
     */

    public DragonDamageTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }

    @Override
    public Collection<String> getExtraWikiLines() {
        Collection<String> ret = super.getExtraWikiLines();
        switch (this.damageType) {
            case "fire":
                ret.add("Sets fire to target for " + effectScale + " seconds and deals " + BONUS_DAMAGE + " damage to Ice Dragons");
                break;
            case "ice":
                ret.add("Freezes target for " + (effectScale * EFFECT_MULTIPLIER) + " seconds and deals " + BONUS_DAMAGE + " damage to Fire Dragons");
                break;
            case "lightning":
                ret.add("Strikes target with lightning and deals " + BONUS_DAMAGE_LIGHTNING + " damage to Fire or Ice Dragons");
                break;
            default:
                ret.add("Invalid DragonDamageTrait");
                break;
        }
        return ret;
    }

}
