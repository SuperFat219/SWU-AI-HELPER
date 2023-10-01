public class CountLettersInArray {
    public static void main(String[] args) {
        char[] array = new char[100];
        int[] letters = new int[26];
        for (int i = 0; i < 100; i++) {
            array[i] = getRandomCharacter('a', 'z');
            letters[array[i] - 'a'] += 1;
        }
        for (int i = 0; i < 26; i++) {
            System.out.printf("%c %d\n", 'a' + i, letters[i]);
        }
    }

    public static char getRandomCharacter(char ch1, char ch2) {
        return (char) (ch1 + Math.random() * (ch2 - ch1 + 1));
    }
}
