package com.clientsizehotkeys;

import com.google.inject.Provides;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyEvent;
import javax.inject.Inject;
import javax.swing.SwingUtilities;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.Keybind;
import net.runelite.client.config.RuneLiteConfig;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ContainableFrame;

@PluginDescriptor(
	name = "Client Size Hotkeys",
	description = "Resizes the client to a set size with a hotkey",
	tags = {"client", "resize", "size", "game size", "hotkey", "window"}
)
public class ClientSizeHotkeysPlugin extends Plugin implements KeyListener
{
	private static final String GAME_SIZE_KEY = "gameSize";

	@Inject private Client client;
	@Inject private ClientSizeHotkeysConfig config;
	@Inject private ConfigManager configManager;
	@Inject private KeyManager keyManager;

	@Override
	protected void startUp()
	{
		keyManager.registerKeyListener(this);
	}

	@Override
	protected void shutDown()
	{
		keyManager.unregisterKeyListener(this);
	}

	@Override
	public boolean isEnabledOnLoginScreen()
	{
		return true;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (resize(config.hotkey1(), config.size1(), e)
			|| resize(config.hotkey2(), config.size2(), e)
			|| resize(config.hotkey3(), config.size3(), e)
			|| resize(config.hotkey4(), config.size4(), e))
		{
			e.consume();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	private boolean resize(Keybind hotkey, Dimension size, KeyEvent e)
	{
		if (!hotkey.matches(e))
		{
			return false;
		}

		if (config.resizeMode() == ResizeMode.WINDOW_SIZE)
		{
			setWindowSize(size);
		}
		else
		{
			configManager.setConfiguration(RuneLiteConfig.GROUP_NAME, GAME_SIZE_KEY, size);
		}

		return true;
	}

	private void setWindowSize(Dimension size)
	{
		Window window = SwingUtilities.getWindowAncestor(client.getCanvas());
		if (!(window instanceof ContainableFrame))
		{
			return;
		}

		ContainableFrame frame = (ContainableFrame) window;
		if ((frame.getExtendedState() & Frame.MAXIMIZED_BOTH) == 0)
		{
			frame.containedSetSize(size, frame.getBounds());
			frame.validate();
		}
	}

	@Provides
	ClientSizeHotkeysConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ClientSizeHotkeysConfig.class);
	}
}
