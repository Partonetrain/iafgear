package info.partonetrain.iafgear.trait;

import com.github.alexthe666.iceandfire.event.ServerEvents;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import info.partonetrain.iafgear.IAFGear;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.silentchaos512.gear.api.traits.ITraitSerializer;
import net.silentchaos512.gear.gear.trait.SimpleTrait;

public class PhantasmalTrait extends SimpleTrait {


    private static final ResourceLocation SERIALIZER_ID = IAFGear.getId("phantasmal_trait");

    public static final ITraitSerializer<PhantasmalTrait> SERIALIZER = new Serializer<>(SERIALIZER_ID, PhantasmalTrait::new);

    //I&F uses onItemUseFinish (left click) but this will do for now
    @Override
    public ActionResultType onItemUse(ItemUseContext context, int traitLevel) {
        if(context.getPlayer() != null){
            //just use the event that's already there
            ServerEvents.onLeftClick(context.getPlayer(), IafItemRegistry.GHOST_SWORD.getDefaultInstance());
        }
        return ActionResultType.PASS;
    }


    public PhantasmalTrait(ResourceLocation id) {
        super(id, SERIALIZER);
    }

}
