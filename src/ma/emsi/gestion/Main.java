
package ma.emsi.gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        System.out.println("Gestion des étudiants — EMSI");

        // 1. Création de la liste des étudiants
        List<Etudiant> etudiants = new ArrayList<>();

        etudiants.add(new Etudiant(1, "Amina", 15));
        etudiants.add(new Etudiant(2, "Youssef", 9));
        etudiants.add(new Etudiant(3, "Sara", 12.5));

        // 2. Affichage et calcul de la moyenne
        System.out.println("\nListe des étudiants :");

        double somme = 0;

        for (Etudiant e : etudiants) {
            System.out.println(
                    e.getId() + " - " + e.getNom()
                            + " : " + e.getNote() + "/20"
            );

            somme += e.getNote();
        }

        if (!etudiants.isEmpty()) {
            double moyenne = somme / etudiants.size();
            System.out.println("\nMoyenne : " + moyenne);
        } else {
            System.out.println("Aucun étudiant dans la liste.");
        }

        // 3. Afficher les étudiants admis
        System.out.println("\nÉtudiants admis :");

        for (Etudiant e : etudiants) {
            if (e.estAdmis()) {
                System.out.println(e.getNom());
            }
        }

        // 4. Héritage et polymorphisme
        Personne personne = etudiants.get(0);
        System.out.println("\nRôle : " + personne.role());

        // 5. Tester l'interface
        Evaluatable evaluation = etudiants.get(0);
        System.out.println("Admis ? " + evaluation.estAdmis());

        // 6. Tester une note invalide
        System.out.println("\nTest d'une note invalide :");

        try {
            Etudiant test = new Etudiant(4, "Test", -2);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        // 7. Recherche par nom
        System.out.println("\nRecherche d'un étudiant :");

        Etudiant resultat =
                rechercherParNom(etudiants, " amina ");

        if (resultat != null) {
            System.out.println(
                    "Étudiant trouvé : " + resultat.getNom()
            );
        } else {
            System.out.println("Étudiant introuvable.");
        }

        // 8. Tester HashSet
        System.out.println("\nTest HashSet :");

        Set<Etudiant> ensemble = new HashSet<>();

        ensemble.add(new Etudiant(1, "Amina", 15));
        ensemble.add(new Etudiant(1, "Amina", 15));

        System.out.println(
                "Taille du HashSet : " + ensemble.size()
        );

        // 9. Tester HashMap
        System.out.println("\nTest HashMap :");

        Map<Integer, Etudiant> dictionnaire = new HashMap<>();

        for (Etudiant e : etudiants) {
            dictionnaire.put(e.getId(), e);
        }

        Etudiant etudiantTrouve = dictionnaire.get(2);

        if (etudiantTrouve != null) {
            System.out.println(
                    "Étudiant d'id 2 : " + etudiantTrouve.getNom()
            );
        } else {
            System.out.println("Étudiant introuvable.");
        }

        // 10. Tri par identifiant
        System.out.println("\nTri par identifiant :");

        List<Etudiant> triParId = new ArrayList<>(etudiants);
        Collections.sort(triParId);

        for (Etudiant e : triParId) {
            System.out.println(
                    e.getNom() + " - " + e.getId()
            );
        }

        // 11. Tri par note croissante
        System.out.println("\nTri par note croissante :");

        List<Etudiant> triParNote = new ArrayList<>(etudiants);

        Collections.sort(triParNote, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                return Double.compare(
                        e1.getNote(), e2.getNote()
                );
            }
        });

        for (Etudiant e : triParNote) {
            System.out.println(
                    e.getNom() + " - " + e.getNote()
            );
        }
    }

    // Méthode de recherche par nom
    public static Etudiant rechercherParNom(
            List<Etudiant> etudiants, String nomRecherche) {

        for (Etudiant e : etudiants) {
            if (e.getNom().equalsIgnoreCase(
                    nomRecherche.trim())) {
                return e;
            }
        }

        return null;
    }
}
