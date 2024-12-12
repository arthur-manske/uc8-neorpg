package com.manske.neorpg.entity;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.item.Item;

import java.util.List;
import java.util.ArrayList;

public class Inventory extends Entity
{
	protected String name;

	protected int cols;
	protected int rows;

	protected List<List<Item>> contents;

	public Inventory(String name, int cols, int rows)
	{
		super(name, 1);

		this.cols = cols;
		this.rows = rows;

		this.clear();
	}

	public void clear()
	{
		this.contents = new ArrayList<>();
		for (int i = 0; i < this.rows; i++) {
			List<Item> row = new ArrayList<>();
			for (int j = 0; j < this.cols; j++) row.add(null);
			this.contents.add(row);
		}
	}

	public boolean tryAddItem(int row, int col, Item item)
	{
		if (row >= 0 && row < this.rows && col >= 0 && col < this.cols && this.contents.get(row).get(col) == null) {
			this.contents.get(row).set(col, item);
			return true;
		}

		return false;
	}

	public boolean tryDelItem(int row, int col)
	{
		if (row >= 0 && row < this.rows && col >= 0 && col < this.cols && this.contents.get(row).get(col) != null) {
			this.contents.get(row).set(col, null);
			return true;
		}

		return false;
	}

	public Item tryGetItem(int row, int col)
	{
		if (row >= 0 && row < this.rows && col >= 0 && col < this.cols) return this.contents.get(row).get(col);
		return null;
	}

	public String getName() { return this.name; }

	public void print()
	{
		/* TODO: TABELA COM TODAS AS INFORMAÇÕES DO INVENTÁRIO */
	}

	public void access()
	{
		/* TODO: OPÇÃO DE ACESSAR SEU PRÓPRIO INVENTÁRIO, MODIFICANDO E ALTERANDO ELE A VONTADE */
	}
	
	public void loot()
	{
		/* TODO: OPÇÃO DE LOOTEAR INVENTÁRIOS DE BAÚS OU INIMIGOS DERROTADOS, PODENDO TRANSFERIR OS ITENS AO SEU PRÓPRIO INVENTÁRIO */
	}
};
