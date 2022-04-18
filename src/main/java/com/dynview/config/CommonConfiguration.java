package com.dynview.config;

import com.dynview.DynView;
import com.google.gson.JsonObject;

public class CommonConfiguration
{
    public int     minChunkViewDist         = 5;
    public int     maxChunkViewDist         = 15;
    public int     meanAvgTickTime          = 45;
    public int     viewDistanceUpdateRate   = 30;
    public boolean logMessages              = true;
    public boolean chunkunload              = true;
    public boolean adjustSimulationDistance = true;
    public int     minSimulationDist        = 4;
    public int     maxSimulationDist        = 10;

    public CommonConfiguration()
    {
    }

    public JsonObject serialize()
    {
        final JsonObject root = new JsonObject();

        final JsonObject entry = new JsonObject();
        entry.addProperty("desc:", "The minimum chunk view distance allowed to use. Default: 5, minimum 3, maximum 200");
        entry.addProperty("minChunkViewDist", minChunkViewDist);
        root.add("minChunkViewDist", entry);

        final JsonObject entry2 = new JsonObject();
        entry2.addProperty("desc:", "The maximum chunk view distance allowed to use. Set to the max a player could benefit from. Default: 15, minimum 1, maximum 200");
        entry2.addProperty("maxChunkViewDist", maxChunkViewDist);
        root.add("maxChunkViewDist", entry2);

        final JsonObject entry3 = new JsonObject();
        entry3.addProperty("desc:",
          "The average tick time to stabilize the distances around. Setting it higher than 50ms is not advised, as after 50ms the TPS will go below 20. Default: 45ms, min: 10, max:100");
        entry3.addProperty("meanAvgTickTime", meanAvgTickTime);
        root.add("meanAvgTickTime", entry3);

        final JsonObject entry4 = new JsonObject();
        entry4.addProperty("desc:", "The change frequency of distances in seconds. Default: 30, min:1, max:1000");
        entry4.addProperty("viewDistanceUpdateRate", viewDistanceUpdateRate);
        root.add("viewDistanceUpdateRate", entry4);

        final JsonObject entry8 = new JsonObject();
        entry8.addProperty("desc:", "The minimum simulation distance allowed to use. Default: 4, minimum 1, maximum 200");
        entry8.addProperty("minSimulationDist", minSimulationDist);
        root.add("minSimulationDist", entry8);

        final JsonObject entry9 = new JsonObject();
        entry9.addProperty("desc:", "The maximum simulation distance allowed to use. Default: 10, minimum 1, maximum 200");
        entry9.addProperty("maxSimulationDist", maxSimulationDist);
        root.add("maxSimulationDist", entry9);

        final JsonObject entry7 = new JsonObject();
        entry7.addProperty("desc:", "Enables automatic simulation distance adjustment. Default: true");
        entry7.addProperty("adjustSimulationDistance", adjustSimulationDistance);
        root.add("adjustSimulationDistance", entry7);

        final JsonObject entry5 = new JsonObject();
        entry5.addProperty("desc:", "Whether to output log messages for actions done. This can be helpful to balance the other settings nicely. Default = true");
        entry5.addProperty("logMessages", logMessages);
        root.add("logMessages", entry5);

        final JsonObject entry6 = new JsonObject();
        entry6.addProperty("desc:", "Enable slow chunk unloading(~1minute) after load, helps with lag caused by mods hot-loading chunks frequently. Default: true");
        entry6.addProperty("chunkunload", chunkunload);
        root.add("chunkunload", entry6);

        return root;
    }

    public void deserialize(JsonObject data)
    {
        if (data == null)
        {
            DynView.LOGGER.error("Config file was empty!");
            return;
        }
        minChunkViewDist = data.get("minChunkViewDist").getAsJsonObject().get("minChunkViewDist").getAsInt();
        minSimulationDist = data.get("minSimulationDist").getAsJsonObject().get("minSimulationDist").getAsInt();
        maxSimulationDist = data.get("maxSimulationDist").getAsJsonObject().get("maxSimulationDist").getAsInt();
        maxChunkViewDist = data.get("maxChunkViewDist").getAsJsonObject().get("maxChunkViewDist").getAsInt();
        meanAvgTickTime = data.get("meanAvgTickTime").getAsJsonObject().get("meanAvgTickTime").getAsInt();
        viewDistanceUpdateRate = data.get("viewDistanceUpdateRate").getAsJsonObject().get("viewDistanceUpdateRate").getAsInt();
        logMessages = data.get("logMessages").getAsJsonObject().get("logMessages").getAsBoolean();
        chunkunload = data.get("chunkunload").getAsJsonObject().get("chunkunload").getAsBoolean();
        adjustSimulationDistance = data.get("adjustSimulationDistance").getAsJsonObject().get("adjustSimulationDistance").getAsBoolean();
    }
}
