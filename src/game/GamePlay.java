package game;
import java.util.ArrayList;
import java.util.Scanner;

public class GamePlay {
    public static boolean flag = false;
    public static boolean Game = true;
    int BankUser; int BankComp;
    Deck deck;
    Scanner sc;

    public GamePlay() {
        deck = new Deck();
        deck.ShuffleDeck();
        sc = new Scanner(System.in);
        BankComp = 500; BankUser = 500;
    }

    public  void Start() throws Exception {
        char key; char key2;
        int counterPlayer = 0; int counterComp = 0;
        int PointPlayer = 0, PointComp = 0;
        ArrayList<Card> tmpPlayer = new ArrayList<Card>();
        ArrayList<Card> tmpComp = new ArrayList<Card>();
        Information();
        deck.ShuffleDeck();
        WhoFirst();
        do
        {
//            WhoFirst();
            do
            {
                //-----------------------------------------------------------------------//

                if (flag == true && PointPlayer == 0)
                {
                    do
                    {
                        System.out.println("Твои карты: ");
                        tmpPlayer.add(deck.Extract());
                        for (int i = 0; i < tmpPlayer.size(); i++)
                        {
                            ShowCard(tmpPlayer.get(i), true);
                        }
                        PointPlayer += tmpPlayer.get(counterPlayer).getPoint();
                        if (PointPlayer > 21)
                        {
                            System.out.println("Ты проиграл партию (перебор) !!!");
                            BankUser -= 100;
                            BankComp += 100;
                            Game = false;
                            break;
                        }
                        else if (PointPlayer == 21)
                        {
                            System.out.println("Ты выиграл партию !!!");
                            BankComp -= 100;
                            BankUser += 100;
                            Game = false;
                            break;
                        }
                        System.out.println("\nУ тебя: " + PointPlayer + " очка(ов);");
                        System.out.println("Еще карту?\n - нажми 'y + Enter' - да,\n - нажми 'n + Enter' - нет;\n");
                        key = sc.next().charAt(0); System.out.println(key);
                        if (key == 'y')
                            counterPlayer++;
                        ConsoleClear();

                    } while (key == 'y');
                    flag = false;
                }

                //--------------------------------------------------------------------------//

                else if (flag == false && PointComp == 0)
                {
                    System.out.println("Карты компьютера: ");
                    while (PointComp < 15)
                    {
                        tmpComp.add(deck.Extract());
                        if (PointComp < 21)
                            ShowCard(tmpComp.get(tmpComp.size()-1), false);
                        PointComp += tmpComp.get(tmpComp.size()-1).getPoint();
                        if (PointComp > 21)
                        {
                            ConsoleClear();
                            for (int i = 0; i < tmpComp.size(); i++)
                            {
                                ShowCard(tmpComp.get(i), true);
                            }
                            System.out.println("Я проиграл партию (перебор) !!!\n");
                            BankComp -= 100;
                            BankUser += 100;
                            Game = false;
                            break;
                        }
                        else if (PointComp == 21)
                        {
                            ConsoleClear();
                            for (int i = 0; i < tmpComp.size(); i++)
                            {
                                ShowCard(tmpComp.get(i), true);
                            }
                            System.out.println("Я выиграл партию!!!\n");
                            BankUser -= 100;
                            BankComp += 100;
                            Game = false;
                            break;
                        }
                        counterComp++;
                    }
                    flag = true;
                }
                else if (PointPlayer > 0 && PointComp > 0)
                    Game = false;
            } while (Game);
            //--------------------------------------------------------------------------------//

            if (PointPlayer < 21 && PointComp < 21)
            {
                System.out.println("Открываем карты");
                sc.nextLine();
                ConsoleClear();
                System.out.println("\nУ меня: " + PointComp + " очка(ов);");
                for (int i = 0; i < tmpComp.size(); i++)
                {
                    ShowCard(tmpComp.get(i), true);
                }

                System.out.println("\nТеперь твои карты");
                sc.nextLine();
                System.out.println("\nУ тебя: " + PointPlayer + " очка(ов);");
                for (int i = 0; i < tmpPlayer.size(); i++)
                {
                    ShowCard(tmpPlayer.get(i), true);
                }

                if (PointPlayer > 0 && PointComp > 0)
                {
                    if (PointPlayer > PointComp)
                    {
                        System.out.println("Ты выиграл партию!!!");
                        BankComp -= 100;
                        BankUser += 100;
                        flag = true;
                    }
                    else if (PointPlayer < PointComp)
                    {
                        System.out.println("Я выиграл партию !!!");
                        BankComp += 100;
                        BankUser -= 100;
                        flag = false;
                    }
                    else if (PointPlayer == PointComp)
                        System.out.println("Ничья !!!");
                }
            }

            System.out.println("Состояние БАНКА: Ты - " + BankUser + "; Компьютер - " + BankComp);
            if (BankComp < 0)
                System.out.println("Я ставлю свою машину, играем дальше");
            if (BankUser < 0)
                System.out.println("Играем на твою квартиру");
            System.out.println("Еще Играем? 'y' - да\t 'n' - нет");
            key2 = sc.next().charAt(0);; System.out.println(key2);
            if (key2 == 'y')
            {
                ConsoleClear();
                flag = false;
                Game = true;
                counterPlayer = 0; counterComp = 0;
                PointPlayer = 0; PointComp = 0;
                tmpPlayer.clear(); tmpComp.clear();
                deck.ClearDeck();
                deck.InitDeck(36);
                deck.ShuffleDeck();
            }
        } while (key2 == 'y');
    }

    public void ConsoleClear(){
        System.out.println("<<<<<<<<<<<<<<<<<----------------------------------------------------->>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println();
        //for (int i = 0; i < 50; ++i) System.out.println();
    }

    public void Information()
    {
        String str = "\t\t\t\t\t <<Игра \"21\">>\n\n\tТвоя задача обыграть компьютер. Выигрывает тот, кто собирает\nнаиболие выгодную комбинацию (максимальные очки, но не болие 21 очка).\nЕсли у Играка болие 21-го очка - это перебор, считается проиграшем. \nОчки: Туз - 11, К - 4, Д - 3, В - 2, остальные карты - согласно номинала.\nБанк: у тебя- 500 грн, и 500 грн у компьютера, ставка 100 грн.\n\n\t<<Удачи!!!>>";
        System.out.print(str);
        System.out.println("\tДля начала игры нажми любую кнопку Enter");
        sc.nextLine();
        ConsoleClear();
    }

    public void WhoFirst() throws InterruptedException {
        int tmpPointsPlayer = 0, tmpPointsComp = 0;
        Card tmpPlayer, tmpComp;
        System.out.println("\tДавай определим, кому первому дилер сдает карты:\n<<Таням по одной карте - у кого больше очков тому карты первому>>");

         do {
            System.out.println("Тяни карту (Enter): ");
            tmpPlayer = deck.Extract();
            sc.nextLine();
            ShowCard(tmpPlayer, true);
            tmpPointsPlayer = tmpPlayer.getPoint();
            System.out.println("\nУ тебя: " + tmpPointsPlayer + " очка(ов);");
            System.out.println("<<Теперь моя очередь>> \n");
            Thread.sleep(1500);
            tmpComp = deck.Extract();
            ShowCard(tmpComp, true);
            tmpPointsComp = tmpComp.getPoint();
             System.out.println("\nУ меня: " + tmpPointsComp + " очка(ов);");
            if (tmpPointsPlayer == tmpPointsComp)
            {
                System.out.println("У нас одинаковое количество очков, разыграем еще раз!\n");
                System.out.println("Нажми любую кнопку...");
                sc.nextLine();
                ConsoleClear();

            }

            if (tmpPointsPlayer > tmpPointsComp)
            {
                System.out.println("\nУ тебя больше очков!!! <<тебе карты первому>>");
                flag = true;
            }
            if (tmpPointsPlayer < tmpPointsComp)
                System.out.println("\nУ меня больше очков!!! <<мне карты первому>>");

        } while (tmpPointsPlayer == tmpPointsComp);
        System.out.println("\nДля продолжения игры нажми любую кнопку Enter");
        sc.nextLine();
        ConsoleClear();
        deck.ClearDeck();
        deck.InitDeck(36);
        deck.ShuffleDeck();
    }

    public void ShowCard(Card aCard, boolean jacket)
    {
        if (jacket)
        {
            String[] face = { "J ", "Q ", "K ", "6 ", "7 ", "8 ", "9 ", "10", "A " }; //J Q K 6 7 8 9 10 A
            String [] suit = {"пика ", "трефа", "бубна", "черва"};

            System.out.println(" ------- ");
            System.out.println("|" + face[aCard.getValue()] + "     |");
            System.out.println("|       |");
            System.out.println("| " + suit[aCard.getSuit()] + " |");
            System.out.println("|       |");
            System.out.println("|     " + face[aCard.getValue()] + "|");
            System.out.println(" ------- ");
        }
        else if (!jacket)
        {
            System.out.println(" ------- ");
            System.out.println("|       |");
            System.out.println("|       |");
            System.out.println("|       |");
            System.out.println("|       |");
            System.out.println("|       |");
            System.out.println(" ------- ");
        }
    }
}
