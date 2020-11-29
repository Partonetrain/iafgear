package info.partonetrain.iafgear.item;

import com.github.alexthe666.iceandfire.IceAndFire;
import info.partonetrain.iafgear.IAFGear;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ShinyScaleItem extends Item{

    public static final ResourceLocation registryName = new ResourceLocation(IAFGear.MOD_ID, "shiny_scale_");

    public ShinyScaleItem(String colorString){
        super(new Item.Properties().group(IceAndFire.TAB_ITEMS));
        this.setRegistryName(registryName + colorString);
    }
}
