package com.tristian.monumentabaernecessities.features.debug;

import com.tristian.monumentabaernecessities.api.Items;
import com.tristian.monumentabaernecessities.utils.ItemColors;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

// this class contains a few debug features
public class ItemDebuggingHelpers {

    public static void register() {

        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> dispatcher.register(literal("tryparseitem")
                        .executes(context -> {
                            ItemStack mainStack = context.getSource().getPlayer().getMainHandStack();
                            context.getSource().getPlayer().sendMessage(Text.literal("For item " + mainStack + "with nbt:\n" + mainStack.getNbt().getCompound("Monumenta")));
                            context.getSource().getPlayer().sendMessage(Text.literal("-----------------"));
                            context.getSource().getPlayer().sendMessage(Text.literal("Parsed as " + Items.fromNbt(mainStack.getNbt()).map(x -> {
                                context.getSource().getPlayer().sendMessage(Text.literal("Which should also have color : " + ItemColors.getColorForLocation(x.getLocation().get().getJsonValue())));
                                return x;
                            })));
                            return 1;
                        })));
    }
}
