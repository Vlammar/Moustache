package fr.zcraft.moustache.commands.moustache;

import fr.zcraft.moustache.Moustache;
import fr.zcraft.quartzlib.components.commands.Command;
import fr.zcraft.quartzlib.components.commands.CommandException;
import fr.zcraft.quartzlib.components.commands.CommandInfo;

import fr.zcraft.quartzlib.components.i18n.I;
import fr.zcraft.quartzlib.tools.PluginLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.List;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.shared.exception.SkinRequestException;
import net.skinsrestorer.shared.exception.SkinRequestException;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.PlayerWrapper;
import org.bukkit.entity.Player;


@CommandInfo (name = "toggle", usageParameters = "")
public final class MoustacheToggleCommand extends Command
{


    @Override
    protected void run() throws CommandException
    {


        Player player = (Player) sender;
        String skin = "amaurypi";

        PluginLogger.info(""+Moustache.getSkinrestorerAPI().getProfile(player.getUniqueId().toString()).toString());
        PluginLogger.info("Setting your skin to " + skin);

        try {
            // setskin for player skin
            Moustache.getSkinrestorerAPI().setSkin(player.getName(), skin);

            // Force skinrefresh for player
            Moustache.getSkinrestorerAPI().applySkin(new PlayerWrapper(player));
        } catch (SkinRequestException e) {
            //e.printStackTrace();
            PluginLogger.info("erreur");
        }
        

    }

    @Override
    protected List<String> complete() throws CommandException
    {
        // TODO implement auto-completion for /moustache toggle
        return null;
    }
}
