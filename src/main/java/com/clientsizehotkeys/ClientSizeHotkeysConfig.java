package com.clientsizehotkeys;

import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

@ConfigGroup(ClientSizeHotkeysConfig.GROUP)
public interface ClientSizeHotkeysConfig extends Config
{
	String GROUP = "clientsizehotkeys";

	@ConfigItem(
		keyName = "resizeMode",
		name = "Resize mode",
		description = "Game size sets the size of the game itself, and the window grows to fit the sidebar and title bar. Window size sets the size of the whole window.",
		position = 0
	)
	default ResizeMode resizeMode()
	{
		return ResizeMode.GAME_SIZE;
	}

	@ConfigItem(
		keyName = "hotkey1",
		name = "Hotkey 1",
		description = "Resizes the client to size 1",
		position = 1
	)
	default Keybind hotkey1()
	{
		return new Keybind(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK);
	}

	@ConfigItem(
		keyName = "size1",
		name = "Size 1",
		description = "Size set by hotkey 1",
		position = 2
	)
	default Dimension size1()
	{
		return new Dimension(1024, 768);
	}

	@ConfigItem(
		keyName = "hotkey2",
		name = "Hotkey 2",
		description = "Resizes the client to size 2",
		position = 3
	)
	default Keybind hotkey2()
	{
		return new Keybind(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK);
	}

	@ConfigItem(
		keyName = "size2",
		name = "Size 2",
		description = "Size set by hotkey 2",
		position = 4
	)
	default Dimension size2()
	{
		return new Dimension(800, 600);
	}

	@ConfigItem(
		keyName = "hotkey3",
		name = "Hotkey 3",
		description = "Resizes the client to size 3",
		position = 5
	)
	default Keybind hotkey3()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "size3",
		name = "Size 3",
		description = "Size set by hotkey 3",
		position = 6
	)
	default Dimension size3()
	{
		return new Dimension(1280, 720);
	}

	@ConfigItem(
		keyName = "hotkey4",
		name = "Hotkey 4",
		description = "Resizes the client to size 4",
		position = 7
	)
	default Keybind hotkey4()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "size4",
		name = "Size 4",
		description = "Size set by hotkey 4",
		position = 8
	)
	default Dimension size4()
	{
		return new Dimension(1920, 1080);
	}
}
