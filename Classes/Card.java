package Classes;

import java.util.List;

public class Card {
    private Card_Enum name;
    private int value;
    private boolean isAce;

    public Card (Card_Enum name, int value) {
        this.name = name;
        this.value = value;
        if (name == Card_Enum.ACE) {
            this.isAce = true;
        }
    }

    public int getValue() {
        return value;
    }

    public void setAceValue() {
        value = 1;
        isAce = false;
    }

    public boolean isAce() {
        return isAce;
    }


}