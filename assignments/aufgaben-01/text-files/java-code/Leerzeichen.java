public class Leerzeichen {

    /**
     * Diese Methode soll alle Leerzeichen zählen, die jeweils NUR am ANFANG
     * oder NUR am ENDE vom String 'text' vorkommen. Dann soll ein String
     * zurückgegeben werden, der diese beiden Informationen enthält.
     *
     * Beispiele:
     * Parameter 'text'       => Rückgabewert
     * -----------------------------------------------
     * "Ohne Leerzeichen"     => "Anfang: 0 - Ende: 0"
     * " Am Anfang"           => "Anfang: 1 - Ende: 0"
     * "Am Ende "             => "Anfang: 0 - Ende: 1"
     * "   Anfang und Ende  " => "Anfang: 3 - Ende: 2"
     */
    public static String zaehleLeerzeichen(String text) {
        int anfang = 0;

        while (anfang < text.length() && text.charAt(anfang) == ' ') {
            anfang++;
        }
        int ende = 0;

        while (ende < text.length() && text.charAt(text.length() - ende - 1) == ' ') {
            ende++;
        }
        return "Anfang: " + anfang + " - Ende: " + ende;
    }
}
