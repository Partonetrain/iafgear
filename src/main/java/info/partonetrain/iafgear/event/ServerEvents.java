package info.partonetrain.iafgear.event;

import com.github.alexthe666.iceandfire.misc.IafDamageRegistry;
import info.partonetrain.iafgear.IAFGear;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.silentchaos512.gear.util.TraitHelper;

@Mod.EventBusSubscriber(modid = IAFGear.MOD_ID)
public class ServerEvents {

    private static ResourceLocation DRAGON_PROTECTION_TRAIT_RL = new ResourceLocation("iafgear:dragon_defense");
    private static ResourceLocation PROJECTILE_PROTECTION_TRAIT_RL = new ResourceLocation("iafgear:projectile_defense");
    private static ResourceLocation TIDE_GUARDIAN_TRAIT_RL = new ResourceLocation("iafgear:tide_guardian");

    @SubscribeEvent
    public static void onEntityDamage(LivingHurtEvent event) {
        ItemStack h = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD);
        ItemStack c = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.CHEST);
        ItemStack l = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.LEGS);
        ItemStack f = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET);

        if (event.getSource().isProjectile()) {
            float multi = 1;
            if (TraitHelper.getTraitLevel(h, PROJECTILE_PROTECTION_TRAIT_RL) != 0) {
                multi -= 0.1;
            }
            if (TraitHelper.getTraitLevel(c, PROJECTILE_PROTECTION_TRAIT_RL) != 0) {
                multi -= 0.3;
            }
            if (TraitHelper.getTraitLevel(l, PROJECTILE_PROTECTION_TRAIT_RL) != 0) {
                multi -= 0.2;
            }
            if (TraitHelper.getTraitLevel(f, PROJECTILE_PROTECTION_TRAIT_RL) != 0) {
                multi -= 0.1;
            }
            event.setAmount(event.getAmount() * multi);
        }
        if (event.getSource() == IafDamageRegistry.DRAGON_FIRE || event.getSource() == IafDamageRegistry.DRAGON_ICE) {
            float multi = 1;
            if (TraitHelper.getTraitLevel(h, DRAGON_PROTECTION_TRAIT_RL) != 0) {
                multi -= 0.1;
            }
            if (TraitHelper.getTraitLevel(c, DRAGON_PROTECTION_TRAIT_RL) != 0) {
                multi -= 0.3;
            }
            if (TraitHelper.getTraitLevel(l, DRAGON_PROTECTION_TRAIT_RL) != 0) {
                multi -= 0.2;
            }
            if (TraitHelper.getTraitLevel(f, DRAGON_PROTECTION_TRAIT_RL) != 0) {
                multi -= 0.1;
            }
            event.setAmount(event.getAmount() * multi);
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        ItemStack itemStack;
        int amountEquipped = 0;

        for (EquipmentSlotType e : EquipmentSlotType.values()) {
            itemStack = event.getEntityLiving().getItemStackFromSlot(e);
            if (TraitHelper.getTraitLevel(itemStack, TIDE_GUARDIAN_TRAIT_RL) != 0) {
                if (event.getEntityLiving().isWet()) {
                    amountEquipped++;
                }
            }
        }
        if (amountEquipped >= 1) {
            event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.STRENGTH, 50, amountEquipped - 1, false, false));
        }
    }
}
