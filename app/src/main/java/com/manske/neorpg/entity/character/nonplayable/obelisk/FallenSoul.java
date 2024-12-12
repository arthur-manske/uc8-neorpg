package com.manske.neorpg.entity.character.nonplayable.obelisk;

import com.manske.neorpg.entity.character.Character;

public class FallenSoul extends Character
{
	public FallenSoul()
	{
		super(
			"O Decaído",
			(long) (((Math.random() * 3) + 9) * 96),
			(long) (((Math.random() * 3) + 9) * 96),
			(long) (((Math.random() * 3) + 9) * 96),
			(long) (((Math.random() * 3) + 9) * 96),
			(long) (((Math.random() * 3) + 9) * 96),
			(long) (((Math.random() * 3) + 9) * 96),
			(long) (((Math.random() * 3) + 9) * 96),
			3	
		);
	}

	/* TODO: Quando Bluezão morre, ele gera a Vontade do Mal Decaída. Sua vontade do mal poderá ser carregada pelo jogador. Será um item utilizável.
	*/
	/* TODO: Habilidades do Bluezão: devem incluir:
		1. Autocura;
		2. Roubo de vida;
		3. Toque mortal (se não estiver usando armadura vai de hit kill)
		4. Decaímento (vai roubando sua vida aos poucos ao longo de 3-6 turnos)
	
	Fraquezas:
		1. Sal;
		2. Fogo;
		3. Beber veneno (caso o jogador beba veneno e ele use decaímento, ele roubará o dano do jogador por 3-6 turnos)
		4. Beber poção mortal (caso ele use decaímento, ele perderá a maioria da vida)
	*/
};
