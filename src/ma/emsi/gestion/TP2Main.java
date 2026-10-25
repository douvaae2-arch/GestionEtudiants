
package ma.emsi.gestion;

import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TP2Main {

    // Étape 1 : fonction pure
    public static boolean noteAdmise(double note) {
        return note >= 10;
    }

    // Étape 2 : vérifier un étudiant avec une règle
    public static boolean verifier(Etudiant e, RegleAdmission regle) {
        return regle.valider(e);
    }

    public static void main(String[] args) {

        // Étape 1 : observer une note mutable
        System.out.println("===== ETAPE 1 =====");

        Etudiant youssef = new Etudiant(2, "Youssef", 9);
        System.out.println("Note initiale : " + youssef.getNote());

        youssef.setNote(11);
        System.out.println("Note modifiée : " + youssef.getNote());

        System.out.println("noteAdmise(9) : " + noteAdmise(9));

        // Une référence final ne rend pas l'objet immutable :
        // son contenu peut toujours être modifié avec setNote().

        // Étape 2 : interface fonctionnelle et lambda
        System.out.println("\n===== ETAPE 2 =====");

        Etudiant amina = new Etudiant(1, "Amina", 15);
        Etudiant youssef2 = new Etudiant(2, "Youssef", 9);

        RegleAdmission regleAnonyme = new RegleAdmission() {
            @Override
            public boolean valider(Etudiant e) {
                return e.getNote() >= 10;
            }
        };

        RegleAdmission regleLambda = e -> e.getNote() >= 10;

        System.out.println("Amina (15) : " + verifier(amina, regleAnonyme));
        System.out.println("Amina au seuil 12 : "
                + (amina.getNote() >= 12));
        System.out.println("Youssef (9) : " + verifier(youssef2, regleLambda));

        // Étape 3 : les interfaces standards
        System.out.println("\n===== ETAPE 3 =====");

        Predicate<Etudiant> admis = e -> e.getNote() >= 10;
        Function<Etudiant, String> resultat =
                e -> admis.test(e) ? "Admis" : "Ajourné";
        Consumer<Etudiant> afficher =
                e -> System.out.println(e.getNom() + " : " + e.getNote());
        Supplier<Etudiant> creerOmar =
                () -> new Etudiant(4, "Omar", 14);

        System.out.println("Amina : " + admis.test(amina)
                + " / " + resultat.apply(amina));
        System.out.println("Youssef : " + resultat.apply(youssef2));

        afficher.accept(amina);

        Etudiant omar = creerOmar.get();
        System.out.println("Omar : " + omar.getNote());

        // Étape 4 : références de méthodes et Record
        System.out.println("\n===== ETAPE 4 =====");

        Consumer<String> afficherTexte = System.out::println;
        afficherTexte.accept("Utilisation de System.out::println");

        Function<Etudiant, String> obtenirNom = Etudiant::getNom;
        System.out.println(obtenirNom.apply(amina));

        ResultatEtudiant fiche =
                new ResultatEtudiant(1, "Amina", 15, true);

        System.out.println(fiche);
        System.out.println("Nom du Record : " + fiche.nom());

        // Étape 5 : intégrer les nouveautés sans Streams
        System.out.println("\n===== ETAPE 5 =====");

        Etudiant[] etudiants = {
                new Etudiant(1, "Amina", 15),
                new Etudiant(2, "Youssef", 9),
                new Etudiant(3, "Sara", 12.5)
        };

        RegleAdmission regle = e -> e.getNote() >= 10;
        Predicate<Etudiant> predicate = e -> regle.valider(e);

        Function<Etudiant, ResultatEtudiant> convertir =
                e -> new ResultatEtudiant(
                        e.getId(),
                        e.getNom(),
                        e.getNote(),
                        predicate.test(e)
                );

        Consumer<ResultatEtudiant> afficherResultat =
                r -> System.out.println(r);

        for (Etudiant e : etudiants) {
            if (predicate.test(e)) {
                afficherResultat.accept(convertir.apply(e));
            }
        }
    }
}
