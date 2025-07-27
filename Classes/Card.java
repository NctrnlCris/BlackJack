package Classes;

import java.util.List;

public class Card {
    Card_Enum name;
    int value;
    boolean is_ace;

    public Card (Card_Enum name, int value) {
        this.name = name;
        this.value = value;
        if (name == Card_Enum.ACE) {
            this.is_ace = true;
        }
    }

}