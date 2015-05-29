package tehnut.goodybags.proxies;

import tehnut.goodybags.items.ItemRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void load() {

    }

    @Override
    public void initRenders() {
        ItemRegistry.registerRenders();
    }
}
