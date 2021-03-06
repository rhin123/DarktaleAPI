package com.darktale.darktaleapi;

import com.darktale.darktaleapi.data.clan.Clan;
import com.darktale.darktaleapi.data.player.DarktalePlayer;
import com.darktale.darktaleapi.data.player.command.APICommandHandler;
import com.darktale.darktaleapi.data.serverconfig.ServerConfig;
import com.darktale.darktaleapi.debug.DebugCommandListener;
import com.darktale.darktaleapi.event.EventHandler;
import com.darktale.darktaleapi.event.player.APIPlayerCommandEvent;
import com.darktale.darktaleapi.listener.ListenerHandler;
import com.google.gson.Gson;

public class DarktaleAPI {

    private static DarktaleAPI api;

    private Gson gson;
    private ServerConfig serverConfig;

    private ListenerHandler listenerHandler;
    private EventHandler eventHandler;
    private APICommandHandler commandHandler;

    public DarktaleAPI() {
        this.gson = new Gson();
        this.serverConfig = new ServerConfig();
    }

    public void saveStates() {
        DarktalePlayer.savePlayerStates();
        Clan.saveClanStates();
    }

    public void setListenerHandler(ListenerHandler listenerHandler) {
        this.listenerHandler = listenerHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void setCommandHandler(APICommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public Gson getGSON() {
        return gson;
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public EventHandler eventHandler() {
        return eventHandler;
    }

    public ListenerHandler listenerHandler() {
        return listenerHandler;
    }

    public APICommandHandler commandHandler() {
        return commandHandler;
    }

    public static void setAPI(DarktaleAPI darktaleAPI) {
        api = darktaleAPI;
    }

    public static DarktaleAPI getAPI() {
        if (api == null) {
            api = new DarktaleAPI();
        }

        return api;
    }

    public static void main(String[] args) {
        DarktaleAPI.getAPI().setListenerHandler(new ListenerHandler());
        DarktaleAPI.getAPI().listenerHandler().registerListener("debugCommandListener", new DebugCommandListener());

        DarktaleAPI.getAPI().setEventHandler(new EventHandler());
        DarktaleAPI.getAPI().setCommandHandler(new APICommandHandler());

        DarktalePlayer player = DarktalePlayer.getPlayer("Rhin-ID", "Rhin");
        //DarktalePlayer otherPlayer = DarktalePlayer.getPlayer("Bob-ID", "Bob");

        //DarktaleAPI.getAPI().eventHandler().callEvent(
        //         new APIPlayerCommandEvent("Rhin-ID", player.getName(), "/clan create Pop"));
        //DarktaleAPI.getAPI().eventHandler().callEvent(
        //        new APIPlayerCommandEvent("Rhin-ID", player.getName(), "/clan invite Bob"));
        //DarktaleAPI.getAPI().eventHandler().callEvent(
        //        new APIPlayerCommandEvent("Bob-ID", player.getName(), "/clan join Pop"));
        DarktaleAPI.getAPI().eventHandler().callEvent(
                new APIPlayerCommandEvent("Rhin-ID", player.getName(), "/clan kick Bob"));
        // DarktaleAPI.getAPI().eventHandler().callEvent(
        //         new APIPlayerCommandEvent("Bob-ID", player.getName(), "/clan leave Pop"));

        DarktaleAPI.getAPI().saveStates();
    }
}
