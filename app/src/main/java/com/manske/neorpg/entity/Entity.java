package com.manske.neorpg.entity;

import com.manske.neorpg.world.Scene;

public class Entity
{
	protected String name;
	protected int    count;
	protected Scene  scene;

	public Entity(String name) { this.name = name; }
	public Entity(String name, int count) { this(name); this.count = count; }

	public String getName()  { return this.name;  }
	public int    getCount() { return this.count; }
	public Scene  getScene() { return this.scene; }

	public void setName(String name)  { this.name  = name;  }
	public void setScene(Scene scene) { this.scene = scene; }
	public void setCount(int count)   { this.count = count; }

	public void spend(int count) { this.count -= count; }
};
