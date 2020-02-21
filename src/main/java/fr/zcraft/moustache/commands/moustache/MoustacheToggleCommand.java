package fr.zcraft.moustache.commands.moustache;

import fr.zcraft.zlib.components.commands.Command;
import fr.zcraft.zlib.components.commands.CommandException;
import fr.zcraft.zlib.components.commands.CommandInfo;

import java.util.List;


@CommandInfo (name = "toggle", usageParameters = "")
public final class MoustacheToggleCommand extends Command
{
    @Override
    protected void run() throws CommandException
    {
        // TODO implement command /moustache toggle
    }

    @Override
    protected List<String> complete() throws CommandException
    {
        // TODO implement auto-completion for /moustache toggle
        return null;
    }
}
