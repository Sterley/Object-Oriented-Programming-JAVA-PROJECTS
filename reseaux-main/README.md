**Projet Programmation : Analyseur de Protocoles Réseau ‘Offline’** LU3IN033 Réseaux

Nom : LABADY,           Prénom : Sterley Gilbert,   Numéro : 21100776

Nom : CHAOUATI,         Prénom : Oualid,            Numéro : 21114737

**Structure du code**

Le point de départ du programme se fait à partir de la classe App qui contient la fonction d'entrée (main).  App initialisera un objet Analyseur qui gère l’interface graphique. Cet objet aura pour fonction d'initialiser l'ensemble des éléments graphiques : Window, Frame, Button, Label, Textfield. De là s'affiche le programme à l'écran. 

Au lancement du programme un seul bouton est cliquable : Feuilleter… En cliquant sur ce bouton, la fonction event du bouton initialisera un objet JFileChooser qui ouvrira une fenêtre pour nous permettre de choisir un fichier à analyser. A noter que le fichier doit être d’extension .txt ou  .trace. 

A l’ouverture de la fenêtre pour choisir le fichier. Si l’utilisateur choisit un fichier .txt ou .trace on aura l’initialisation d’un objet Vérification qui fera la vérification complète de la qualité du fichier. S’il choisit un autre type de fichier, un petit message indique qu'il doit choisir un fichier .txt ou .trace (ERREUR 01 : Vous devez sélectionner un fichier .txt ou .trace). S’il choisit de fermer la fenêtre, l’analyse sera annulée.  

Durant la vérification du fichier, le constructeur lira le fichier ligne par ligne pour vérifier la qualité des octets et des offsets. En cas d’erreur, un petit message s'affiche dans le textfield vous indiquant la ligne des données erronée. 
Liste des erreurs possibles : 
Un octet qui n’est pas une valeur hexadécimale.  
Un offset dans une trame qui n’a pas été initialisée avec un offset 0000.
Un offset qui n’est pas supérieur à 3 caractères hexadécimales.
Un offset dont la valeur n’est pas cohérente avec la trame en cours de lecture.
Cependant : un octet qui n’est pas un caractère hexadécimal a la fin d’une ligne est ignoré. Une ligne qui ne commence pas par un offset en caractères hexadécimales, la ligne est donc ignorée.

L’objet Vérification vérifie aussi si la trame respecte la longueur minimale (64 octets) et la longueur maximale (1518 octets). Il vérifie si on a bien un type IPV4 dans l'en-tête Ethernet, une version 4, un protocole UDP, un IHL valide (5<=IHL<=15) et un Total Length valide (20<=Longueur) dans l'en-tête IP. Si tout se passe bien, Verification indique dans Analyseur qu’il n’y a pas d’erreur, en cas contraire un message expliquant l’erreur est retourné.

Après une Vérification réussie vous aurez la possibilité de voir le nombre de trames contenues dans le fichier et de pouvoir choisir votre trame à analyse. A noter qu’une Trame c’est un ArrayList, toutes les trames sont stockées dans un ArrayList d’ArrayList.

En cliquant sur Analyser on récupère l’indice de la trame sélectionnée, on fait un .get(i) sur l’ArrayList des trames et on initialise l’ensemble des objets correspondant à chaque entête. DNS et DHCP s'initialisent dépendamment du numéro de port UDP (DNS(53), DHCP(67,68). Chaque objet d'en-tête prendra en paramètre du constructeur l’ArrayList de la trame pour pouvoir procéder au décodage. A noter qu’en cliquant sur Analyser un fichier Trace.txt sera généré dans le même dossier que le programme et contiendra le décodage de la trame comme dans wireshark.

Dans l’objet Ethernet on récupère chaque champ d'en-tête a partir d’un indice sur l’ArrayList de la trame, de même pour IP et UDP (ces 3 sont les plus faciles à décoder, on passe directement à la lecture de Nom avec DNS).

Pour DNS après avoir récupéré les champs précédents les champs Questions, Answer, Authority et Additional, avec l’indice sur ArrayList, le code devient plus complexe. Pour récurer les Queries, Answer, Authority et Addition, on crée une boucle pour chaque type de champ dans l’ordre qu’il se trouve. Dans cette boucle on récupère le nom, le type, la classe et les data. Pour avoir les noms (data en string), on a créé une fonction récursive qui va commencer a lire le nom, lorsque la fonction rencontre un pointeur, elle fait un appelle sur elle-même pour aller continuer a lire sur l’octet qu’indique le pointeur. A la fin retourne le domaine.

Pour DHCP, pour les options, on a créé une liste des options qui auront une adresse IP comme donnée, une liste d’options qui auront un entier et une liste d'options qui auront un nom (string). On implémente une fonction pour chacun des cas et on vérifie dans quelle liste se trouve l’option pour savoir quelle fonction appeler pour récupérer les données. On a enfin une fonction avec un switch case qui retourne le nom correspondant à l'option.

NB : Si par exemple dans l'entête IP on a un protocole de type UDP et que les données d’IP ne font pas la longueur de l'entête UDP, vous aurez un message d’ERREUR vous indiquant que vos données sont erronées.

Et pour conclure après initialisation des objets de chacun des champs d’entêtes, vous pouvez donc naviguer sur les entêtes avec les boutons correspondants.
