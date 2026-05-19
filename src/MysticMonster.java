import java.util.Scanner;

public class MysticMonster extends Monster {

    private String image = "Мк";

    private static final String[][] WORDS = {
        {"CAT", "DOG", "SUN", "KEY", "MAP"},
        {"HERO", "FIRE", "WOLF", "GATE", "DARK"},
        {"BRAVE", "MAGIC", "SWORD", "QUEST", "TOWER"},
        {"CASTLE", "DRAGON", "KNIGHT", "SHIELD", "BATTLE"},
        {"DUNGEON", "MONSTER", "VICTORY", "WARRIOR", "KINGDOM"}
    };

    MysticMonster(int sizeBoard) {
        super(sizeBoard);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        int level = Math.min(difficultGame, 5) - 1;
        String word = WORDS[level][r.nextInt(WORDS[level].length)];
        int shift = r.nextInt(1, 26);
        String encrypted = encrypt(word, shift);
        int attempts = difficultGame <= 3 ? 3 : difficultGame == 4 ? 2 : 1;

        System.out.println("Мистический монстр испытывает твою смекалку!");
        System.out.println("Шифр Цезаря. Каждая буква сдвинута вперёд по алфавиту на одно и то же число позиций.");
        System.out.println("Зашифрованное слово: " + encrypted);

        if (difficultGame <= 2) {
            System.out.println("Подсказка: сдвиг равен " + shift);
        } else if (difficultGame == 3) {
            System.out.println("Подсказка: сдвиг " + (shift < 13 ? "меньше 13" : "13 или больше"));
        } else {
            System.out.println("Подсказок нет. Удача тебе не поможет!");
        }

        System.out.println("У тебя " + attempts + (attempts == 1 ? " попытка" : " попытки") + "!");

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < attempts; i++) {
            System.out.print("Введи расшифрованное слово: ");
            String answer = sc.next().trim().toUpperCase();
            if (answer.equals(word)) {
                System.out.println("Верно! Ты победил монстра!");
                return true;
            }
            int remaining = attempts - i - 1;
            if (remaining > 0) {
                System.out.println("Неверно! Осталось попыток: " + remaining);
                System.out.println("Совпадающих букв на своих местах: " + countMatchingLetters(answer, word));
            } else {
                System.out.println("Неверно! Попытки исчерпаны!");
            }
        }

        System.out.println("Правильный ответ был: " + word);
        System.out.println("Ты проиграл эту битву!");
        return false;
    }

    private String encrypt(String word, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            sb.append((char) ('A' + (c - 'A' + shift) % 26));
        }
        return sb.toString();
    }

    private int countMatchingLetters(String answer, String word) {
        int count = 0;
        int len = Math.min(answer.length(), word.length());
        for (int i = 0; i < len; i++) {
            if (answer.charAt(i) == word.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
