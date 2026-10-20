
package ma.emsi.gestion;

public class Etudiant extends Personne
        implements Evaluatable, Comparable<Etudiant> {

    private int id;
    private double note;

    public Etudiant(int id, String nom, double note) {
        super(nom);

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "L'identifiant doit être positif."
            );
        }

        this.id = id;
        setNote(note);
    }

    public int getId() {
        return id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        if (!Double.isFinite(note) || note < 0 || note > 20) {
            throw new IllegalArgumentException(
                    "La note doit être comprise entre 0 et 20."
            );
        }

        this.note = note;
    }

    @Override
    public boolean estAdmis() {
        return note >= 10;
    }

    @Override
    public String role() {
        return "Étudiant";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Etudiant autre = (Etudiant) obj;
        return id == autre.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public int compareTo(Etudiant autre) {
        return Integer.compare(this.id, autre.id);
    }
}
