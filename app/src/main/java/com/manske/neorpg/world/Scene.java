package com.manske.neorpg.world;

import java.util.List;
import java.util.ArrayList;

import com.manske.neorpg.utils.*;
import com.manske.neorpg.entity.Entity;
import com.manske.neorpg.entity.item.Item;
import com.manske.neorpg.entity.character.Character;

public class Scene
{
	protected String name;
	protected String info;
	protected Scene up, down, left, right;

	/* A localização padrão é o Superfície */
	protected String where = """
	 .oooooo..o                                            .o88o.  o8o             o8o                
	d8P'    `Y8                                            888 `"  `"'             `"'                
	Y88bo.      oooo  oooo  oo.ooooo.   .ooooo.  oooo d8b o888oo  oooo   .ooooo.  oooo   .ooooo.      
	 `"Y8888o.  `888  `888   888' `88b d88' `88b `888""8P  888    `888  d88' `"Y8 `888  d88' `88b     
	     `"Y88b  888   888   888   888 888ooo888  888      888     888  888        888  888ooo888 o8o 
	oo     .d8P  888   888   888   888 888    .o  888      888     888  888   .o8  888  888    .o `"' 
	8""88888P'   `V88V"V8P'  888bod8P' `Y8bod8P' d888b    o888o   o888o `Y8bod8P' o888o `Y8bod8P' o8o 
				 888                                                                  `"' 
				o888o                                                                     
	""";
	
	protected List<Entity> entities;

	public Scene(String name, String info, Scene up, Scene down, Scene left, Scene right, List<Entity> entities)
	{
		this.name    = name;
		this.info    = info;
		this.up      = up;
		this.down    = down;
		this.left    = left;
		this.right   = right;
		this.entities = entities;
	}
	
	/* Construtores diferentes para simplificação */
	public Scene(String name, String info) { this(name, info, null, null, null, null, new ArrayList<Entity>()); }
	public Scene(String name, String info, List<Entity> entities) { this(name, info, null, null, null, null, entities); }
	public Scene(String name, String info, Scene up, Scene down, Scene left, Scene right) { this(name, info, up, down, left, right, new ArrayList<Entity>()); }

	public Scene getUp()    { return this.up;    }
	public Scene getDown()  { return this.down;  }
	public Scene getLeft()  { return this.left;  }
	public Scene getRight() { return this.right; }

	public String getName()  { return this.name;  }
	public String getInfo()  { return this.info;  }
	public String getWhere() { return this.where; }

	public List<Entity> getEntities() { return this.entities; }

	protected Character nearestCharacter(int idx)
	{
		int loopIdx   = idx;
		Entity entity = null;

		do {
			/* Se o loopIdx chegar no topo, então zere ele. */
			if (loopIdx < 0 || loopIdx >= this.entities.size()) loopIdx = 0;

			entity = this.entities.get(loopIdx);
			if (entity == null || !(entity instanceof Character)) {
				loopIdx++;
				continue;
			}

			if (entity.getCount() < 1 && entity.getCount() != -7) {
				this.entities.remove(loopIdx);
				loopIdx--;
				continue;
			}

			return (Character) entity;
		} while (loopIdx != idx); /* Quando o loopIdx atingir novamente o idx, isso significa que não existe nenhum personagem válido */
		
		return null;
	}

	public Character findRandomNPC(int spend)
	{
		Character character = this.nearestCharacter((int) (Math.random() * this.entities.size()));
		/* Se character for null, isso significa que não existe personagem válido na lista */
		if (character == null) return character;

		/* Gasta uma entidade da lista, para poder limitar o número de vezes de farming de NPCs por cenário
			(e obviamente pra poder criar NPCs e bosses únicos)
		*/
		character.spend(spend);
		return character;
	}

	public void setUp(Scene    up)    { this.up    = up;    }
	public void setDown(Scene  down)  { this.down  = down;  }
	public void setLeft(Scene  left)  { this.left  = left;  }
	public void setRight(Scene right) { this.right = right; }
	
	public void setName(String name) { this.name = name; }
	public void setInfo(String info) { this.info = info; }

	public void setWhere(String where) { this.where = where; }
	
	public void enter(Character who)
	{
		who.setScene(this);

		Terminal.clear();
		
		Terminal.println(this.where);

		Terminal.println(this.name);
		Terminal.println(this.info);
	}

	public void goUp(Character    who) { up.enter(who);     }
	public void goDown(Character  who) { down.enter(who);   }
	public void goLeft(Character  who) { left.enter(who);   }
	public void goRight(Character who) { right.enter(who);  }
};
