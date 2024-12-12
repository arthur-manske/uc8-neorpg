package com.manske.neorpg.entity.item;

import com.manske.neorpg.entity.Entity;

public class Item extends Entity
{
	public Item(String name, int count)
	{
		super(name, count);
	}

	/* Tipo de item padrão */
	public int getId() { return 0; }
};
