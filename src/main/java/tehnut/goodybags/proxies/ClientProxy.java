package tehnut.goodybags.proxies;

import net.minecraftforge.client.ClientCommandHandler;
import tehnut.goodybags.command.CommandPrintEntities;

public class ClientProxy extends CommonProxy {

    @Override
    public void load() {

    }

    @Override
    public void registerCommands() {
        ClientCommandHandler.instance.registerCommand(new CommandPrintEntities());
    }
}
