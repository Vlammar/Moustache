package fr.zcraft.moustache;

import fr.zcraft.quartzlib.core.QuartzPlugin;
import fr.zcraft.quartzlib.components.commands.Commands;
import fr.zcraft.moustache.listeners.PlayersListener;
import fr.zcraft.moustache.commands.moustache.MoustacheToggleCommand;
import fr.zcraft.quartzlib.tools.PluginLogger;
import net.skinsrestorer.bukkit.SkinsGUI;
import net.skinsrestorer.bukkit.SkinsRestorer;
import net.skinsrestorer.bukkit.skinfactory.SkinFactory;
import net.skinsrestorer.shared.storage.SkinStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.skinsrestorer.api.bukkit.BukkitHeadAPI;
import net.skinsrestorer.shared.exception.SkinRequestException;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.bukkit.skinfactory.UniversalSkinFactory;
public final class Moustache extends QuartzPlugin
{


    private static SkinsRestorerAPI skinsRestorerAPI;

    public static Plugin getPlugin() {
        return getPlugin();
    }
    /* @Override
        public void onEnable()
        {

            instance = this;

            //loadComponents(Commands.class, PlayersListener.class);

            Commands.register("moustache", MoustacheToggleCommand.class);




        }*/



    @Override
    public void onEnable() {


        // Connecting to Bukkit API for applying the skin
        skinsRestorerAPI = SkinsRestorerAPI.getApi();

        //Commands.register("moustache", MoustacheToggleCommand.class);
        getServer().getPluginManager().registerEvents(new PlayersListener(), this);
    }


    public static SkinsRestorerAPI getSkinrestorerAPI()
    {
        return skinsRestorerAPI;
    }
}
