package game;

public class Card {
    int value; // Значение карты //0 1 2 3 4 5 6 7 8 X 4
    int suit; // Масть карты // 3x8 (чирва) 4x8 (бубна) 5x8 (крест) 6x8 (пика)
    int point; // Очки карты //2 3 4 6 7 8 9 10 11 X 4

    public Card() {this.value = 0; this.suit = 0; this.point = 0;}
    public Card(int value, int suit, int point) {this.value = value; this.suit = suit; this.point = point;}


    public int getValue() {return value;}
    public void setValue(int value) {this.value = value;}

    public int getSuit() {return suit;}
    public void setSuit(int suit) {this.suit = suit;}

    public int getPoint() {return point;}
    public void setPoint(int point) {this.point = point;}
}
