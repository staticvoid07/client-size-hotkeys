package com.clientsizehotkeys;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyManager;
import net.runelite.client.ui.ContainableFrame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HotkeyResizeTest
{
	@Spy private ClientSizeHotkeysConfig config = new ClientSizeHotkeysConfig()
	{
	};
	@Mock private Client client;
	@Mock private Canvas canvas;
	@Mock private ContainableFrame frame;
	@Mock private ConfigManager configManager;
	@Mock private KeyManager keyManager;
	@InjectMocks private ClientSizeHotkeysPlugin plugin;

	private static KeyEvent press(int modifiers, int keyCode, char keyChar)
	{
		return new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 0, modifiers, keyCode, keyChar)
		{
			// Keybind matches on this, and only events from real input have it set
			@Override
			public int getExtendedKeyCode()
			{
				return keyCode;
			}
		};
	}

	@Test
	public void hotkeySetsItsGameSizeAndConsumesTheKey()
	{
		KeyEvent first = press(InputEvent.CTRL_DOWN_MASK, KeyEvent.VK_1, '1');
		plugin.keyPressed(first);
		verify(configManager).setConfiguration("runelite", "gameSize", new Dimension(1024, 768));
		assertTrue(first.isConsumed());

		KeyEvent second = press(InputEvent.CTRL_DOWN_MASK, KeyEvent.VK_2, '2');
		plugin.keyPressed(second);
		verify(configManager).setConfiguration("runelite", "gameSize", new Dimension(800, 600));
		assertTrue(second.isConsumed());
	}

	private void useWindowSizeMode()
	{
		when(config.resizeMode()).thenReturn(ResizeMode.WINDOW_SIZE);
		when(client.getCanvas()).thenReturn(canvas);
		when(canvas.getParent()).thenReturn(frame);
	}

	@Test
	public void windowSizeModeResizesTheWindowInsteadOfTheGame()
	{
		useWindowSizeMode();
		Rectangle bounds = new Rectangle(10, 20, 1200, 900);
		when(frame.getBounds()).thenReturn(bounds);

		KeyEvent key = press(InputEvent.CTRL_DOWN_MASK, KeyEvent.VK_1, '1');
		plugin.keyPressed(key);

		verify(frame).containedSetSize(new Dimension(1024, 768), bounds);
		verify(configManager, never()).setConfiguration(anyString(), anyString(), any(Dimension.class));
		assertTrue(key.isConsumed());
	}

	@Test
	public void windowSizeModeLeavesAMaximizedWindowAlone()
	{
		useWindowSizeMode();
		when(frame.getExtendedState()).thenReturn(Frame.MAXIMIZED_BOTH);

		KeyEvent key = press(InputEvent.CTRL_DOWN_MASK, KeyEvent.VK_1, '1');
		plugin.keyPressed(key);

		verify(frame, never()).containedSetSize(any(Dimension.class), any(Rectangle.class));
		assertTrue(key.isConsumed());
	}

	@Test
	public void otherKeysAreLeftAlone()
	{
		KeyEvent plain = press(0, KeyEvent.VK_1, '1');
		plugin.keyPressed(plain);
		verify(configManager, never()).setConfiguration(anyString(), anyString(), any(Dimension.class));
		assertFalse(plain.isConsumed());
	}

	@Test
	public void listensOnTheLoginScreen()
	{
		assertTrue(plugin.isEnabledOnLoginScreen());
	}

	@Test
	public void keyListenerFollowsPluginLifecycle()
	{
		plugin.startUp();
		verify(keyManager).registerKeyListener(plugin);
		plugin.shutDown();
		verify(keyManager).unregisterKeyListener(plugin);
	}
}
