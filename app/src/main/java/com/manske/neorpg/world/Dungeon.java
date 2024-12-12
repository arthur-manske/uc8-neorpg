package com.manske.neorpg.world;

public class Dungeon extends Scene
{
	protected Scene inside;

	public Dungeon(String name, String info, Scene inside)
	{
		super(name, info);
		this.inside = inside;
	}

	public Scene getInside() { return this.inside; }
	public void setInside(Scene inside) { this.inside = inside; }
};
