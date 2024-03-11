import java.util.Arrays;
/**
*@author Lucas Gelgon
*Le jeu se joue à deux joueurs. La position de départ consiste en un
*unique tas d’objets (des allumettes ou des pions, par exemple).
* Le seul coup disponible pour les joueurs consiste à séparer
*un tas d’objets en deux tas de tailles distinctes. Les joueurs jouent
* à tour de rôle, jusqu’à ce que l’un d’entre eux ne puisse plus jouer.
*/
class Grundy1{
    void principal(){
        jeu();
		//testCreerLigne();
        //testAfficherLigne();
        //testVerifTas();
		//testSeparerTas();
        //testLongueurLigne();
        //testEstGagnant();
        //testRecherche();
       
    }
      
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
        String joueur1 = SimpleInput.getString("Nom du premier joueur : ");
        String joueur2 = SimpleInput.getString("Nom du second joueur : ");
        afficherLigne(numLigne, tas);
        /* lesTas est un "tableau de tableaux" qui comportera les différents
         * tas de la partie. Il a une longueur de allumettes afin de faire 
         * large et d'être sûr d'avoir des tableaux assez grands. lesIndices
         * est le tableau avec les indices correspondant aux tas.*/        
        String[] lesTas;
        lesTas = new String[allumettes];
        int[] lesIndices;
        int i = 0;
        // Remplit le tableau avec des zero
        while (i < allumettes){
            lesTas[i] = creerLigne(0);
            i++;
        }
        lesIndices = new int[allumettes];
        boolean peutContinuer = true;
        int compteur = 0;
        /* tourJoueur = true signifie que c'est le tour du premier joueur, 
           tourJoueur = false signifie que c'est le tour du second joueur.*/
        boolean tourJoueur = true;
        lesTas[numLigne] = tas;
        boolean estDansJeu;
        int indiceLigne;
        int var;
        while (peutContinuer == true){
            if (tourJoueur == true){
                System.out.println();
                System.out.println("Tour du joueur : "+joueur1);
            }else{
                System.out.println();
                System.out.println("Tour du joueur : "+joueur2);
            }
            indiceLigne = SimpleInput.getInt("Ligne : ");
            estDansJeu = recherche(lesIndices, indiceLigne);
            while ((estDansJeu == false)||(longueurLigne(lesTas[indiceLigne]) <= 2)){
                indiceLigne = SimpleInput.getInt("Ligne : ");
                estDansJeu = recherche(lesIndices, indiceLigne);
            }
            tas = separerTas(longueurLigne(tas));
            numLigne = numLigne + 1;
            lesTas[numLigne] = tas;
            while (compteur <= numLigne){
                if (indiceLigne == compteur){
                    var = longueurLigne(lesTas[compteur]) - longueurLigne(lesTas[compteur+1]);
                    lesTas[compteur] = creerLigne(var);
                }
                afficherLigne(compteur, lesTas[compteur]);
                compteur = compteur + 1;
            }
            compteur = 0;
            lesIndices[numLigne] = numLigne;
            if (estGagnant(lesIndices, lesTas) == true){
                peutContinuer = false;
                if (tourJoueur == true){
                    System.out.println(joueur1+" est le gagnant !");
                }else{
                    System.out.println(joueur2+" est le gagnant !");
                }
                break;
            }
            if (tourJoueur == true){
                tourJoueur = false;
            }else{
                tourJoueur = true;
            }
            
            
        }
        

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
     * teste un appel de la méthode recherche
     * @param tab - tableau dans lequel on cherche une valeur
     * @param valeur - valeur recherchée
     * @param result - resultat attendu
     */
     void testCasRecherche(int[] tab, int valeur, boolean result){
         //Arrange
         System.out.println ("recherche()\t= " + result+"\t : ");
         
         //Act
         boolean resExec = recherche(tab, valeur);
         
         //Assert
         if (resExec == result){
             System.out.println("OK");
        } else{
            System.err.println("ERREUR");
        }
    }
    
      /**
     * teste la méthode recherche()
     */
    void testRecherche(){
        System.out.println();
        System.out.println("*** testRecherche()");

        int[] tab1 = {1, 2, 3, 4};
        testCasRecherche(tab1, 2, true);
        testCasRecherche(tab1, 5, false);


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
     * teste un appel de la méthode CreerLigne
     * @param allumettes - le nombre d'allumettes dans le tas
     * @param result - resultat attendu
     */
     void testCasCreerLigne(int allumettes, String result){
         //Arrange
         System.out.println ("creerTas("+allumettes+")\t= " + result+"\t : ");
         
         //Act
         String resExec = creerLigne(allumettes);
         
         //Assert
         if (resExec.length() == result.length()){
             System.out.println("OK");
        } else{
            System.err.println("ERREUR");
        }
    }
    
      /**
     * teste la méthode creerLigne()
     */
    void testCreerLigne(){
        System.out.println();
        System.out.println("*** testCreerLigne()");
        
        //Cas normaux
        testCasCreerLigne(5, "| | | | | ");
        testCasCreerLigne(3, "| | | ");
        testCasCreerLigne(4, "| | | "); //Renvoie ERREUR
        
        //Cas limite
        testCasCreerLigne(1, "| ");
        
        //Cas d'erreur
        testCasCreerLigne(0, "");

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
     * teste la méthode afficherLigne()
     * La méthode separerTas() est un void donc il n'y a pas de testCas.
     * De plus, il y a seulement de cas normaux car cette méthode sert
     * uniquement à afficher une ligne.(qui a été crée avec CreerLigne).
     */
     void testAfficherLigne(){
         System.out.println();
         System.out.println("***testAfficherLigne()");
         afficherLigne(0, "| | | | | ");
         afficherLigne(1, "| | | | ");
         afficherLigne(2, "| | |");


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
     * teste un appel de la méthode verifTas
     * @param nbAllumettes - le nombre d'allumettes dans le tas
     * @param tasActuel - le nombre d'allumettes qu'on veut séparer 
     * @param result - resultat attendu
     */
     void testCasVerifTas(int nbAllumettes, int tasActuel, boolean result){
         //Arrange
         System.out.println("verifTas("+tasActuel+")\t="+result+"\t :");
         //Act
         boolean resExec = verifTas(nbAllumettes, tasActuel);
         //Assert
         if (resExec == result){
             System.out.println("OK");
         }else{
             System.err.println("ERREUR");
         }
    }
    
    /**
     * teste la méthode verifTas()
     * Cette méthode est un booléen, il y a donc que des cas normaux car
     * le principe de la méthode est de déterminer si la valeur saisie
     * fonctionne ou non, puis de demander de saisir un nouveau
     * nombre (dans la méthode separerTas) si elle ne fonctionne pas.
     */
     void testVerifTas(){
         System.out.println();
         System.out.println("***testVerifTas()");
         testCasVerifTas(7, 3, true);
         testCasVerifTas(5, 0, true);
         testCasVerifTas(8, 4, false); //Cela ferait deux tas de 4 allumettes
         testCasVerifTas(3, -1, false);
    }
    
    /**
     * Sépare le tas d'allumettes
     * @param nbAllumettes - le nombre d'allumettes dans le tas  
     * @return nouveauTas - le nouveau tas d'allumettes
     */
    String separerTas(int nbAllumettes){
        String ret = "NbAllumettes négatif";
        /* tasActuel correspond au "tas actuel" auquel on aura enlevé le nombre d'allumettes saisi
         * C'est donc le tas de longueur nbAllumettes auquel on aura retiré un certain nombre d'allumettes.
         */        
		int tasActuel = SimpleInput.getInt("nombre d'allumettes a separer : ");
        boolean verif = verifTas(nbAllumettes, tasActuel);
        // verif == true
        while (verif == false){
            //Ce cas est particulier, il faut afficher une erreur sinon la méhode tourne en boucle
            if (nbAllumettes < 0){
                break;
            }
            tasActuel = SimpleInput.getInt("nombre d'allumettes a separer : ");
            verif = verifTas(nbAllumettes, tasActuel);
        }
        /* nouveauTas correspond au nouveau tas d'allumettes qui va être crée en dessous du/des tas d'allumettes 
         * du tour précédent. Sa longueur sera donc égale à la longueur du "tas précédent" moins celle du "tas actuel".
         * Voici un exemple de jeu :
         *  0 : | | | | | | |
         *  Tour du premier joueur : Lucas
         *  Nombre d’allumettes à séparer : 2
         *  0 : | |
         *  1 : | | | | |
         * Ici, nouveauTas correspond à la ligne 1 et tasActuel à la ligne 0.
         */
          
        if (verif == true){
            String nouveauTas = creerLigne((nbAllumettes-tasActuel));
            ret = nouveauTas;
        }
		return ret;
    }
		
		
	
     /** 
     * teste un appel de la méthode separerTas
     * @param allumettes - le nombre d'allumettes dans le tas
     * @param result - resultat attendu
     */
     void testCasSeparerTas(int allumettes, String result){
         //Arrange
         System.out.println("separerTas("+allumettes+")\t= "+result+"\t :");
         //Act
         String resExec = separerTas(allumettes);
         //Assert
         if (resExec.length() == result.length()){
             System.out.println("OK");
         }else{
             System.err.println("ERREUR");
         }
    }
    
     /**
     * teste la méthode separerTas()
     */
     void testSeparerTas(){
         System.out.println();
         System.out.println("***testSeparerTas()");
         //Cas normaux
         String ligne1 = creerLigne(3); 
         testCasSeparerTas(7,ligne1); //Saisir 4
         String ligne2 = creerLigne(1); 
         testCasSeparerTas(3,ligne2); //Saisir 2
         
         //Cas d'erreur
         String ligne3 = creerLigne(4);
         testCasSeparerTas(8,ligne3); 
         /*Cas impossible car les deux valeurs doivent être distinctes.
          *Il faudrait saisir 4 afin d'obtenir la ligne 2, mais cela demande
          *de saisir un nouveau nombre. Il faut donc saisir un autre nombre
          *et cela affiche ERREUR*/
         String ligne4 = creerLigne(-1);
         testCasSeparerTas(-1,ligne4);
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
    
    /** 
     * teste un appel de la méthode estGagnant
     * @param 
     * @param result - resultat attendu
     */
     void testCasLongueurLigne(String ligne, int result){
         //Arrange
         System.out.println("longueurLigne("+ligne+")\t= "+result+"\t :");
         //Act
         int resExec = longueurLigne(ligne);
         //Assert
         if (resExec == result){
             System.out.println("OK");
         }else{
             System.err.println("ERREUR");
         }
    }
    
    /**
     * teste la méthode longueurLigne()
     */
     void testLongueurLigne(){
         System.out.println();
         System.out.println("***testLongueurLigne()");
         
         //Cas normaux
         testCasLongueurLigne("| | | ", 3);
         testCasLongueurLigne("| | | | | ", 4); //Renvoie ERREUR
         
         //Cas limite 
         testCasLongueurLigne("| ", 1);
         
         //Cas d'erreur
         testCasLongueurLigne("", 0);

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

     /** 
     * teste un appel de la méthode estGagnant
     * @param lesIndices - un tableau contenant les numéros de lignes
     * @param lesTas - le tableau contenant tous les tas du tour
     * @param result - resultat attendu
     */
     void testCasEstGagnant(int[] lesIndices, String[] lesTas, boolean result){
         //Arrange
         System.out.println("estGagnant()\t= "+result+"\t :");
         //Act
         boolean resExec = estGagnant(lesIndices, lesTas);
         //Assert
         if (resExec == result){
             System.out.println("OK");
         }else{
             System.err.println("ERREUR");
         }
    }
    
    /**
     * teste la méthode estGagnant()
     */
     void testEstGagnant(){
         System.out.println();
         System.out.println("***testEstGagnant()");
         int[] lesIndices = {0, 1, 2, 3, 4, 0, 0};
         String[] lesTas = {"| | "," | ","| ","| | | ","| | "," "," "};
         testCasEstGagnant(lesIndices, lesTas, true); // Renvoie ERREUR
         testCasEstGagnant(lesIndices, lesTas, false);

    }


}
