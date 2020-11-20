package info.partonetrain.iafgear.trait;

import com.github.alexthe666.iceandfire.event.ServerEvents;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import info.partonetrain.iafgear.IAFGear;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.silentchaos512.gear.api.traits.ITraitSerializer;
import net.silentchaos512.gear.gear.trait.SimpleTrait;

public class PhantasmalTrait extends SimpleTrait {


    private static final ResourceLocation SERIALIZER_ID = IAFGear.getId("phantasmal_trait");

    public static final ITraitSerializer<PhantasmalTrait> SERIALIZER = new Serializer<>(SERIALIZER_ID, PhantasmalTrait::new);

    @Override
    public void onItemSwing(ItemStack stack, LivingEntity entity, int traitLevel) {
        //just use the event that's already there
        ServerEvents.onLeftClick((PlayerEntity)entity, IafItemRegistry.GHOST_SWORD.getDefaultInstance());
    }


    public PhantasmalTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }

}
