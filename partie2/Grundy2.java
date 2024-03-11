/**
*@author Lucas Gelgon
*Cette version du jeu de Grundy oppose le joueur à un ordi. 
* La position de départ consiste en un unique tas d’objets 
* (des allumettes ou des pions, par exemple).
* Le seul coup disponible pour les joueurs consiste à séparer
*un tas d’objets en deux tas de tailles distinctes. Le joueur et l'ordi jouent
* à tour de rôle, jusqu’à ce que l’un d’entre eux ne puisse plus jouer.
* Les méthodes de Grundy1 ont déjà été testées donc leurs méthodes de test
* sont inutiles dans cette partie.
*/
class Grundy2{
    
    /*Pour la partie Grundy2, mon code ne fonctionne pas et est loin d'être correct.
    Mais voici mes idées concernant les méthodes qui pourraient faire fonctionner le bot*/
    /**
     * La méthode jeu éxécute une partie de Grundy à deux joueurs.
     */
    void jeu(){
        //Saisie du nombre d'allumettes puis des noms des joueurs
        int allumettes;
        allumettes = SimpleInput.getInt("Nombre d'allumettes : ");
        int numLigne = 0;
        String tas;
        /* Il ne peut pas il y avoir un nombre d'allumettes négatif, si il
         * y a une ou deux allumettes, le premier joueur gagne et il n'y a
         * pas de jeu.*/
        // condition continuation: allumettes >= 3
        while (allumettes < 3){
            allumettes = SimpleInput.getInt("Nombre d'allumettes : ");
            tas = creerLigne(allumettes);
        }
        tas = creerLigne(allumettes);
        char joueur1 = SimpleInput.getChar("Nom du joueur : ");
    }
    
    
    /**
     * Renvoie les décompositions d'un nombre sous la forme d'un tableau
     * @param nombre - nombre dont on veut connaitre les décompositions
     * @return tab - tableau contenant les décompositions du nombre.
     */
     int[] decomposition(int nombre){
         /* Pour cette méthode, on peut créer un tableau pour chaque 
          * décomposition et regrouper ces tableaux dans tab qui sera un
          * "tableau de tableaux".*/
          int tab[];
          tab = new int[nombre];
          int i = 1;
          /* On divise nombre par 2 afin de ne pas avoir deux fois les mêmes
           * décompositions. Exemple : Si on a le chiffre 7, il y aura les 
           * décompositions 3+4 et 4+3 alors que ce sont les mêmes.*/
          while ( i < nombre/2){
              // Si nombre = 20, ça donne 1+19, 2+18, 3+17, etc..
              tab[i] = i+nombre -1;
              i++;
              nombre--;
        }
         return tab;
    }
    
    
    
    /**
     * Renvoie les décompositions d'un nombre sous la forme d'un tableau
     * @param nombre - entier 
     * @return true si le nombre est gagnant, c'est à dire que ses décompositions
     * sont perdantes.
     */
    boolean situation(int nombre){
        boolean ret = true;
        int[] decompositionsNbr = decomposition(nombre);
        int i = 0;
        while (i < decompositionsNbr.length){
            if (situation(decompositionsNbr[i]) == false){
                ret = false;
            }
        }
        
        return ret;
    }    
        
            
        
    
    
    










    
    /**
     * Regarde si une valeur est présente dans un tableau.
     * @param tab - tableau dans lequel on cherche une valeur
     * @param valeur - valeur recherchée
     * @return true si la valeur recherchée est dans le tableau, false
     * si elle n'y est pas.
     */
    boolean recherche(int[] tab, int valeur){
        boolean ret = false;
        int i = 0;
        while (i < tab.length){
            if (tab[i] == valeur){
                ret = true;
                break;
            }
            i++;
        }

        return ret;
    }
    
    /**
     * crée un tas d'alumettes 
     * @param allumettes - le nombre d'allumettes dans le tas
     * @return ligne - tas contennant les allumettes sous forme de bâtons ('|')
     */
     String creerLigne(int allumettes){
		 String ligne = "";
		 if (allumettes > 0){
			int i = 0;
			while ( i < allumettes){
				ligne = ligne + "| ";
				i++;
			}
		}
        return ligne;
    }
    
    /**
     * Affiche une ligne du jeu
     * @param ligne - tas d'allumettes
     * @param num - numéro de la ligne à afficher
     */ 
    void afficherLigne(int numLigne, String ligne){
        System.out.println(numLigne+" : "+ligne);
    }
    
    /**
     * Vérifie que le nombre d'allumettes que le joueur veut séparer d'un
     * tas n'enfreint pas les règles du jeu.
     * Tout d'abord, il faut vérifier que le joueur ne saisisse pas un
     * nombre négatif, en effet on ne peut pas retirer un nombre négatif
     * d'allumettes. Ensuite, il faut vérifier que le nombre d'allumettes qu'il retire
     * ne soit pas égal au nombre d'allumettes qu'il y aura dans nouveauTas
     * (dans la méthode separerTas), car les deux tas doivent être distincts.
     * @param nbAllumettes - le nombre d'allumettes dans le tas
     * @param tasActuel - le nombre d'allumettes qu'on veut séparer
     * @return true si le nombre d'allumettes à séparer est conforme aux règles
     */
    boolean verifTas(int nbAllumettes, int tasActuel){
        boolean ret = true;
        if (tasActuel <= 0){
            ret = false;
        }
        if( tasActuel == nbAllumettes-tasActuel){
            ret = false;
        }
        if( tasActuel >= nbAllumettes){
            ret = false;
        }
        
        return ret;
    }
    
    /**
     * Sépare le tas d'allumettes
     * @param nbAllumettes - le nombre d'allumettes dans le tas  
     * @return nouveauTas - le nouveau tas d'allumettes
     */
    String separerTas(int nbAllumettes){
        String ret = "NbAllumettes négatif";      
		int tasActuel = SimpleInput.getInt("nombre d'allumettes a separer : ");
        boolean verif = verifTas(nbAllumettes, tasActuel);
        // verif == true
        while (verif == false){
            if (nbAllumettes < 0){
                break;
            }
            tasActuel = SimpleInput.getInt("nombre d'allumettes a separer : ");
            verif = verifTas(nbAllumettes, tasActuel);
        }
        if (verif == true){
            String nouveauTas = creerLigne((nbAllumettes-tasActuel));
            ret = nouveauTas;
        }
		return ret;
    }
    
    /** 
     * Renvoie la longueur d'un tas.
     * @param ligne - un tas d'allumettes
     * @return longueur - la longueur du tas
     */
    int longueurLigne(String ligne){
        int longueur = (ligne.length())/2;
        return longueur;
    }
    
    /**Détermine si une combinaison est gagnante, renvoie vrai si 
     * la combinaison est gagnante, false si elle ne l'est pas.
     * @param lesIndices - un tableau contenant les numéros de lignes
     * @param lesTas - le tableau contenant tous les tas du tour
     * @return tasMin - vrai si tous les tas on la longueur minimum (3),
     *  retourne false sinon
     */
    boolean estGagnant(int[] lesIndices, String[] lesTas){ 
		boolean tasMin = true;
             for(int i = 0;  (i < lesIndices.length) && (tasMin == true); i++){
                 if (longueurLigne(lesTas[i]) > 2){
					tasMin = false;
                }
            } 
        return tasMin;
    }
}
    
