# TP Spring Boot / JPA

Ceci est un TP a destination des L3 Miage de l'UGA (IM²AG). 

Il peut être librement par quiquonque souhaite et sans limitation à l'exception de référencé ce répository
https://github.com/bordigoni/l3-tp-spring-boot-jpa

Ce TP nécessite: 
* Java 17
* Maven (optionnel si on utilise le wrapper)
* IntelliJ
  * ou un autre éditeur de texte et un outil de visualisation d'OpenAPI 
* Postman pour les tests

Pour les étudiants de l'UGA, les VM préconfigurée pourront être utilisés.

## PARTIE 1: SPRING BOOT

**Lisez tout!!!** sinon vous aurez du mal à rendre le TP

### Présentation

Vous allez implémenter une API gérant une bibliothèque.

Vous aurez en charge de gérer les operations de creation, modification, effacement et lecture/recherche pour:

* Les auteurs
* Les livres

*ATTENTION* la relation entre livres et auteurs est de type many-to-many. 

```mermaid
classDiagram
    Book "0..*" -- "1..*" Author
    class Author {
        Long id
        String fullName  
        addBook(Book)      
    }
    class Book {
        Long id
        String title
        long isbn
        String publisher
        short year
        String language
        addAuthor(Author)
    }
```

C'est à dire qu'un livre peut avoir plusieurs auteurs et un auteur peut être l'auteur de plusieurs livres.

### Quelques regles métiers
 
1. On ne peut pas créer de livre sans auteur 
1. On ne peut pas effacer un auteur qui est co-auteur d'un livre
1. Supprimer un auteur provoque la suppression de ses livres

Ces règles métiers sont déjà implémentées dans le module `service-mock`
et sont reflétées dans le contrat d'API en revanche vous devrez gérer les erreurs qui seront levées dans le cas n°2.

### Organisation du projet et architecture

Le projet contient 5 modules:

* `app` : l'application Spring Boot, avec:
  * Controller
  * DTO
  * Mapper DTO <--> Object de domaine
* `service-pub`: les interfaces de services métiers
* `service-mock`: une implémentation simple et en mémoire de service-pub  
* `data`: 
  * le modèle d'objet de domaine
  * les repository JPA (partie 2)
* `service-impl`: non utilisé pour la partie 1


Vous ne modifirez que le module app, mais vous aurez besoin de consulter `data` et `service-pub` pour mieux comprendre

```mermaid
graph TD
    A[app - spring boot] --> |depends on| B[service pub] --> |depends on| C[data]
    B --> |implemented by| D[service-mock]
```

### VOTRE TRAVAIL

Vous devrez  executer les deux points ci-dessous conjointement pour maximiser vos chances de réussite.

#### Implementation de l'API

En respectant toute la specification OpenAPI v3 [library-openapi.yaml](library-openapi.yaml) vous devrez implémenter toutes les méthodes présente dans `AuthorController` et `BooksController`.

Pour visualier l'OpenAPI, vous pouvez
* Utiliser IntelliJ
* Utiliser [Swagger](https://editor.swagger.com) editor (en ligne)
* Lire le document YAML (pour les personne ne souffrant pas de problèmes de vue ;-) 

Ceci inclut : 
* Les annotations Spring pour créer les path d'endpoint
* Les annotations Spring pour gérer les path param, query string, code de retour
* Les annotations pour la validation des DTO [Bean Validation](https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/constraints/package-summary.html))
* Les appels de service: veuillez consulter la javadoc des classes `AuthorService` `BookService` et aussi `BaseService` toutes les méthodes vous seront utiles.

Vous n'avez **PAS** a implémenter:
* les services
* les DTO
* le mapping DTO <==> objects de domaine

#### Vérifier le contrat d'API

A l'aide de Postman vous pourrez importer deux resources que vous retrouverez dans l'onglet "Collections" sur la gauche: 

* le fichier [library-openapi.yaml](library-openapi.yaml)
  File -> Import -> OpenAPI ... puis choisissez le fichier
  Vous y retrouverez tous les endpoint classés par path avec des examples de paylaod
* le fichier des tests à executer [L3 Miage Library API Test.postman_collection.json](L3%20Miage%20Library%20API%20Test.postman_collection.json)
  Il contient une série de requêtes, contentant des tests.
  File -> Import -> Upload files... puis choisissez le fichier

Ce dernier vous aidera à choisir l'ordre dans lequel vous pouvez envisager d'implémenter vos endpoint.

#### HOWTO: Comment lancer les tests dans Postman ?

1. Cliquez sur la collection "L3 Miage Library API Test"
2. Cliquez sur 'Run' en haut du panneau de droite
3. Cliquez sur 'Run L3 Miage Library API Test'
4. Dans "summary" en haut à droite vous aurez une vue synthétique des resultat, il y un onglet "Failed".

Vous pouvez aussi les executer individuellement en cliquant sur la requête et en cliquant ensuite sur le bouton 'Send' à droite.

Je vous conseille de bien redemarrer l'application entre chaque salve de test pour éviter les effets de bord.


### Evaluation & rendu

L'évaluation se fera:
* sur le nombre d'endpoint implémentés et le soin apporté au code
* mais surtout sur les tests qui fonctionnent (voir ci-dessus)

**EN AUCUN SERA ACCEPTER UN CODE QUI NE COMPILE PAS OU UNE APPLICATION QUI NE DEMARRE PAS**

Vous devrez forker ce repository sur votre compte github puis cloner votre fork et pousser les changement. 
Vous devrez donc me rendre : l'URL de votre fork dans le Moodle. 
Vous pourrez annoté votre rendu pour faliciter sa correction.

### Bonus

Vous rendrez compte qu'un endpoint est manquant !

* ajoutez le à la spec OpenAPI
* implementez-le

Plusieurs indice peuvent trahir ça présence.

Si il n'est pas implémenté, alors vous ne pourrez pas executer tous les tests d'un coup.
Si tout ce passe

## PARTIE 2: JPA