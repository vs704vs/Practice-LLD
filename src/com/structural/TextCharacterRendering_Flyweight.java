/*
 * Text Editor Character Rendering
Asked in: Microsoft, Adobe

Requirement: Millions of characters rendered, but:

Font, size, style shared
Position differs

Implement:
Flyweight factory caching intrinsic state

Acceptance:
Memory usage minimized

Follow-ups:
Intrinsic vs extrinsic state
Cache eviction
Flyweight vs Singleton
 */

package com.structural;
import java.util.HashMap;

class CharacterType {
	String font;
	int size;
	
	public CharacterType(String font, int size) {
		this.font = font;
		this.size = size;
	}
	
	public void renderCharacter(int x, int y) {
		System.out.println("font - " + this.font + " | size - " + this.size + " | positionX - " + x + " | positionY - " + y);
	}
}


class CharacterTypeFactory {
	HashMap<String, CharacterType> map;
	public CharacterTypeFactory() {
		map = new HashMap<String, CharacterType>();
	}
	
	public CharacterType getCharacterType(String font, int size) {
		String key = font + "_" + size;
		if(!map.containsKey(key)) {
			map.put(key, new CharacterType(font, size));
		}
		return map.get(key);
	}
}



class RenderCharacter {
	int x, y;
	CharacterType characterType;
	
	public RenderCharacter(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void updatePosition(int x, int y) {
		this.x += x;
		this.y += y;
	}
}

public class TextCharacterRendering_Flyweight {
    public static void main(String[] args) {
        CharacterTypeFactory factory = new CharacterTypeFactory();

        // Create characters with shared font/size but different positions
        RenderCharacter c1 = new RenderCharacter(10, 20);
        c1.characterType = factory.getCharacterType("Arial", 12);
        c1.characterType.renderCharacter(c1.x, c1.y);

        RenderCharacter c2 = new RenderCharacter(30, 40);
        c2.characterType = factory.getCharacterType("Arial", 12); // same font/size, reused flyweight
        c2.characterType.renderCharacter(c2.x, c2.y);

        RenderCharacter c3 = new RenderCharacter(50, 60);
        c3.characterType = factory.getCharacterType("TimesNewRoman", 14);
        c3.characterType.renderCharacter(c3.x, c3.y);

        // Demonstrate update of extrinsic state
        c1.updatePosition(5, 5);
        c1.characterType.renderCharacter(c1.x, c1.y);

        // Show caching effect
        System.out.println("\nCache size: " + factory.map.size());
    }
}
