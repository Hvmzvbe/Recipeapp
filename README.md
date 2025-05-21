
# 🍽️ RecipeApp2

**Application Android** de découverte de recettes à partir d’une API ouverte ([TheMealDB](https://www.themealdb.com)). L’utilisateur peut rechercher un plat, visualiser ses détails (ingrédients, description, image), et naviguer entre différentes catégories.

---

## 📱 Fonctionnalités

- 🔍 Recherche de recettes en temps réel (API + Room)
- 📄 Détails complets des plats avec image et ingrédients
- 📚 Sauvegarde locale avec Room (MVVM)
- 🌐 Intégration Retrofit + ViewModel
- 📦 Architecture en couches claire (DAO, Repository, ViewModel, View)
- ✅ Formulaire d’inscription avec validation (login/signup)

---

## 🧱 Arborescence du projet

```
RecipeApp2/
├── Data/
│   ├── Dao/
│   │   ├── Plat/         # Interface IPlat pour Room
│   │   └── User/         # Interface IUser pour Room
│   ├── model/            # Entités User, Plat, PlatResponse
│   ├── network/          # API Retrofit + Instance
│   ├── Repository/       # PlatRepository, UserRepository
│   └── Room/             # AppDatabase singleton
│
├── View/
│   ├── Home/
│   │   ├── Home.java           # Activité principale
│   │   └── SuggestionAdapter   # Adapter pour suggestions
│   ├── Plat/
│   │   └── PlatDetailActivity  # Détail du plat
│   ├── login/
│   │   └── MainActivity        # Connexion
│   ├── signup/
│   │   └── SignUp              # Création de compte
│   └── ViewModel/
│       └── outils/
│           ├── PlatViewModel
│           └── UserViewModel
│
├── res/
│   ├── layout/             # Fichiers XML (home, detail, etc.)
│   ├── drawable/           # Icônes, backgrounds
│   └── values/             # strings.xml, colors, themes
│
└── AndroidManifest.xml     # Déclarations des activités
```

---

## ⚙️ Technologies utilisées

- **Java**
- **Android Jetpack** (ViewModel, LiveData, Room)
- **Retrofit2** + GSON
- **Glide** pour chargement des images
- **MVVM** + Clean architecture simplifiée

---

## 🧪 Lancer le projet

1. Cloner le repo dans Android Studio
2. Ajouter la permission internet dans `AndroidManifest.xml` :

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

3. Lancer l’émulateur (Pixel 4 API 35 ou +)
4. Lancer l’activité `Home` (point d’entrée défini dans le manifest)

---

## 📸 Captures d’écran

| Recherche live | Détail recette |
|----------------|----------------|
| ![search](./search.png) | ![detail](./detail.png) |

---

## 📝 Auteur

- Projet développé par [TonNom], dans le cadre de l’évaluation Android – MVVM + API + Room.
