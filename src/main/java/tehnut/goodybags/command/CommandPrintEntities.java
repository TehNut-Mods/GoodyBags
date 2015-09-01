package tehnut.goodybags.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import tehnut.goodybags.GoodyBags;
import tehnut.goodybags.util.Utils;

public class CommandPrintEntities extends CommandBase {

    public CommandPrintEntities() {
        super();
    }

    @Override
    public String getCommandName() {
        return "printEntities";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        for (String name : Utils.getEntityNames())
            GoodyBags.logger.info(name);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}