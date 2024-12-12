package com.manske.neorpg.entity.character.nonplayable.obelisk;

import com.manske.neorpg.entity.character.Character;

public class IdeaOfEvil extends Character
{
	public IdeaOfEvil()
	{
		super(
			"Idéia do mal",
			(long)((Math.random() * 6000) + 666) * 666,
			(long)((Math.random() * 6000) + 666) * 666,
			(long)((Math.random() * 6000) + 666) * 666,
			(long)((Math.random() * 6000) + 666) * 666,
			(long)((Math.random() * 6000) + 666) * 666,
			(long)((Math.random() * 6000) + 666) * 666,
			(long)((Math.random() * 6000) + 666) * 666,
			6
		);
	}
	
	/* TODO: Quando a idéia do mal morre, ela gera a vontade do mal suprema */
	/* TODO: Habilidades Especiais da Idéia do Mal */
};
