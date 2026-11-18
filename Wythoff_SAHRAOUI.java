/**
 * SAE S1.01 Jeu de Wythoff (mode duo et mode solo contre l'ordinateur).
 * Un pion est place au hasard sur un plateau carre. A chaque tour, on le
 * deplace vers le bas, vers la gauche ou en diagonale.
 * Celui qui amene le pion en (0,0) gagne.
 * @author B.SAHRAOUI
 */
class Wythoff_SAHRAOUI {
//fonction 1
    void principal () {
        testMouvementValide();
        jouer();
    }

//PARTIE 1:

//fonction 2
    /**
     * Demande la taille du plateau et redemande tant qu'elle
     * n'est pas comprise entre 4 et 16.
     * @return la taille valide
     */
     
    int saisirTaille () {
        int taille = SimpleInput.getInt("Taille du plateau (entre 4 et 16) : ");
        while (taille < 4 || taille > 16) {
            System.out.println("Taille invalide, recommencez.");
            taille = SimpleInput.getInt("Taille du plateau (entre 4 et 16) : ");
        }
        return taille;
        i=0
    }
//fonction 3
    /**
     * Demande le mode de jeu et redemande tant qu'il est invalide.
     * @return "solo" ou "duo"
     */
    String saisirMode () {
        String mode = SimpleInput.getString("Mode du jeu, solo ou duo : ");
        while (!mode.equals("solo") && !mode.equals("duo")) {
            System.out.println("Mode invalide.");
            mode = SimpleInput.getString("Choisis soit solo soit duo : ");
        }
        return mode;
    }

//fonction 4
    /**
     * Demande le nom d'un joueur.
     * @param message question a afficher
     * @return le nom saisi
     */
    String saisirNom (String message) {
        return SimpleInput.getString(message);
    }
//fonction 5

    /**
     * Demande a l'humain s'il veut commencer (mode solo).
     * @return 1 si le joueur humain commence, 2 sinon
     */
    int saisirQuiCommence () {
        String rep = SimpleInput.getString("Veux-tu commencer ? (oui ou non) : ");
        while (!rep.equals("oui") && !rep.equals("non")) {
            System.out.println("Reponse invalide.");
            rep = SimpleInput.getString("Veux-tu commencer ? (oui ou non) : ");
        }
        int res = 2;
        if (rep.equals("oui")) {
            res = 1;
        }
        return res;
    }
//PARTIE 2:

//fonction 6
    /**
     * Calcule les positions gagnantes de la moitie superieure du plateau
     * (x <= y). La position de rang r a pour abscisse le plus petit entier
     * pas encore utilise, et pour ordonnee abscisse + r.
     * @param taille taille du plateau
     * @param gx tableau (de taille au moins "taille") qui recevra les abscisses
     * @param gy tableau (de taille au moins "taille") qui recevra les ordonnees
     * @return le nombre de positions gagnantes trouvees
     */
    int calculerGagnantes (int taille, int[] gx, int[] gy) {
        boolean[] utilise = new boolean[3 * taille];
        int nb = 0;
        int rang = 0;
        int x = 0;
        while (x < taille && x + rang < taille) {
            gx[nb] = x;
            gy[nb] = x + rang;
            utilise[x] = true;
            utilise[x + rang] = true;
            nb = nb + 1;
            rang = rang + 1;
            x = 0;
            while (utilise[x]) {
                x = x + 1;
            }
        }
        return nb;
    }
//fonction 7
    /**
     * Indique si une position est gagnante (en tenant compte de la symetrie).
     * @param x abscisse
     * @param y ordonnee
     * @param gx abscisses des positions gagnantes
     * @param gy ordonnees des positions gagnantes
     * @param nb nombre de positions gagnantes
     * @return vrai si (x,y) est une position gagnante
     */
    boolean estGagnante (int x, int y, int[] gx, int[] gy, int nb) {
        boolean res = false;
        for (int i = 0; i < nb; i++) {
            if ((gx[i] == x && gy[i] == y) || (gx[i] == y && gy[i] == x)) {
                res = true;
            }
        }
        return res;
    }
//fonction 8
    /**
     * Verifie qu'un mouvement est autorise.
     * @param x abscisse du pion
     * @param y ordonnee du pion
     * @param dir direction : 1 = bas, 2 = gauche, 3 = diagonale bas-gauche
     * @param nb nombre de cases a parcourir
     * @return vrai si le mouvement est valide (reste dans le plateau)
     */
    boolean mouvementValide (int x, int y, int dir, int nb) {
        boolean res = false;
        if (nb >= 1) {
            if (dir == 1) {
                res = (nb <= y);
            } else if (dir == 2) {
                res = (nb <= x);
            } else if (dir == 3) {
                res = (nb <= x && nb <= y);
            }
        }
        return res;
    }


//PARTIE 3:
//fonction 9
    /**
     * Calcule la nouvelle abscisse apres un mouvement valide.
     * @param x abscisse actuelle
     * @param dir direction (1 = bas, 2 = gauche, 3 = diagonale)
     * @param nb nombre de cases
     * @return la nouvelle abscisse
     */
    int nouveauX (int x, int dir, int nb) {
        int res = x;
        if (dir == 2 || dir == 3) {
            res = x - nb;
        }
        return res;
    }
//fonction 10
    /**
     * Calcule la nouvelle ordonnee apres un mouvement valide.
     * @param y ordonnee actuelle
     * @param dir direction (1 = bas, 2 = gauche, 3 = diagonale)
     * @param nb nombre de cases
     * @return la nouvelle ordonnee
     */
    int nouveauY (int y, int dir, int nb) {
        int res = y;
        if (dir == 1 || dir == 3) {
            res = y - nb;
        }
        return res;
    }

//fonction 11
    // TESTS

    /**
     * Teste la methode mouvementValide sur plusieurs cas.
     */
    void testMouvementValide () {
        System.out.println("*** Test de mouvementValide ***");
        // cas normaux
        testCasMouvementValide(3, 5, 1, 2, true);
        testCasMouvementValide(3, 5, 2, 3, true);
        testCasMouvementValide(3, 5, 3, 3, true);
        // cas limites
        testCasMouvementValide(3, 5, 1, 5, true);
        testCasMouvementValide(3, 5, 2, 4, false);
        testCasMouvementValide(3, 5, 3, 4, false);
        // cas d'erreur
        testCasMouvementValide(3, 5, 1, 0, false);
        testCasMouvementValide(3, 5, 1, -2, false);
        testCasMouvementValide(3, 5, 4, 1, false);
        testCasMouvementValide(0, 4, 2, 1, false);
        testCasMouvementValide(0, 4, 3, 1, false);
    }
    
    
    
    
    //celle dans la principale -->
    
    
//fonction 12
    /**
     * Teste un cas de mouvementValide et affiche le resultat.
     * @param x abscisse du pion
     * @param y ordonnee du pion
     * @param dir direction
     * @param nb nombre de cases
     * @param attendu resultat attendu
     */
    void testCasMouvementValide (int x, int y, int dir, int nb, boolean attendu) {
        System.out.print("pion (" + x + "," + y + "), direction " + dir
                         + ", " + nb + " case(s) : attendu " + attendu + ", obtenu ");
        boolean res = mouvementValide(x, y, dir, nb);
        System.out.print(res + " -> ");
        if (res == attendu) {
            System.out.println("OK");
        } else {
            System.err.println("ERREUR");
        }
    }
   
    // AFFICHAGE
//fonction 13
    /**
     * Affiche le plateau. Le pion est note "o" et, si demande, les positions
     * gagnantes sont notees "x".
     * @param taille taille du plateau
     * @param px abscisse du pion
     * @param py ordonnee du pion
     * @param montrer vrai pour afficher les positions gagnantes (mode solo)
     * @param gx abscisses des positions gagnantes
     * @param gy ordonnees des positions gagnantes
     * @param nb nombre de positions gagnantes
     */
	void afficher (int taille, int px, int py, boolean montrer, int[] gx, int[] gy, int nb) {
    for (int ligne = taille - 1; ligne >= 0; ligne--) {
        String s = "";
        if (ligne < 10) {
            s = " ";
        }
        s = s + ligne + "|";
        for (int col = 0; col < taille; col++) {
            String symbole = " ";
            if (montrer && estGagnante(col, ligne, gx, gy, nb)) {
                symbole = "x";
            }
            if (col == px && ligne == py) {
                symbole = "o";
            }
            s = s + " " + symbole + " |";
        }
        System.out.println(s);
    }
    String bas = "   ";
    for (int col = 0; col < taille; col++) {
        if (col < 10) {
            bas = bas + " ";
        }
        bas = bas + col + "  ";
    }
    System.out.println(bas);
}
    // ORDINATEUR
//fonction 14
    /**
     * Regarde si la position (a,b) peut etre atteinte depuis (x,y) en un
     * mouvement. Si oui, remplit coup avec la direction et le nombre de cases.
     * @param x abscisse du pion
     * @param y ordonnee du pion
     * @param a abscisse de la cible
     * @param b ordonnee de la cible
     * @param coup tableau de 2 cases : coup[0] = direction, coup[1] = nombre de cases
     * @return vrai si la cible est atteignable
     */
    boolean tester (int x, int y, int a, int b, int[] coup) {
        boolean res = false;
        if (a == x && b < y) {
            coup[0] = 1;
            coup[1] = y - b;
            res = true;
        } else if (b == y && a < x) {
            coup[0] = 2;
            coup[1] = x - a;
            res = true;
        } else if (a < x && b < y && x - a == y - b) {
            coup[0] = 3;
            coup[1] = x - a;
            res = true;
        }
        return res;
    }
//fonction 15

    /**
     * Cherche un mouvement qui amene le pion sur une position gagnante.
     * @param x abscisse du pion
     * @param y ordonnee du pion
     * @param gx abscisses des positions gagnantes
     * @param gy ordonnees des positions gagnantes
     * @param nb nombre de positions gagnantes
     * @param coup tableau de 2 cases rempli avec la direction et le nombre de cases
     * @return vrai si un tel mouvement existe
     */
    boolean chercherCoupGagnant (int x, int y, int[] gx, int[] gy, int nb, int[] coup) {
        boolean trouve = false;
        int i = 0;
        while (i < nb && !trouve) {
            if (tester(x, y, gx[i], gy[i], coup) || tester(x, y, gy[i], gx[i], coup)) {
                trouve = true;
            }
            i = i + 1;
        }
        return trouve;
    }
    
//fonction 16

    /**
     * Choisit un mouvement aleatoire valide.
     * @param x abscisse du pion
     * @param y ordonnee du pion
     * @param coup tableau de 2 cases rempli avec la direction et le nombre de cases
     */
    void coupAleatoire (int x, int y, int[] coup) {
        int dir = (int) (Math.random() * 3) + 1;
        int nb = (int) (Math.random() * 16) + 1;
        while (!mouvementValide(x, y, dir, nb)) {
            dir = (int) (Math.random() * 3) + 1;
            nb = (int) (Math.random() * 16) + 1;
        }
        coup[0] = dir;
        coup[1] = nb;
    }
    
    
    
    
    
    
    //la fonction jouer dans la principale
//fonction 17
    /**
     * Deroule une partie complete de Wythoff.
     */
    void jouer () {
        int taille = saisirTaille();
        String mode = saisirMode();
        boolean solo = mode.equals("solo");

        // noms des joueurs (le joueur 2 est l'ordinateur en mode solo)
        String nom1;
        String nom2;
        int joueur = 1;
        if (solo) {
            nom1 = saisirNom("Nom du joueur : ");
            nom2 = "Ordinateur";
            joueur = saisirQuiCommence();
        } else {
            nom1 = saisirNom("Nom du joueur 1 : ");
            nom2 = saisirNom("Nom du joueur 2 : ");
        }

        // positions gagnantes
        int[] gx = new int[taille];
        int[] gy = new int[taille];
        int nb = calculerGagnantes(taille, gx, gy);

        // position initiale aleatoire (differente de la case d'arrivee)
        int x = (int) (Math.random() * taille);
        int y = (int) (Math.random() * taille);
        while (x == 0 && y == 0) {
            x = (int) (Math.random() * taille);
            y = (int) (Math.random() * taille);
        }

        String vainqueur = "";
        int[] coup = new int[2];
        while (x != 0 || y != 0) {
            afficher(taille, x, y, solo, gx, gy, nb);
            String nomCourant = nom1;
            if (joueur == 2) {
                nomCourant = nom2;
            }
            System.out.println("Au tour de " + nomCourant);

            if (solo && joueur == 2) {
                // tour de l'ordinateur : il vise une position gagnante si possible
                if (!chercherCoupGagnant(x, y, gx, gy, nb, coup)) {
                    coupAleatoire(x, y, coup);
                }
                System.out.println("L'ordinateur joue : direction " + coup[0]
                                   + ", " + coup[1] + " case(s)");
            } else {
                // tour d'un humain
                boolean ok = false;
                while (!ok) {
                    coup[0] = SimpleInput.getInt("Direction (1 = bas, 2 = gauche, 3 = diagonale) : ");
                    coup[1] = SimpleInput.getInt("Nombre de cases : ");
                    ok = mouvementValide(x, y, coup[0], coup[1]);
                    if (!ok) {
                        System.out.println("Mouvement invalide, recommencez.");
                    }
                }
            }

            int nouvX = nouveauX(x, coup[0], coup[1]);
            int nouvY = nouveauY(y, coup[0], coup[1]);
            x = nouvX;
            y = nouvY;

            if (x == 0 && y == 0) {
                vainqueur = nomCourant;
            } else if (joueur == 1) {
                joueur = 2;
            } else {
                joueur = 1;
            }
        }

        afficher(taille, x, y, solo, gx, gy, nb);
        System.out.println("Bravo " + vainqueur + ", vous avez gagne !");
    }
}
