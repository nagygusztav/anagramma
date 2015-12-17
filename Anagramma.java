package anagramma;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Anagramma {

    // 3, 4, 5 feladathoz:
    private static String szóRendező(String forrás) {
        char[] abcKarakterek = forrás.toCharArray();
        Arrays.sort(abcKarakterek);
        return String.copyValueOf(abcKarakterek);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        final String FORRÁSÁLLOMÁNY2 = "szotar.txt";
        final String CÉLÁLLOMÁNY3 = "abc.txt";
        final String CÉLÁLLOMÁNY7 = "rendezve.txt";
        final int SZÓHOSSZMIN = 2;
        final int SZÓHOSSZMAX = 30;
        
        Scanner billentyűOlvasó = new Scanner(System.in);

        /*
         Kérjen be a felhasználótól egy szöveget, majd határozza meg, hogy hány 
         különböző karakter található a szövegben! A darabszámot és a 
         karaktereket írja ki a képernyőre!
         */
        System.out.println("1. feladat:");
        System.out.println("Kérek egy szót:");
        String szó1 = billentyűOlvasó.nextLine();

        // 1. értelmezés: minden betű egyszer
        Set<Character> betűHalmaz1 = new TreeSet<>();
        for (int i = 0; i < szó1.length(); i++) {
            betűHalmaz1.add(szó1.charAt(i));
        }
        System.out.println(betűHalmaz1.size() + " féle karakter: ");
        for (Character betű : betűHalmaz1) {
            System.out.print(betű);
        }
        System.out.println();

        // 2. értelmezés: betűk száma külön-külön
        Map<Character, Integer> betűk1 = new TreeMap<>();
        for (int i = 0; i < szó1.length(); i++) {
            Integer db = betűk1.get(szó1.charAt(i));
            betűk1.put(szó1.charAt(i), (db == null) ? 1 : db + 1);
        }
        for (Character betű : betűk1.keySet()) {
            System.out.print(betű + ":" + betűk1.get(betű) + ' ');
        }
        System.out.println();

        /*
         Olvassa be a szotar.txt állományból a szavakat, és a következő 
         feladatok megoldása során ezekkel dolgozzon!
         */
        System.out.println("\n2. feladat:");
        BufferedReader szótárOlvasó = new BufferedReader(
                new FileReader(FORRÁSÁLLOMÁNY2));
        List<String> szavak = new ArrayList<>();
        String szó2;
        while ((szó2 = szótárOlvasó.readLine()) != null) {
            szavak.add(szó2);
        }
        szótárOlvasó.close();
        //System.out.println("Szavak: " + szavak);

        /*
         Az állományból beolvasott szavakat alakítsa át úgy, hogy minden szó 
         karaktereit egyenként tegye ábécérendbe! Az így létrehozott szavakat 
         írja ki az abc.txt állományba az eredeti állománnyal egyező sorrendben!
         */
        System.out.println("\n3. feladat:");
        PrintWriter abcÍró = new PrintWriter(CÉLÁLLOMÁNY3);
        Map<String, String> rendezettPárok3 = new TreeMap<>(); // 5,6,7. feladat
        for (String szó : szavak) {
            String rendezett = szóRendező(szó);
            abcÍró.println(szó + '\t' + rendezett);
            rendezettPárok3.put(szó, rendezett); // 5,6,7. feladat
        }
        abcÍró.close();

        /*
         Kérjen be a felhasználótól két szót, és döntse el, hogy a két szó 
         anagramma-e! Ha azok voltak, írja ki a képernyőre az „Anagramma” szót, 
         ha nem, akkor pedig a „Nem anagramma” szöveget!
         */
        System.out.println("\n4. feladat:");
        System.out.println("Kérem az első szót:");
        String szó41 = szóRendező(billentyűOlvasó.nextLine());
        System.out.println("Kérem a második szót:");
        String szó42 = szóRendező(billentyűOlvasó.nextLine());
        if (szó41.equals(szó42)) {
            System.out.println("Anagramma");
        } else {
            System.out.println("Nem anagramma");
        }

        /*
         Kérjen be a felhasználótól egy szót! A szotar.txt állomány szavaiból 
         keresse meg a szó anagrammáit (a szót önmagát is annak tekintve)! 
         Ha van találat, azokat egymás alá írja ki a képernyőre, ha nem volt 
         találat, akkor írja ki a „Nincs a szótárban anagramma” szöveget!
         */
        System.out.println("\n5. feladat:");
        System.out.println("Kérek egy szót:");
        String szó5Rendezett = szóRendező(billentyűOlvasó.nextLine());

        // 1. verzió: nyers keresés
        int anadrammaDb5 = 0;
        for (String szó : szavak) {
            if (szóRendező(szó).equals(szó5Rendezett)) {
                System.out.println(szó);
                anadrammaDb5++;
            }
        }
        if (anadrammaDb5 == 0) {
            System.out.println("Nincs a szótárban anagramma");
        }

        // 2. verzió: a 3. feladatnál már előállítottuk: tároljuk el!?
        for (String szó : rendezettPárok3.keySet()) {
            if (rendezettPárok3.get(szó).equals(szó5Rendezett)) {
                System.out.println(szó);
            }
        }

        /*
         Határozza meg, hogy a szotar.txt állományban melyik a leghosszabb szó! 
         Ha több, ugyanannyi karakterből álló leghosszabb szó volt, akkor az 
         ugyanazokat a karaktereket tartalmazó szavakat (amelyek egymás 
         anagrammái) közvetlenül egymás alá írja ki! A feltételnek megfelelő 
         összes szó pontosan egyszer szerepeljen a kiírásban!        
         */
        /*
         Rendezze a szotar.txt állományban lévő szavakat a karakterek száma 
         szerint növekvő sorrendbe! Az egyforma hosszúságú és ugyanazokat a 
         karaktereket tartalmazó szavak (amelyek egymás anagrammái) szóközzel 
         elválasztva ugyanabba a sorba kerüljenek!
         Az egyforma hosszúságú, de nem ugyanazokat a karaktereket tartalmazó 
         szavak külön sorba kerüljenek! A különböző hosszúságú szavakat egy üres 
         sorral különítse el egymástól!
         Az így rendezett szavakat írja ki a rendezve.txt állományba!
         */
        Map<String, List<String>>[] anagrammák = new TreeMap[SZÓHOSSZMAX + 1];
        for (int i = SZÓHOSSZMIN; i <= SZÓHOSSZMAX; i++) {
            anagrammák[i] = new TreeMap<>();
        }
        for (String szó : rendezettPárok3.keySet()) {
            String rendezett = rendezettPárok3.get(szó);
            List<String> anagrammái = anagrammák[szó.length()].get(rendezett);
            if (anagrammái == null) {
                anagrammái = new ArrayList<String>();
                anagrammák[szó.length()].put(rendezett, anagrammái);
            }
            anagrammái.add(szó);
        }

        System.out.println("\n6. feladat:");
        int utolsó = SZÓHOSSZMAX;
        while (anagrammák[utolsó].isEmpty()) {
            utolsó--;
        }
        System.out.println("A legnagyobb betűszám: " + utolsó);
        for (String rendezett : anagrammák[utolsó].keySet()) {
            for (String szó : anagrammák[utolsó].get(rendezett)) {
                System.out.println(szó);
            }
        }

        System.out.println("\n7. feladat:");
        PrintWriter rendezveÍró = new PrintWriter(CÉLÁLLOMÁNY7);
        for (int i = SZÓHOSSZMIN; i <= SZÓHOSSZMAX; i++) {
            if (!anagrammák[i].isEmpty()) {
                for (String rendezett : anagrammák[i].keySet()) {
                    rendezveÍró.print(rendezett + ": ");
                    
                    for (String szó : anagrammák[i].get(rendezett)) {
                        rendezveÍró.print(szó + ' ');
                    }
                    rendezveÍró.println();
                }
                if (i < utolsó) {
                    rendezveÍró.println();
                }
            }
        }
        rendezveÍró.close();
    }
}
