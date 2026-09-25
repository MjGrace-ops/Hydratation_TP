# Application de suivi d'hydratation

## Présentation

Ce projet est une application Android permettant de suivre la quantité d'eau consommée au cours d'une journée. Elle a été développée en Kotlin avec Jetpack Compose et Material Design 3.

L'application utilise une architecture MVVM. L'état de l'interface est géré par un `ViewModel` et exposé au moyen d'un `StateFlow`.

## Fonctionnalités

- définition d'un objectif quotidien de 2 000 ml ;
- affichage de la progression sous la forme d'une jauge circulaire ;
- ajout rapide de 100 ml, 250 ml ou 500 ml ;
- affichage de la quantité consommée et de la quantité restante ;
- calcul du nombre de verres consommés sur la base de 250 ml par verre ;
- enregistrement de l'heure de chaque ajout dans l'historique de la journée ;
- annulation du dernier ajout ;
- réinitialisation des données après confirmation ;
- indication visuelle lorsque l'objectif quotidien est atteint.

## Technologies utilisées

- Kotlin ;
- Jetpack Compose ;
- Material Design 3 ;
- architecture MVVM ;
- `ViewModel` et `StateFlow` pour la gestion de l'état ;
- Gradle avec un catalogue de versions ;
- JUnit, Robolectric et Roborazzi pour les tests.

## Organisation du projet

```text
Hydratation_TP/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── HydrationViewModel.kt
│   │   │   │   └── ui/
│   │   │   │       ├── HydrationScreen.kt
│   │   │   │       └── theme/
│   │   │   │           ├── Color.kt
│   │   │   │           ├── Theme.kt
│   │   │   │           └── Type.kt
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   ├── test/
│   │   └── androidTest/
│   └── build.gradle.kts
├── gradle/
│   ├── wrapper/
│   └── libs.versions.toml
├── build.gradle.kts
├── gradle.properties
├── settings.gradle.kts
├── gradlew
└── gradlew.bat
```

### Principaux fichiers

- `MainActivity.kt` constitue le point d'entrée de l'application.
- `HydrationViewModel.kt` contient l'état et les opérations liées au suivi de la consommation d'eau.
- `HydrationScreen.kt` définit l'interface utilisateur et ses composants.
- Le dossier `ui/theme` contient les couleurs, la typographie et le thème Material 3.
- `libs.versions.toml` regroupe les versions des bibliothèques et des plugins Gradle.

## Configuration technique

| Élément | Valeur |
|---|---:|
| Version minimale d'Android | API 24 |
| Version cible d'Android | API 35 |
| Version de compilation | API 35 |
| Version de Gradle | 8.11.1 |
| Android Gradle Plugin | 8.10.1 |
| Version de Kotlin | 2.0.21 |
| Cible JVM du code | JVM 11 |

Android Studio doit utiliser un JDK compatible avec la version du plugin Android. Le JDK intégré à une version récente d'Android Studio convient à cette configuration.

## Ouverture du projet dans Android Studio

1. Télécharger ou cloner le projet.
2. Ouvrir Android Studio.
3. Sélectionner **Open**, puis choisir le dossier racine `Hydratation_TP`.
4. Attendre la fin de la synchronisation Gradle et du téléchargement des dépendances.
5. Vérifier que le SDK Android 35 est installé dans le SDK Manager.

## Compilation

La compilation peut être lancée depuis Android Studio avec l'action **Build Project**. Elle peut aussi être effectuée dans un terminal ouvert à la racine du projet.

Sous Windows :

```powershell
.\gradlew.bat :app:assembleDebug
```

Sous Linux ou macOS :

```bash
./gradlew :app:assembleDebug
```

L'APK produit se trouve dans le dossier suivant :

```text
app/build/outputs/apk/debug/
```

## Exécution

1. Démarrer un émulateur Android local ou connecter un appareil physique avec le débogage USB activé.
2. Sélectionner cet appareil dans Android Studio.
3. Choisir la configuration `app`.
4. Cliquer sur **Run 'app'** ou utiliser le raccourci `Shift + F10`.

Si un appareil Firebase est sélectionné mais indisponible, il faut choisir un émulateur local dans la liste des appareils. Si l'émulateur est indiqué comme hors ligne, il doit être arrêté puis redémarré avant une nouvelle exécution.

## Tests

Les tests unitaires peuvent être lancés avec la commande suivante :

```powershell
.\gradlew.bat :app:testDebugUnitTest
```

Ils peuvent également être exécutés depuis Android Studio en effectuant un clic droit sur le dossier `app/src/test` puis en sélectionnant **Run Tests**.

## Remarque sur les données

Les informations affichées correspondent à l'état de la session en cours. Aucune authentification n'est nécessaire pour utiliser les fonctions principales de suivi de l'hydratation.
