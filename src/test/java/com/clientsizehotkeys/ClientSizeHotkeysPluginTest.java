package com.clientsizehotkeys;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ClientSizeHotkeysPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ClientSizeHotkeysPlugin.class);
		RuneLite.main(args);
	}
}
