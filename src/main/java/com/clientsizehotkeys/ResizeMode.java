package com.clientsizehotkeys;

public enum ResizeMode
{
	GAME_SIZE("Game size"),
	WINDOW_SIZE("Window size");

	private final String name;

	ResizeMode(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
