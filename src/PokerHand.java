import java.util.Arrays;
import java.util.Comparator;

public class PokerHand
{
    public enum Result { TIE, WIN, LOSS }
    public enum HandType {
        RoyalFlush, StraightFlush, FourOfAKind, FullHouse,
        Flush, Straight, ThreeOfAKind, TwoPair, OnePair,
        HighCard
    }

    String[] cards;

    PokerHand(String hand)
    {
        cards = hand.split(" ");
        sortCardArray();
    }

    private void sortCardArray() {
        Arrays.sort(cards, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int o1Value = getCardValue(o1);
                int o2Value = getCardValue(o2);

                if (o1Value > o2Value) {
                    return -1;
                } else if (o1Value < o2Value) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

    }

    private int getCardValue(String card) {
        char cardValChar = card.charAt(0);
        switch (cardValChar) {
            case 'A':
                return 14;
            case 'K':
                return 13;
            case 'Q':
                return 12;
            case 'J':
                return 11;
            case 'T':
                return 10;
            default:
                return Character.getNumericValue(cardValChar);
        }

    }

    private int[] getCardValues() {
        int[] cardValues = new int[cards.length];
        for (int i = 0; i < cards.length; i++) {
            cardValues[i] = getCardValue(cards[i]);
        }
        return cardValues;
    }

    private int equalValues(int from, int to) {
        int[] cardValues = getCardValues();
        for (int i = from + 1; i <= to; i++) {
            if (cardValues[i] != cardValues[i-1]) {
                return -1;
            }
        }
        return cardValues[from];
    }

    private int isFlush() {
        char suit = cards[0].charAt(1);
        boolean sameSuit = true;
        for (int i = 1; i < cards.length; i++) {
            if (cards[i].charAt(1) != suit) {
                sameSuit = false;
                break;
            }
        }
        if (sameSuit) {
            return isHighCard();
        }
        return -1;
    }

    private int isStraight() {
        boolean consecutive = true;
        for (int i = 1; i < cards.length; i++) {
            if ( Math.abs(getCardValue(cards[i]) - getCardValue(cards[i-1])) != 1) {
                consecutive = false;
                break;
            }
        }
        if (consecutive) {
            return isHighCard();
        }
        return -1;
    }

    private int isRoyalFlush() {
        if (cards[0].charAt(0) == 'A') {
            return isStraightFlush();
        }
        return -1;
    }

    private int isStraightFlush() {
        if (isFlush() != -1) {
            int value = isStraight();
            if (value != -1) {
                return value;
            }
        }
        return -1;
    }

    private int isFourOfAkind() {
        int value = equalValues(0, 3);
        if (value != -1) {
            return value * 10 + getCardValue(cards[4]);
        } else {
            value = equalValues(1, 4);
            if (value != -1) {
                return value * 10 + getCardValue(cards[0]);
            }
        }
        return -1;
    }

    private int isFullHouse() {
        int threeValue = equalValues(0, 2);
        if (threeValue != -1) {
            int pairValue = equalValues(3, 4);
            if (pairValue != -1) {
                return (threeValue * 100) + pairValue;
            }
        } else {
            threeValue = equalValues(2, 4);
            if (threeValue != -1) {
                int pairValue = equalValues(0, 1);
                if (pairValue != -1) {
                    return (threeValue * 100) + pairValue;
                }
            }
        }
        return -1;
    }

    private int isThreeOfAKind() {
        int[] cardValues = getCardValues();
        int threeValue = equalValues(0, 2);
        if (threeValue != -1) {
            return threeValue * 100 + cardValues[3] * 10 + cardValues[4];
        } else {
            threeValue = equalValues(1, 3);
            if (threeValue != -1) {
                return threeValue * 100 + cardValues[0] * 10 + cardValues[4];
            } else {
                threeValue = equalValues(2, 4);
                if (threeValue != -1) {
                    return threeValue * 100 + cardValues[0] * 10 + cardValues[1];
                }
            }
        }
        return -1;
    }

    private int isTwoPair() {
        int pairValue1 = equalValues(0, 1);
        if (pairValue1 == -1) {
            pairValue1 = equalValues(1, 2);
            if (pairValue1 != -1) {
                int pairValue2 = equalValues(3, 4);
                if (pairValue2 != -1) {
                    return pairValue1 * 100 + pairValue2 * 10 + getCardValue(cards[0]);
                }
            }
        } else {
            int pairValue2 = equalValues(2, 3);
            if (pairValue2 != -1) {
                return pairValue1 * 100 + pairValue2 * 10 + getCardValue(cards[4]);
            } else {
                pairValue2 = equalValues(3, 4);
                if (pairValue2 != -1) {
                    return pairValue1 * 100 + pairValue2 * 10 + getCardValue(cards[2]);
                }
            }
        }
        return -1;
    }

    private int isOnePair() {
        int[] cardValues = getCardValues();
        int pairValue = equalValues(0, 1);
        if (pairValue != -1) {
            return pairValue * 1000 + cardValues[2] * 100 + cardValues[3] * 10 + cardValues[4];
        } else {
            pairValue = equalValues(1, 2);
            if (pairValue != -1) {
                return pairValue * 1000 + cardValues[0] * 100 + cardValues[3] * 10 + cardValues[4];
            } else {
                pairValue = equalValues(2, 3);
                if (pairValue != -1) {
                    return pairValue * 1000 + cardValues[0] * 100 + cardValues[1] * 10 + cardValues[4];
                } else {
                    pairValue = equalValues(3, 4);
                    if (pairValue != -1) {
                        return pairValue * 1000 + cardValues[0] * 100 + cardValues[1] * 10 + cardValues[2];
                    }
                }
            }
        }
        return -1;
    }

    private int isHighCard() {
        int[] cardValues = getCardValues();
        int multiplier = 10000;
        int value = 0;
        for (int cardValue : cardValues) {
            value += cardValue * multiplier;
            multiplier /= 10;
        }
        return value;
    }

    private HandType getHandType () {
        if (isRoyalFlush() != -1) {
            return HandType.RoyalFlush;
        } else if (isStraightFlush() != -1) {
            return HandType.StraightFlush;
        } else if (isFourOfAkind() != -1) {
            return HandType.FourOfAKind;
        } else if (isFullHouse() != -1) {
            return HandType.FullHouse;
        } else if (isFlush() != -1) {
            return HandType.Flush;
        } else if (isStraight() != -1) {
            return HandType.Straight;
        } else if (isThreeOfAKind() != -1) {
            return HandType.ThreeOfAKind;
        } else if (isTwoPair() != -1) {
            return HandType.TwoPair;
        } else if (isOnePair() != -1) {
            return HandType.OnePair;
        } else {
            return HandType.HighCard;
        }
    }

    private int getHandTypeLevel() {
        switch (getHandType()) {
            case RoyalFlush:
                return 10;
            case StraightFlush:
                return 9;
            case FourOfAKind:
                return 8;
            case FullHouse:
                return 7;
            case Flush:
                return 6;
            case Straight:
                return 5;
            case ThreeOfAKind:
                return 4;
            case TwoPair:
                return 3;
            case OnePair:
                return 2;
            case HighCard:
            default:
                return 1;
        }
    }

    private int getHandValue() {
        switch (getHandType()) {
            case RoyalFlush:
                return isRoyalFlush();
            case StraightFlush:
                return isStraightFlush();
            case FourOfAKind:
                return isFourOfAkind();
            case FullHouse:
                return isFullHouse();
            case Flush:
                return isFlush();
            case Straight:
                return isStraight();
            case ThreeOfAKind:
                return isThreeOfAKind();
            case TwoPair:
                return isTwoPair();
            case OnePair:
                return isOnePair();
            case HighCard:
            default:
                return isHighCard();
        }
    }

    public Result compareWith(PokerHand hand) {
        if (getHandTypeLevel() > hand.getHandTypeLevel()) {
            return Result.WIN;
        } else if (getHandTypeLevel() < hand.getHandTypeLevel()) {
            return Result.LOSS;
        } else {
            if (getHandValue() > hand.getHandValue()) {
                return Result.WIN;
            } else if (getHandValue() < hand.getHandValue()) {
                return Result.LOSS;
            } else {
                return Result.TIE;
            }
        }
    }
}
