# Client Size Hotkeys

Resizes the client to a set size with a hotkey. Each of the four slots has a hotkey and a size, set in the plugin's config.

| Slot | Default hotkey | Default size |
|------|----------------|--------------|
| 1    | Ctrl+1         | 1024x768     |
| 2    | Ctrl+2         | 800x600      |
| 3    | Not set        | 1280x720     |
| 4    | Not set        | 1920x1080    |

The Resize mode setting chooses what the size applies to:

| Mode                 | What the hotkey sets                                                                          |
|----------------------|-----------------------------------------------------------------------------------------------|
| Game size (default)  | The game itself, through RuneLite's Game size setting. The window grows to fit the sidebar and title bar. |
| Window size          | The whole window, including the sidebar and title bar. The game takes up the space left over. |

In Window size mode the hotkey does nothing while the client is maximized, and RuneLite stops the window from going below the minimum size it needs.

Use a hotkey with a modifier such as Ctrl, because a plain key also fires while typing in chat or entering a bank PIN.

The plugin adds nothing to the window, so dragging the client by its custom title bar keeps working on Linux.
