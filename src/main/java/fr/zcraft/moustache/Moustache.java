package fr.zcraft.moustache;

import fr.zcraft.zlib.core.ZPlugin;
import fr.zcraft.zlib.components.commands.Commands;
import fr.zcraft.moustache.listeners.PlayersListener;
import fr.zcraft.moustache.commands.moustache.MoustacheToggleCommand;


public final class Moustache extends ZPlugin
{
    private static Moustache instance;

    @Override
    public void onEnable()
    {
        instance = this;

        loadComponents(Commands.class, PlayersListener.class);
        
        Commands.register("moustache", MoustacheToggleCommand.class);

    }

    public static Moustache get()
    {
        return instance;
    }
}
